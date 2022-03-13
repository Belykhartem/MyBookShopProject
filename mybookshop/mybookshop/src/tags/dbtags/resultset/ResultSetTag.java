/*
* Copyright 1999,2004 The Apache Software Foundation.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package tags.dbtags.resultset;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;
import java.util.Iterator;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;

import tags.dbtags.statement.StatementTag;


/**
 * <p>JSP tag resultSet, executes the query and loops through the results
 * for the enclosing statement or preparedstatement tag.  The body of
 * this tag is executed once per row in the resultset.  The optional
 * "loop" attribute, which default to true, specifies whether to execute
 * the tag body once per row "true", or to simply assign the ResultSet
 * to the page attribute specified by "id". The optional "name" and "scope"
 * attributes can be used to retrieve a resultset (or rowset) from context.</p>
 *
 * <p>JSP Tag Lib Descriptor
 * <pre>
 * &lt;name>resultSet&lt;/name>
 * &lt;tagclass>tags.dbtags.resultset.ResultSetTag&lt;/tagclass>
 * &lt;teiclass>tags.dbtags.connection.ResultSetTEI&lt;/teiclass>
 * &lt;bodycontent>JSP&lt;/bodycontent>
 * &lt;info>JSP tag resulset, executes the query and loops through the results
 * for the enclosing statement or preparedstatement tag.  The body of
 * this tag is executed once per row in the resultset.  The optional
 * "loop" attribute, which default to true, specifies whether to execute
 * the tag body once per row "true", or to simply assign the ResultSet
 * to the page attribute specified by "id". The optional "name" and "scope"
 * attributes can be used to retrieve a resultset (or rowset) from context.<&lt;/info>
 *   &lt;attribute>
 *     &lt;name>id&lt;/name>
 *     &lt;required>true&lt;/required>
 *     &lt;rtexprvalue>false&lt;/rtexprvalue>
 *   &lt;/attribute>
 *   &lt;attribute>
 *     &lt;name>loop&lt;/name>
 *     &lt;required>false&lt;/required>
 *     &lt;rtexprvalue>false&lt;/rtexprvalue>
 *   &lt;/attribute>
 *   &lt;attribute>
 *     &lt;name>name&lt;/name>
 *     &lt;required>false&lt;/required>
 *     &lt;rtexprvalue>true&lt;/rtexprvalue>
 *   &lt;/attribute>
 *   &lt;attribute>
 *     &lt;name>scope&lt;/name>
 *     &lt;required>false&lt;/required>
 *     &lt;rtexprvalue>false&lt;/rtexprvalue>
 *   &lt;/attribute>
 * </pre>
 *
 * @author Morgan Delagrange
 * @author Ted Husted
 * @author Craig McClanahan
 * @see tags.dbtags.statement.StatementImplTag
 * @see tags.dbtags.statement.QueryTag
 * @see tags.dbtags.preparedstatement.PreparedStatementImplTag
 */
public class ResultSetTag extends BodyTagSupport{

    private Statement _statement = null;
    private ResultSet _rset = null;
    private boolean _shouldLoop = true;
    private String _name = null;
    private String _scope = null;
    private int _rowCount = 0;
    private int _rowCountAll = 0;
    private static int UNASSIGNED=-1;
    private int startRow = UNASSIGNED, endRow = UNASSIGNED;
    private boolean keepGoing;

    private boolean _isBack=false;
    private boolean _isFwd=false;
    private int _pageCount=0;
    /**
     * Locate and return the specified bean, from an optionally specified
     * scope, in the specified page context.  If no such bean is found,
     * return <code>null</code> instead.  If an exception is thrown, it will
     * have already been saved via a call to <code>saveException()</code>.
     *
     * @param pageContext Page context to be searched
     * @param name Name of the bean to be retrieved
     * @param scope Scope to be searched (page, request, session, application)
     *  or <code>null</code> to use <code>findAttribute()</code> instead
     *
     * @exception JspException if an invalid scope name
     *  is requested
     */
    protected Object lookup(PageContext pageContext,
                            String name, String scope) throws JspTagException {

        Object bean = null;
        if (scope == null)
            bean = pageContext.findAttribute(name);
        else if (scope.equalsIgnoreCase("page"))
            bean = pageContext.getAttribute(name, PageContext.PAGE_SCOPE);
        else if (scope.equalsIgnoreCase("request"))
            bean = pageContext.getAttribute(name, PageContext.REQUEST_SCOPE);
        else if (scope.equalsIgnoreCase("session"))
            bean = pageContext.getAttribute(name, PageContext.SESSION_SCOPE);
        else if (scope.equalsIgnoreCase("application"))
            bean =
                    pageContext.getAttribute(name, PageContext.APPLICATION_SCOPE);
        else {
            JspTagException e = new JspTagException("Invalid scope " + scope);
            throw e;
        }
        return (bean);

    }

    /**
     * Name of the bean that contains the rowset to process.
     */
    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }
    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public void setName(String name) {
        _name = name;
    }

    public void setScope(String scope) {
        _scope = scope;
    }

    public void setLoop(boolean shouldLoop) {
        _shouldLoop = shouldLoop;
    }

    public ResultSet getResultSet() {
        return _rset;
    }

    public int doStartTag() throws JspTagException {

        // reset body's content, otherwise it could result in an error if container is pooling
        // tag handlers (see bug 26863 for more details)
        super.bodyContent = null;

        try {
            // if name property given, retrieve resultset from context
            // may also be a related class, like RowSet
            if (_name!=null) {
                _rset = (ResultSet) lookup(pageContext, _name, _scope);
            }
            else {
                StatementTag _stmtTag =
                        (StatementTag) findAncestorWithClass(this,
                                Class.forName("tags.dbtags.statement.StatementTag"));
                _rset = _stmtTag.executeQuery();
            }

            pageContext.setAttribute(getId(), _rset);




            if (_shouldLoop == false) {
                return EVAL_BODY_TAG;
            }

            if(startRow == UNASSIGNED) {
                keepGoing = _rset.next(); // point to first row initially
            }
            else {
                if(startRow < 1)
                    startRow = 1;

                keepGoing = _rset.absolute(startRow);
            }

//            if (_rset.next() == false) {
//                setTotalRowCount(_rowCount);
//                return SKIP_BODY;
//            }
//


        } catch (ClassNotFoundException e) {
            throw new JspTagException(e.toString());
        } catch (SQLException e) {
            throw new JspTagException(e.toString());
        }

//        return EVAL_BODY_TAG;

        setIsFwd(_isFwd);
        if (startRow!=1)
              _isBack=true;
        setIsBack(_isBack);



        if(keepGoing){
            ++_rowCount;
            setTotalRowCount(_rowCount);
            return EVAL_BODY_TAG;
        }
        else
            setTotalRowCount(_rowCount);
            setTotalRowCountAll(_rowCountAll);
            return SKIP_BODY;

    }

    public int doEndTag() throws JspTagException{
        pageContext.removeAttribute(getId());
        try {
            if (getBodyContent() != null && getPreviousOut() != null) {
                getPreviousOut().write(getBodyContent().getString());
            }
        } catch (IOException e) {
            throw new JspTagException(e.toString());
        } finally {
            try {
                _rset.close();
            }
            catch (SQLException e) {
                // it's not fatal if the result set cannot be closed
                e.printStackTrace();
            }
        }

        // we have to call this guy manually now
        // with the spec clarification


//        double x1=_rowCountAll/(endRow-startRow+1);
//        int x2=_rowCountAll%(endRow-startRow+1);
//        _pageCount=((int)(x2==0?Math.floor(x1):(Math.floor(x1)+1)));
//        setPageCount(_pageCount);

        int x1=(int) Math.floor(_rowCountAll/(endRow-startRow+1));
        int x2=_rowCountAll%(endRow-startRow+1);
        _pageCount=x2==0?x1:x1+1;
        setPageCount(_pageCount);

        release();

        return EVAL_PAGE;
    }

    public int doAfterBody() throws JspTagException{

        if (_shouldLoop == false) {
            return EVAL_PAGE;
        }

//        try {
//            if (_rset.next() == true) {
//                ++_rowCount;
//                setTotalRowCount(_rowCount);
//                return EVAL_BODY_TAG;
//            }
//        } catch (SQLException e) {
//            throw new JspTagException(e.toString());
//        }

        try {
            if(endRow == UNASSIGNED) {
                if(_rset.isLast()) {
                    _rset.beforeFirst();
//               writeBodyContent();
                    return SKIP_BODY;
                }
            }
            else {
                if(_rset.getRow() == endRow || _rset.isLast()) {
                    int rsetRecno=_rset.getRow();
                    if(_rset.last())
                        _rowCountAll=_rset.getRow();
                    else
                       _rowCountAll=0;

                    _isFwd=(rsetRecno==_rowCountAll)?false:true;
                    setIsFwd(_isFwd);
                    setTotalRowCountAll(_rowCountAll);
                    _rset.beforeFirst();
//               writeBodyContent();
                    return SKIP_BODY;
                }
            }
            _rset.next();
            ++_rowCount;
            setTotalRowCount(_rowCount);
            return EVAL_BODY_TAG;
        }
        catch(SQLException e) {
            throw new JspTagException(e.toString());
        }
//        setTotalRowCount(_rowCount);
//        return EVAL_PAGE;
    }

    public void release() {
        _statement = null;
        _rset = null;
        _shouldLoop = true;
        _name = null;
        _scope = null;
        _rowCount = 0;
        _rowCountAll = 0;
        _isBack=false;
        _isFwd=false;
        _pageCount=0;

    }

    protected void setTotalRowCount(int rowCount) {
        pageContext.setAttribute("tags.dbtags.resultset.rowcount",
                new Integer(rowCount));
    }

    private void setTotalRowCountAll(int rowCountAll) {
        pageContext.setAttribute("tags.dbtags.resultset.rowcountall",
                new Integer(rowCountAll));
    }


    private void setIsFwd(boolean isFwd) {
        pageContext.setAttribute("tags.dbtags.resultset.isfwd",
                new Boolean(isFwd));
    }

    private void setIsBack(boolean isBack) {
        pageContext.setAttribute("tags.dbtags.resultset.isback",
                new Boolean(isBack));
    }

    private void setPageCount(int pageCount) {
        pageContext.setAttribute("tags.dbtags.resultset.pagecount",
                new Integer(pageCount));
    }

}
