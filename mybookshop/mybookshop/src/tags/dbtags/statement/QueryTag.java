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

import java.sql.SQLException;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * <p>JSP tag query, sets the SQL query for the enclosing statement or
 * preparedstatement tag.  </p>
 * 
 * <p>JSP Tag Lib Descriptor
 * <pre>
 * &lt;name>query&lt;/name>
 * &lt;tagclass>tags.dbtags.statement.QueryTag&lt;/tagclass>
 * &lt;bodycontent>JSP&lt;/bodycontent>
 * &lt;info>Sets the SQL query for the enclosing statement or
 * preparedstatement tag.&lt;/info>
 * </pre>
 * 
 * @author Morgan Delagrange
 * @see StatementImplTag
 * @see tags.dbtags.preparedstatement.PreparedStatementImplTag
 */

public class QueryTag extends BodyTagSupport {

  public int doEndTag() throws JspTagException{
    try {
      StatementTag stmtTag = 
      (StatementTag) findAncestorWithClass(this, Class.forName("tags.dbtags.statement.StatementTag"));
      stmtTag.setQuery(getBodyContent().getString().trim()); 
    } catch (ClassNotFoundException e) {
      throw new JspTagException(e.toString());
    } catch (SQLException e) {
      throw new JspTagException(e.toString());
    }
    return EVAL_PAGE;
  }

}
