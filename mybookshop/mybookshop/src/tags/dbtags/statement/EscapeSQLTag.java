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

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * <p>JSP tag escapesql, replaces each single quote in the tag body
 * with a pair of single quotes.</p>
 * 
 * <p>JSP Tag Lib Descriptor
 * <pre>
 * &lt;name>escapeSql&lt;/name>
 * &lt;tagclass>tags.dbtags.statement.EscapeSQLTag&lt;/tagclass>
 * &lt;bodycontent>JSP&lt;/bodycontent>
 * &lt;info>Replaces each single quote in the tag body
 * with a pair of single quotes.  Body content will not be trimmed.&lt;/info>
 * </pre>
 * 
 * @author Morgan Delagrange
 */
public class EscapeSQLTag extends BodyTagSupport {
    
  public int doEndTag() throws JspTagException {
    String unescaped = getBodyContent().getString();
    char[] unescapedChars = new char[unescaped.length()];
    unescaped.getChars(0,unescaped.length(),unescapedChars,0);
    JspWriter output = pageContext.getOut();

    try {
      for (int i = 0; i < unescapedChars.length; ++i) {
        switch (unescapedChars[i]) {
        case ('\''):
          output.print('\'');
        default:
          output.print(unescapedChars[i]);
        }
      }
    } catch (IOException e) {
      throw new JspTagException(e.toString());
    }

    return EVAL_PAGE;
  }
  
}
