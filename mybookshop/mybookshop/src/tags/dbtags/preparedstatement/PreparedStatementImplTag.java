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
package tags.dbtags.preparedstatement;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import tags.dbtags.statement.StatementTag;

/**
 * <p>JSP tag preparedstatement.  According to the TEI, the preparedstatemnt
 * object specified in the "id" attribute is available within the scope
 * of the preparedstatement tags.  However, this is not quite the case.
 * Technically, the object is not added to the page context until after
 * the query tag is executed, because PreparedStatement objects cannot
 * be instantiated without a query.</p>
 *
 * <p>JSP Tag Lib Descriptor
 * <pre>
 * &lt;name>preparedStatement&lt;/name>
 * &lt;tagclass>tags.dbtags.preparedstatement.PreparedStatementImplTag&lt;/tagclass>
 * &lt;teiclass>tags.dbtags.connection.PreparedStatementTEI&lt;/teiclass>
 * &lt;bodycontent>JSP&lt;/bodycontent>
 * &lt;info>JSP tag preparedstatement, used the enclosed query,
 * resultset/execute and set* tags to perform a database operation.&lt;/info>
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
public class PreparedStatementImplTag extends BodyTagSupport
    implements StatementTag {

  private String _connId               = null;
  private PreparedStatement _statement = null;

  public void setQuery(String query) throws SQLException, JspTagException {
    Connection conn = (Connection)pageContext.findAttribute(_connId);
    if(conn == null) {
      throw new JspTagException("There is no such connection'"+_connId+"'");
    }
    _statement = createStatement ( conn, query );
    pageContext.setAttribute(getId(), _statement);
  }

  /**
   * Let subclass redefine how to create the statement
   */
  protected PreparedStatement createStatement ( Connection theConnection, String theQuery ) throws SQLException
  {
    return theConnection.prepareStatement ( theQuery );
  }


  /**
   *
   * @param connId
   */
  public void setConn(String connId) {
    _connId = connId;
  }

  /**
   * Get the PreparedStatement contained within this tag
   *
   * @return the PreparedStatement
   */
  public PreparedStatement getPreparedStatement() {
    return _statement;
  }

  public void executeUpdate() throws SQLException {
    _statement.executeUpdate();
  }

  public ResultSet executeQuery() throws SQLException {
    return _statement.executeQuery();
  }

  public int doStartTag() throws JspTagException {
    if (_connId == null) {
      throw new JspTagException("Connection id has not been set.");
    }

    // _statement is set by the setQuery tag, not by the start tag.
    // Unlike Statements, PreparedStatements cannot be instantiated
    // without a query.

    return EVAL_BODY_TAG;
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
      // not a fatal error
      e.printStackTrace();
    }

    return EVAL_PAGE;
  }

  public void release() {
    _connId = null;
    _statement = null;
  }
}
