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
package tags.dbtags.statement;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * <p>JSP tag statement.  According to the TEI, the java.sql.Statement
 * object specified in the "id" attribute is available within the scope
 * of the statement tags.  </p>
 *
 * <p>JSP Tag Lib Descriptor
 * <pre>
 * &lt;name>statement&lt;/name>
 * &lt;tagclass>tags.dbtags.statement.StatementImplTag&lt;/tagclass>
 * &lt;teiclass>tags.dbtags.connection.StatementTEI&lt;/teiclass>
 * &lt;bodycontent>JSP&lt;/bodycontent>
 * &lt;info>JSP tag statement, uses the enclosed query and resultset/execute
 * tags to perform a database operation.&lt;/info>
 *   &lt;attribute>
 *     &lt;name>id&lt;/name>
 *     &lt;required>true&lt;/required>
 *     &lt;rtexprvalue>false&lt;/rtexprvalue>
 *   &lt;/attribute>
 *   &lt;attribute>
 *     &lt;name>conn&lt;/name>
 *     &lt;required>true&lt;/required>
 *     &lt;rtexprvalue>false&lt;/rtexprvalue>
 *   &lt;/attribute>
 * </pre>
 *
 * @author Morgan Delagrange
 */
public class StatementImplTag extends BodyTagSupport implements StatementTag{

  private Statement _statement = null;
  private String _query        = null;
  private String _connId       = null;

  // all the public methods are Javadoced
  // in their interfaces

  public void setQuery(String query) {
    _query = query;
  }

  public void setConn(String connId) {
    _connId = connId;
  }

  public void executeUpdate() throws SQLException {
    _statement.executeUpdate(_query);
  }

  public ResultSet executeQuery() throws SQLException {
    return _statement.executeQuery(_query);
  }

  public int doStartTag() throws JspTagException {
    if (_connId == null) {
      throw new JspTagException("Connection id has not been set.");
    }

    try {
      Connection conn = (Connection)pageContext.findAttribute(_connId);
      if(conn == null) {
        throw new JspTagException("There is no such connection'"+_connId+"'");
      }
      _statement = createStatement ( conn );
      pageContext.setAttribute(getId(), _statement);
    } catch (SQLException e) {
      throw new JspTagException(e.toString());
    }

    return EVAL_BODY_TAG;
  }


  /**
   * Let subclass redefine how to create the statement
   */
  protected Statement createStatement ( Connection theConnection ) throws SQLException
  {
    return theConnection.createStatement(
                                ResultSet.TYPE_SCROLL_INSENSITIVE,
                                ResultSet.CONCUR_READ_ONLY);
  }


  public int doAfterBody() throws JspTagException
  {
      // Automatically trim the contents of this tag
      try {
        getPreviousOut().write(getBodyContent().getString().trim());
      } catch (IOException e) {
        throw new JspTagException(e.toString());
      }
      return EVAL_PAGE;
  }

  public int doEndTag() {
    pageContext.removeAttribute(getId());

    try {
      _statement.close();
    } catch (SQLException e) {
      // it's not a fatal error if we can't close the statement
      e.printStackTrace();
    }

    return EVAL_PAGE;
  }

  public void release() {
    _connId=null;
    _statement = null;
    _query=null;
  }

}
