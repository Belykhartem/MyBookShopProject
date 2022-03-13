
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
 * <p>JSP tag url, sets the database URL for the enclosing connection
 * tag.  The URL is read from the indicated initParameter, if the
 * parameter is set, or from the body of the tag if it is not.</p>
 * 
 * <p>JSP Tag Lib Descriptor
 * <pre>
 * &lt;name>url&lt;/name>
 * &lt;tagclass>tags.dbtags.connection.DatabaseURLTag&lt;/tagclass>
 * &lt;bodycontent>JSP&lt;/bodycontent>
 * &lt;info>Sets the database URL of the enclosing connection tag
 * according to the initParameter, if specified, or the body of
 * the tag.  The tag body will be trimmed.&lt;/info>
 *   &lt;attribute>
 *     &lt;name>initParameter&lt;/name>
 *     &lt;required>false&lt;/required>
 *     &lt;rtexprvalue>false&lt;/rtexprvalue>
 *   &lt;/attribute>
 * </pre>
 * 
 * @author Morgan Delagrange
 * @see ConnectionTag
 */
public class DatabaseURLTag extends BodyTagSupport {
    
  private String _url = null;

  /**
   * The name of the init parameter containing the database URL.
   * 
   * @param paramName init parameter name
   */
  public void setInitParameter(String paramName) {
    _url = pageContext.getServletContext().getInitParameter(paramName);
  }

  public int doEndTag() throws JspTagException {
    try { 
      ConnectionTag connTag = 
        (ConnectionTag) findAncestorWithClass(this, Class.forName("tags.dbtags.connection.ConnectionTag"));
      
      if (_url == null) {
        _url = getBodyContent().getString().trim();
      }
      connTag.setUrl(_url); 
    } 
     catch (ClassNotFoundException e) {
      throw new JspTagException(e.toString());
    }
    return EVAL_PAGE;
  }

  public void release() {
    _url = null;
  }
  
}
