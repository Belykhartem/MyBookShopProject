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

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * <p>Prints out the number of rows retrieved from the database.
 * It can be used inside a ResultSet tag to provide a running
 * count of rows retreived, or after the ResultSet tag to
 * display the total number.  Using the tag before the ResultSet
 * will
 * produce an error.</p>
 * 
 * <p>JSP Tag Lib Descriptor
 * <pre>
 * &lt;name>rowCount&lt;/name>
 * &lt;tagclass>tags.dbtags.statement.RowCountTag&lt;/tagclass>
 * &lt;bodycontent>empty&lt;/bodycontent>
 * &lt;info>Prints out the number of rows retrieved from the database.
 * It can be used inside a ResultSet tag to provide a running
 * count of rows retreived, or after the ResultSet tag to
 * display the total number.  Using the tag before the ResultSet
 *  will
 * produce an error.&lt;/info>
 * </pre>
 * 
 * @author Morgan Delagrange
 */
public class RowCountTag extends TagSupport {

  public int doStartTag() throws JspTagException {

    Integer integer = 
      (Integer) pageContext.getAttribute("tags.dbtags.resultset.rowcount");

    if (integer == null) {
      throw new JspTagException("rowCount tag must be used inside or after a ResultSet tag.");
    }

    try {
      pageContext.getOut().write(integer.toString());
    } catch (IOException e) {
      throw new JspTagException(e.toString());
    }

    return SKIP_BODY;
  }

}
