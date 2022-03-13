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
 * <p>JSP tag execute, executes the query for the enclosing statement or
 * preparedstatement tag.  
 
 * <p>Setting the "ignoreErrors" atttibute to true
 * will instruct the page to continue in the event of a SQLException,
 * otherwise by default exceptions will throw a JspTagException.</p>
 * 
 * <p>JSP Tag Lib Descriptor
 * <pre>
 * &lt;name>execute&lt;/name>
 * &lt;tagclass>tags.dbtags.statement.ExecuteTag&lt;/tagclass>
 * &lt;bodycontent>JSP&lt;/bodycontent>
 * &lt;info>Executes the query for the enclosing statement or
 * preparedstatement tag.&lt;/info>
 *   &lt;attribute>
 *     &lt;name>ignoreErrors&lt;/name>
 *     &lt;required>false&lt;/required>
 *     &lt;rtexprvalue>true&lt;/rtexprvalue>
 *   &lt;/attribute>
 * </pre>
 * 
 * @author Morgan Delagrange
 * @see StatementImplTag
 * @see QueryTag
 * @see tags.dbtags.preparedstatement.PreparedStatementImplTag
 */
public class ExecuteTag extends BodyTagSupport{
  
  boolean _ignoreErrors = false;

  public void setIgnoreErrors(boolean ignoreErrors) {
    _ignoreErrors = ignoreErrors;
  }

  public int doEndTag() throws JspTagException {
    try {
      StatementTag stmtTag = 
        (StatementTag) findAncestorWithClass(this, Class.forName("tags.dbtags.statement.StatementTag"));
      stmtTag.executeUpdate();
    } catch (ClassNotFoundException e) {
      throw new JspTagException(e.toString());
    } catch (SQLException e) {
      if (_ignoreErrors == false) {
        throw new JspTagException(e.toString());
      } else {
        // print out error rather than throw a fatal exception
        e.printStackTrace();
      }
    }
    
    return EVAL_PAGE;
  }
  
  public void release(){
    _ignoreErrors = false;
  }

}
