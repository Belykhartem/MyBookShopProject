
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
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Locale;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Base class for all the getter tags in the resultset package.
 *
 * @author Morgan Delagrange
 * @author Marius Scurtescu
 */
public class BaseGetterTag extends TagSupport {

  // subclasses should use getPosition to access
  // the column via getString(int) etc.
  private int _position = -1;
  private String _name = null;

  protected String _attributeName = null;
  protected String _scope = "page";
  protected ResultSetTag _tag = null;
  protected ResultSetMetaData _metaData = null;
  protected String _locale = null;

  public void setLocale (String strLocale)
  {
    _locale = strLocale;
  }

  /**
   * Sets the column number of the result set.
   *
   * @param position column index
   */
  public void setPosition(int position) {
    _position = position;
  }

  /**
   * Gets the column number for the result set.
   *
   * @return column position (1,2,3,etc.)
   */
  public int getPosition() throws JspTagException{
    // if the column was set according to its name,
    // figure out its position
    if (_position == -1) {
      _position = getColumnNumber(_name);
    }
    return _position;
  }

  /**
   * Sets the column number of the result set.
   *
   * @param position column index
   */
  public void setPosition(String strPosition) throws JspTagException
  {
    try {
      _position = Integer.parseInt (strPosition);
    } catch (NumberFormatException ex) {
      throw new JspTagException ("The 'position' attribute must be an int: " + ex.getMessage());
    }
  }

  /**
   * Sets the column name if the result set.
   *
   * @param strName The column name.
   */
  public void setColName (String strName)
  {
    _name = strName;
  }

  /**
   * Name of the attribute.
   *
   * @param attributeName
   *               attribute name
   */
  public void setTo(String attributeName) {
    _attributeName = attributeName;
  }

  /**
   * Scope of the attribute.
   *
   * @param scope  scope (page | request | session | application)
   */
  public void setScope(String scope) {
    _scope = scope;
  }

  /**
   * Set an attribute to the specified scope.
   *
   * @param name   name of the attribute
   * @param object the attribute
   * @param scope  attribute scope (page | request | session | application)
   */
  protected void setAttribute(String name, Object object, String scope) {
    if (scope.equals("request")) {
      pageContext.setAttribute(name, object, PageContext.REQUEST_SCOPE);
    } else if (scope.equals("application")) {
      pageContext.setAttribute(name, object, PageContext.APPLICATION_SCOPE);
    } else if (scope.equals("session")) {
      pageContext.setAttribute(name, object, PageContext.SESSION_SCOPE);
    } else {
      pageContext.setAttribute(name, object);
    }
  }

  /**
   * Get the ResultSet object from the enclosing resultset tag
   *
   * @return ResultSet of the resultset tag
   */
  protected ResultSet getResultSet() throws JspTagException {
    if (_tag == null) {
      _tag = getResultSetTag();
    }
    return _tag.getResultSet();
  }

  /**
   * Get the MetaData object for this result set
   *
   * @return Meta data object
   * @exception JspTagException
   */
  protected ResultSetMetaData getMetaData() throws JspTagException {
    if (_metaData == null) {

      if (_tag == null) {
        _tag = getResultSetTag();
      }

      try {
        _metaData = _tag.getResultSet().getMetaData();
      } catch (SQLException e) {
        throw new JspTagException(e.toString());
      }
    }
    return _metaData;
  }

  /**
   * Get the parent result set tag
   *
   * @return ResultSetTag
   * @exception JspTagException
   */
  protected ResultSetTag getResultSetTag() throws JspTagException{
    try {
      ResultSetTag rsetTag =
      (ResultSetTag) findAncestorWithClass(this, Class.forName("tags.dbtags.resultset.ResultSetTag"));
      return rsetTag;
    } catch (ClassNotFoundException e) {
      throw new JspTagException(e.toString());
    }
  }

  // get the position for a named column
  // needed for the metadata methods
  private int getColumnNumber (String strName)
  throws JspTagException {

    ResultSetMetaData meta = getMetaData();

    try {
      int cntColumn = meta.getColumnCount ();

      for (int i = 1; i <= cntColumn; i++) {
        if (strName.equalsIgnoreCase (meta.getColumnName (i))) {
          return i;
        }
      }
    } catch (SQLException e) {
      throw new JspTagException(e.toString());
    }

    throw new JspTagException("Could not find column named " + strName);

  }


  /**
   * Create a Locale for the formatting of dates, times,
   * and numbers.
   *
   * @param strLocale
   * @return the indicated Locale
   */
  protected Locale getLocale (String strLocale)
  {
    if (strLocale == null)
      return Locale.getDefault ();

    int pos1 = strLocale.indexOf ("_");

    if (pos1 == -1)
      return new Locale (strLocale, "");

    String strLanguage = strLocale.substring (0, pos1);
    int pos2 = strLocale.indexOf ("_", pos1 + 1);

    if (pos2 == -1)
      return new Locale (strLanguage, strLocale.substring (pos1 + 1));

    return new Locale (strLanguage, strLocale.substring (pos1 + 1, pos2), strLocale.substring (pos2 + 1));
  }


  /**
   * If I understand the new JSP spec correctly, release()
   * is NOT called between invocations of a cached taglib,
   * so I guess we do it manually or write a new method.
   * 
   * @return EVAL_PAGE constant
   */
  public int doEndTag() {
    release();
    return EVAL_PAGE;
  }

  public void release() {
    _position = -1;
    _attributeName = null;
    _name = null;
    _scope = "page";
    _tag = null;
    _metaData = null;
    _locale = null;
  }
}
