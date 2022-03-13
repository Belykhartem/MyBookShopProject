
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

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * <p>Executes its body if the last ResultSet tag received 0 rows
 * from the database.  You must be after a ResultSet tag and inside
 * a StatementTag or PreparedStatementTag, or an error will be generated.</p>
 *
 * <p>The subclass WasNotEmpty sets the "value" property to false,
 * and therefore executes its tag body is the ResultSet contained
 * <i>greater than</i> 0 rows.  
 * 
 * <p>JSP Tag Lib Descriptor
 * <pre>
 * &lt;name>wasEmpty&lt;/name>
 * &lt;tagclass>tags.dbtags.statement.WasEmptyTag&lt;/tagclass>
 * &lt;bodycontent>JSP&lt;/bodycontent>
 * &lt;info>
 * Executes its body if the last ResultSet tag received 0 rows
 * from the database.  You must be after a ResultSet tag and inside
 * a StatementTag or PreparedStatementTag, or an error will be generated.
 * &lt;/info>
 * </pre>
 * 
 * @author Morgan Delagrange
 * @see WasNotEmptyTag
 */
public class WasEmptyTag extends TagSupport {

  private boolean _value = true;

  /**
   * Sets the necessary boolean value in order to
   * execute the tag body.
   * 
   * @param value
   */
  public void setValue(boolean value) {
    _value = value;
  }

  public int doStartTag() throws JspTagException {

    Integer integer = 
      (Integer) pageContext.getAttribute("tags.dbtags.resultset.rowcount");
    
    if (integer == null) {
      throw new JspTagException("WasEmpty and WasNotEmpty tags must follow a ResultSet tag.");
    }

    int rowCount = integer.intValue();

    boolean wasEmpty = true;
    if (rowCount > 0) {
      wasEmpty = false;
    }

    // evaluate the body only if wasEmpty matches the desired
    // value (true or false)
    if (wasEmpty == _value) {
      return EVAL_BODY_INCLUDE;
    } else {
      return SKIP_BODY;
    }
  }

  public void release() {
    _value = true;
  }
}

