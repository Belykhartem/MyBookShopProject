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
 * <p>JSP tag userid, sets the database user id for the
 * enclosing connection tag.  This tag is optional if the user name has
 * been encoded inside the database URL, or if the database does not
 * require a user name.  The user name is read from the indicated
 * initParameter, if the attribute is set, or from the body of the tag
 * if it is not.  The tag body <i>will</i> be trimmed.</p>
 * 
 * <p>JSP Tag Lib Descriptor
 * <pre>
 * &lt;name>userId&lt;/name>
 * &lt;tagclass>tags.dbtags.connection.UserIdTag&lt;/tagclass>
 * &lt;bodycontent>JSP&lt;/bodycontent>
 * &lt;info>JSP tag userid, sets the database user id for the
 * enclosing connection tag.  This tag is optional if the user name has
 * been encoded inside the database URL, or if the database does not
 * require a user name.  The user name is read from the indicated
 * initParameter, if the attribute is set, or from the body of the tag
 * if it is not.  The tag body will be trimmed.&lt;/info>
 *   &lt;attribute>
 *     &lt;name>initParameter&lt;/name>
 *     &lt;required>false&lt;/required>
 *     &lt;rtexprvalue>false&lt;/rtexprvalue>
 *   &lt;/attribute>
 * </pre>
 * 
 * @author Morgan Delagrange
 * @see ConnectionTag
 * @see DatabaseURLTag
 */
public class UserIdTag extends BodyTagSupport {
    
  private String _userId = null;

  /**
   * The name of the init parameter containing the database user id.
   * 
   * @param paramName database user id
   */
  public void setInitParameter(String paramName) {
    _userId = pageContext.getServletContext().getInitParameter(paramName);
  }

  public int doEndTag() throws JspTagException{
    try { 
      ConnectionTag connTag = 
        (ConnectionTag) findAncestorWithClass(this, Class.forName("tags.dbtags.connection.ConnectionTag"));
      
      if (_userId == null) {
        _userId = getBodyContent().getString().trim();
      }
      
      connTag.setUserId(_userId); 
    } 
     catch (ClassNotFoundException e) {
      throw new JspTagException(e.toString());
    }
    return EVAL_PAGE;
  }

  public void release() {
    _userId = null;
  }
  
}
