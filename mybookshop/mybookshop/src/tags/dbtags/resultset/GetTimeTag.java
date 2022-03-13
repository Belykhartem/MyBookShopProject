
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

package tags.dbtags.resultset;

import java.io.IOException;
import java.sql.Types;

import javax.servlet.jsp.JspTagException;

/**
 * Translates a java.sql.Time to a readable String.
 * 
 * <p>JSP Tag Lib Descriptor
 * <pre>
 *   &LT;tag>
 *     &LT;name>getTime&LT;/name>
 *     &LT;tagclass>tags.dbtags.resultset.GetTimeTag&LT;/tagclass>
 *     &LT;teiclass>tags.dbtags.resultset.BaseGetterTEI&LT;/teiclass>
 *     &LT;bodycontent>empty&LT;/bodycontent>
 *     &LT;info>
 *     Similar to getColumn, but provides more precise control over
 *     java.sql.Time formatting.  
 *     
 *     The "format" attribute can be either a pattern as
 *     accepted by SimpleDateFormat or a style: "FULL",
 *     "LONG", "MEDIUM" or "SHORT".  
 *     
 *     The "locale" attribute can have one to three 
 *     components as accepted by the Locale constructor: language,
 *     country and variant. They are separated by "_".
 *     
 *     If neither the format nor locale attribute is set, output should be
 *     identical to getColumn.
 *     &LT;/info>
 *     &LT;attribute>
 *       &LT;name>position&LT;/name>
 *       &LT;required>false&LT;/required>
 *       &LT;rtexprvalue>false&LT;/rtexprvalue>
 *     &LT;/attribute>
 *     &LT;attribute>
 *       &LT;name>colName&LT;/name>
 *       &LT;required>false&LT;/required>
 *       &LT;rtexprvalue>false&LT;/rtexprvalue>
 *     &LT;/attribute>
 *     &LT;attribute>
 *       &LT;name>to&LT;/name>
 *       &LT;required>false&LT;/required>
 *       &LT;rtexprvalue>false&LT;/rtexprvalue>
 *     &LT;/attribute>
 *     &LT;attribute>
 *       &LT;name>scope&LT;/name>
 *       &LT;required>false&LT;/required>
 *       &LT;rtexprvalue>false&LT;/rtexprvalue>
 *     &LT;/attribute>
 *     &LT;attribute>
 *       &LT;name>locale&LT;/name>
 *       &LT;required>false&LT;/required>
 *       &LT;rtexprvalue>true&LT;/rtexprvalue>
 *     &LT;/attribute>
 *     &LT;attribute>
 *       &LT;name>format&LT;/name>
 *       &LT;required>false&LT;/required>
 *       &LT;rtexprvalue>true&LT;/rtexprvalue>
 *     &LT;/attribute>
 *   &LT;/tag>
 * 
 * </pre>
 * 
 * @author Morgan Delagrange
 * @author Marius Scurtescu
 */
public class GetTimeTag extends BaseDateTimeGetterTag {

  public int doStartTag() throws JspTagException {

    try {
      int position = getPosition();

      String time = getDateAsString(position,Types.TIME);

      // null results are often OK, in outer joins for example
      if (time == null) {
        return EVAL_BODY_INCLUDE;
      }

      if (_attributeName != null) {
        setAttribute(_attributeName, time, _scope);
      } else {
        pageContext.getOut().write(time);
      }
    } catch (IOException e) {
      throw new JspTagException(e.toString());
    }

    return EVAL_BODY_INCLUDE;

  }

}
