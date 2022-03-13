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

package tags.dbtags.connection;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.jsp.tagext.TagSupport;

/**
 * <p>JSP tag closeconnection, used to close the
 * specified java.sql.Connection.<p>
 * 
 * <p>JSP Tag Lib Descriptor
 * <pre>
 * &lt;name>closeConnection&lt;/name>
 * &lt;tagclass>tags.dbtags.connection.CloseConnectionTag&lt;/tagclass>
 * &lt;bodycontent>empty&lt;/bodycontent>
 * &lt;info>Close the specified connection.  The "conn" attribute is the name of a
 * connection object in the page context.&lt;/info>
 *   &lt;attribute>
 *     &lt;name>conn&lt;/name>
 *     &lt;required>true&lt;/required>
 *     &lt;rtexprvalue>false&lt;/rtexprvalue>
 *   &lt;/attribute>
 * </pre>
 * 
 * @author Morgan Delagrange
 * @see ConnectionTag
 */
public class CloseConnectionTag extends TagSupport {

  private String _connId = null;

  /**
   * The "conn" attribute is the name of a
   * page context object containing a
   * java.sql.Connection.
   * 
   * @param connectionId
   *               attribute name of the java.sql.Connection to close.
   * @see ConnectionTag
   */
  public void setConn(String connectionId) {
    _connId = connectionId;
  }
  
  public int doStartTag() {

    try {
      Connection conn = (Connection)pageContext.getAttribute(_connId);
      conn.close();
      conn = null;
    } catch (SQLException e) {
      // failing to close a connection is not fatal
      e.printStackTrace();
    }
    
    return EVAL_BODY_INCLUDE;
  }

  public void release() {
    _connId = null;
  }
    
}
