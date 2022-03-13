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

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.jsp.JspTagException;

/**
 * <p>Interface for StatementImplTag and PreparedStatementImplTag.
 * Used to mask the differences between statements and preparedstatements
 * in this taglib.</p>
 * 
 * @author Morgan Delagrange
 * @see StatementImplTag
 * @see tags.dbtags.preparedstatement.PreparedStatementImplTag
 * @see QueryTag
 * @see ExecuteTag
 * @see tags.dbtags.resultset.ResultSetTag
 */
public interface StatementTag {
  
  /**
   * SQL query to be executed in the statement
   * 
   * @param query  SQL query
   * @exception SQLException
   *                   throws an exception when a PreparedStatement cannot be created
   */
  public void setQuery(String query) throws SQLException, JspTagException;

  /**
   * The id of a page context attribute containing a java.sql.Connection
   * 
   * @param connId id of the Connection attribute
   * @see tags.dbtags.connection.ConnectionTag
   */
  public void setConn(String connId);

  /**
   * Execute a SQL insert, update or delete.
   * 
   * @exception SQLException
   */
  public void executeUpdate() throws SQLException;

  /**
   * Execute a SQL select
   * 
   * @return Resultset based on the {@link #setQuery query}
   * @exception SQLException
   */
  public ResultSet executeQuery() throws SQLException;

  
}
