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
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;

import javax.servlet.jsp.JspTagException;

/**
 * Base tag for getDate, getTime, and getTimestamp
 * 
 * @author Morgan Delagrange
 * @author Marius Scurtescu
 */
public class BaseDateTimeGetterTag extends BaseGetterTag {

  protected static HashMap _mapDateTimeStyle = new HashMap (4);
  protected String _format = null;

  static
  {
    _mapDateTimeStyle.put ("FULL", new Integer (DateFormat.FULL));
    _mapDateTimeStyle.put ("LONG", new Integer (DateFormat.LONG));
    _mapDateTimeStyle.put ("MEDIUM", new Integer (DateFormat.MEDIUM));
    _mapDateTimeStyle.put ("SHORT", new Integer (DateFormat.SHORT));
  }

  public void setFormat (String strFormat) {
    _format = strFormat;
  }

  /**
   * Returns a formatted Date as a String, or null if the 
   * database row was null
   * 
   * @param position position of the column in the ResultSet
   * @param nType    one of the following java.sql.Types: DATE, TIME, TIMESTAMP
   * @return a formatted String, or null
   * @exception JspTagException
   */
  protected String getDateAsString(int position, int nType) throws JspTagException {

    ResultSet rset = getResultSet();

    String string = null;
    try {

      switch (nType) {
        case Types.DATE:
          {
            java.sql.Date date = rset.getDate (position);

            if (date != null) {
              if (_format == null)
                return date.toString ();

              DateFormat fmt = getDateFormat (nType, _format, getLocale (_locale));

              string = fmt.format (date);
            }
            break;
          }
        case Types.TIME:
          {
            java.sql.Time time = rset.getTime (position);

            if (time != null) {
              if (_format == null)
                return time.toString ();

              DateFormat fmt = getDateFormat (nType, _format, getLocale (_locale));

              string = fmt.format (time);
            }
            break;
          }
        case Types.TIMESTAMP:
          {
            java.sql.Timestamp times = rset.getTimestamp (position);

            if (times != null) {
              if (_format == null)
                return times.toString ();

              DateFormat fmt = getDateFormat (nType, _format, getLocale (_locale));

              string = fmt.format (times);
            }
            break;
          }
        default:
          throw new JspTagException();
      }
    } catch (SQLException e) {
      throw new JspTagException(e.toString());
    }

    return string;

  }

  private DateFormat getDateFormat (int nType, String strFormat, Locale loc) throws JspTagException
  {
    switch (nType) {
      case Types.DATE:
        {
          if (!_mapDateTimeStyle.containsKey (strFormat))
            return new SimpleDateFormat (strFormat, loc);

          Integer iDateStyle = (Integer)_mapDateTimeStyle.get (strFormat);
          return DateFormat.getDateInstance (iDateStyle.intValue(), loc);
        }

      case Types.TIME:
        {
          if (!_mapDateTimeStyle.containsKey (strFormat))
            return new SimpleDateFormat (strFormat, loc);

          Integer iTimeStyle = (Integer)_mapDateTimeStyle.get (strFormat);
          return DateFormat.getTimeInstance (iTimeStyle.intValue(), loc);
        }

      case Types.TIMESTAMP:
        {
          Integer iDateStyle, iTimeStyle;
          int pos = strFormat.indexOf (",");

          if (pos == -1) {
            if (!_mapDateTimeStyle.containsKey (strFormat))
              return new SimpleDateFormat (strFormat, loc);

            iDateStyle = iTimeStyle = (Integer)_mapDateTimeStyle.get (strFormat);
          } else {
            String strDateStyle = strFormat.substring (0, pos);
            String strTimeStyle = strFormat.substring (pos + 1);

            if (!_mapDateTimeStyle.containsKey (strDateStyle) || !_mapDateTimeStyle.containsKey (strTimeStyle))
              return new SimpleDateFormat (strFormat, loc);

            iDateStyle = (Integer)_mapDateTimeStyle.get (strDateStyle);
            iTimeStyle = (Integer)_mapDateTimeStyle.get (strTimeStyle);
          }

          return DateFormat.getDateTimeInstance (iDateStyle.intValue (), iTimeStyle.intValue (), loc);
        }

      default:
        throw new JspTagException ("Not a Date/Time JDBC type: " + nType);
    }
  }


}
