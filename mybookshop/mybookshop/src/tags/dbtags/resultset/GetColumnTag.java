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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;

import javax.servlet.jsp.JspTagException;

/**
 * <p>Get the value of a database column as a String.</p>
 * 
 * <p>JSP Tag Lib Descriptor
 * <pre>
 * &lt;name>getString&lt;/name>
 * &lt;tagclass>tags.dbtags.resultset.GetStringTag&lt;/tagclass>
 * &lt;bodycontent>empty&lt;/bodycontent>
 * &lt;info>Gets the value, as a String, of a coulmn in the enclosing
 * resultset.  Either set the column number via the "position" attribute,
 * or set the column name with the "colName" attribute.
 * You can optionally set the value, as a String, to a serlvet attribute 
 * instead of the tag body with the "to" attribute.  The scope of the servlet
 * attribute is specified by the "scope" XML attribute (default = page).  Dates,
 * times, timestamps and numbers are output according to the JVM's defaults.&lt;/info>
 *   &lt;attribute>
 *     &lt;name>position&lt;/name>
 *     &lt;required>false&lt;/required>
 *     &lt;rtexprvalue>false&lt;/rtexprvalue>
 *   &lt;/attribute>
 *     &lt;attribute>
 *     &lt;name>colName&lt;/name>
 *     &lt;required>false&lt;/required>
 *     &lt;rtexprvalue>false&lt;/rtexprvalue>
 *   &lt;/attribute>
 *   &lt;attribute>
 *     &lt;name>to&lt;/name>
 *     &lt;required>false&lt;/required>
 *     &lt;rtexprvalue>false&lt;/rtexprvalue>
 *   &lt;/attribute>
 *     &lt;attribute>
 *     &lt;name>scope&lt;/name>
 *     &lt;required>false&lt;/required>
 *     &lt;rtexprvalue>false&lt;/rtexprvalue>
 *   &lt;/attribute>
 * </pre>
 * 
 * @author Morgan Delagrange
 */
public class GetColumnTag extends BaseGetterTag {
    
  public int doStartTag() throws JspTagException {
    try {
      int position = getPosition();
      
      ResultSet rset = getResultSet();
      
      // some complex datatypes, such as clobs,
      // require special handling
      ResultSetMetaData meta = getMetaData();
      String string = null;
      switch (meta.getColumnType(position)) {
      case (Types.CLOB):
        try {
          string = readClob(rset.getClob(position));
        } catch (IOException e) {
          throw new JspTagException(e.toString());
        } catch (SQLException e) {
          throw new JspTagException(e.toString());
        }
        break; 
      default:
        string = rset.getString(position);
      }
      
      // null results are often OK, in outer joins for example
      if (string == null) {
        return EVAL_BODY_INCLUDE;
      }

      if (_attributeName != null) {
        setAttribute(_attributeName, string, _scope);
      } else {
        pageContext.getOut().write(string);
      }
    } catch (SQLException e) {
      throw new JspTagException(e.toString());
    } catch (IOException e) {
      throw new JspTagException(e.toString());
    }
    
    return EVAL_BODY_INCLUDE;
  }

  private String readClob(Clob clob) throws IOException, SQLException {
    StringBuffer buffer = new StringBuffer();
    Reader reader = clob.getCharacterStream();
    BufferedReader buffReader = new BufferedReader(reader);
    String line = buffReader.readLine();
    while (line != null) {
      buffer.append(line);
      line = buffReader.readLine();
    }
    buffReader.close();
    return buffer.toString();
  }
}
