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
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

import javax.servlet.jsp.JspTagException;

/**
 * Translates any SQL number to a readable String.
 * 
 * <p>JSP Tag Lib Descriptor
 * <pre>
 *   &LT;tag>
 *     &LT;name>getNumber&LT;/name>
 *     &LT;tagclass>tags.dbtags.resultset.GetNumberTag&LT;/tagclass>
 *     &LT;teiclass>tags.dbtags.resultset.BaseGetterTEI&LT;/teiclass>
 *     &LT;bodycontent>empty&LT;/bodycontent>
 *     &LT;info>
 *     Similar to getColumn, but provides more precise control over
 *     number formatting.  
 *     
 *     The "format" attribute can be either a pattern as
 *     accepted by the DecimalFormat constructor or a style: "CURRENCY", 
 *     "PERCENT" or "NUMBER".  
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
 * </pre>
 * 
 * @author Morgan Delagrange
 * @author Marius Scurtescu
 */

public class GetNumberTag extends BaseGetterTag {

  protected String _format = null;

  public void setFormat (String strFormat) {
    _format = strFormat;
  }

  public int doStartTag() throws JspTagException {
    try {
      ResultSet rset = getResultSet();

      // some complex datatypes, such as clobs,
      // require special handling
      ResultSetMetaData meta = getMetaData();

      int position = getPosition();

      String string = null;
      switch (meta.getColumnType(position)) {
      case Types.REAL:
      case Types.FLOAT:
      case Types.DOUBLE:
      case Types.DECIMAL:
      case Types.NUMERIC:
        {
          double d = rset.getDouble (position);

          if (rset.wasNull() == false) {

            if (_format == null) {
              string = Double.toString (d);
            } else {
              NumberFormat fmt = getNumberFormat (_format, getLocale (_locale));
              string =  fmt.format (d);
            }
          }
          break;
        }
      case Types.TINYINT:
      case Types.SMALLINT:
      case Types.INTEGER:
      case Types.BIGINT:
        {
          long l = rset.getLong (position);

          if (rset.wasNull() == false) {
            if (_format == null) {
              string = Long.toString (l);
            } else {
              NumberFormat fmt = getNumberFormat (_format, getLocale (_locale));
              string = fmt.format (l);
            }
          }
          break;
        }
      default:
        throw new JspTagException("Column is not a recognized number type"); 
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

  private static NumberFormat getNumberFormat (String strFormat, Locale loc)
  {
    NumberFormat fmt;

    if (strFormat.equals ("CURRENCY"))
      fmt = NumberFormat.getCurrencyInstance (loc);
    else if (strFormat.equals ("PERCENT"))
      fmt = NumberFormat.getPercentInstance (loc);
    else if (strFormat.equals ("NUMBER"))
      fmt = NumberFormat.getNumberInstance (loc);
    else
      fmt = new DecimalFormat (strFormat, new DecimalFormatSymbols(loc));

    return fmt;
  }

  public void release() {
    super.release();
    _format = null;
  }
}
