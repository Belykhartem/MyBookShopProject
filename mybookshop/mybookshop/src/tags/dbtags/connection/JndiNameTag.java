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

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * <p>JSP tag jndiname, sets the jndi named javax.sql.DataSource for the
 * enclosing connection tag.  The jndiname is read from the indicated
 * initParameter, if the parameter is set, or from the body of the tag
 * if it is not.</p>
 *
 * <p>JSP Tag Lib Descriptor
 * <pre>
 * &lt;name>jndiName&lt;/name>
 * &lt;tagclass>tags.dbtags.connection.JndiNameTag&lt;/tagclass>
 * &lt;bodycontent>JSP&lt;/bodycontent>
 * &lt;info>Sets the jndi named javax.sql.DataSource for the enclosing
 * connection tag according to the initParameter, if specified, or the
 * body of the tag.  The tag body will be trimmed.&lt;/info>
 *   &lt;attribute>
 *     &lt;name>initParameter&lt;/name>
 *     &lt;required>false&lt;/required>
 *     &lt;rtexprvalue>false&lt;/rtexprvalue>
 *   &lt;/attribute>
 * </pre>
 *
 * @author Rich Catlett
 * @see ConnectionTag
 */
public class JndiNameTag extends BodyTagSupport {

  private String _jndiName = null;

  /**
   * The name of the init parameter containing the jndinameed DataSource.
   *
   * @param paramName jndiname for Datasource
   */
  public void setInitParameter(String paramName) {
    _jndiName = pageContext.getServletContext().getInitParameter(paramName);
  }

  public int doEndTag() throws JspTagException{
    try {
      ConnectionTag connTag =
        (ConnectionTag) findAncestorWithClass(this, Class.forName("tags.dbtags.connection.ConnectionTag"));
      if (_jndiName == null) {
        _jndiName = getBodyContent().getString().trim();
      }

      connTag.setJndiName(_jndiName);
    }
     catch (ClassNotFoundException e) {
      throw new JspTagException(e.toString());
    }
    return EVAL_PAGE;
  }

  public void release() {
    _jndiName = null;
  }

}
