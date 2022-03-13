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

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * <p>Executes its body if the last getColumn tag received a null value
 * from the database.  You must be inside a resultset tag and there must
 * be a previous getColumn tag, or an error will be generated.</p>
 *
 * <p>The subclass WasNotNull sets the "value" property to false,
 * and therefore executes its tag body is the last column was 
 * <i>not</i> null.</p>  
 * 
 * <p>JSP Tag Lib Descriptor
 * <pre>
 * &lt;name>wasNull&lt;/name>
 * &lt;tagclass>tags.dbtags.resultset.WasNullTag&lt;/tagclass>
 * &lt;bodycontent>JSP&lt;/bodycontent>
 * &lt;info>Executes its body if the last getColumn tag received a null value
 * from the database.  You must be inside a resultset tag and there must
 * be a previous getColumn tag, or an error will be generated.&lt;/info>
 * </pre>
 * 
 * @author Morgan Delagrange
 * @see WasNotNullTag
 */
public class WasNullTag extends TagSupport {

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

  /**
   * Get the ResultSet object from the enclosing resultset tag
   * 
   * @return ResultSet of the resultset tag
   * @exception ClassNotFoundException
   */
  private ResultSet getResultSet() throws ClassNotFoundException {
    ResultSetTag rsetTag = 
      (ResultSetTag) findAncestorWithClass(this, Class.forName("tags.dbtags.resultset.ResultSetTag"));
    return rsetTag.getResultSet();
  }

  public int doStartTag() throws JspTagException {
    try {
      ResultSet rset = getResultSet();
      boolean wasNull = rset.wasNull();

      // evaluate the body only if wasNull matches the desired
      // value (true or false)
      if (wasNull == _value) {
        return EVAL_BODY_INCLUDE;
      } else {
        return SKIP_BODY;
      }
    } catch (ClassNotFoundException e) {
      throw new JspTagException(e.toString());
    } catch (SQLException e) {
      throw new JspTagException(e.toString());
    }
  }

  public void release() {
    _value = true;
  }
}

