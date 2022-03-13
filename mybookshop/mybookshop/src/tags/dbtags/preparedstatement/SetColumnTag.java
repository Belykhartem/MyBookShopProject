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

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.jsp.JspTagException;

/**
 * <p>Setter for the enclosing preparedstatement tag.  Set the 
 * value inside the tag body.  Body content <i>will not</i>
 * be trimmed.</p>
 * <p>JSP Tag Lib Descriptor
 * <pre>
 * &lt;name>setColumn&lt;/name>
 * &lt;tagclass>tags.dbtags.preparedstatement.SetColumnTag&lt;/tagclass>
 * &lt;bodycontent>JSP&lt;/bodycontent>
 * &lt;info>Setter for the enclosing preparedstatement tag.  Set the 
 * value inside the tag body.  Body content <i>will not</i>
 * be trimmed.&lt;/info>
 *   &lt;attribute>
 *     &lt;name>position&lt;/name>
 *     &lt;required>true&lt;/required>
 *     &lt;rtexprvalue>false&lt;/rtexprvalue>
 *   &lt;/attribute>
 * </pre>
 * 
 * @author Morgan Delagrange
 * @see tags.dbtags.preparedstatement.PreparedStatementImplTag
 */
public class SetColumnTag extends BaseSetterBodyTag {

  public int doEndTag() throws JspTagException {

    PreparedStatement statement = getPreparedStatement();

    try {
      String string = null;

      if (_attributeName == null) {
        string = getBodyContent().getString();
      } else {
        string = (String) getAttribute(_attributeName);
      }

      statement.setString(_position,string);

    } catch (SQLException e) {
      throw new JspTagException(e.toString());
    }
    return EVAL_PAGE;
  }

}
