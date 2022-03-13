
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
package tags.dbtags.preparedstatement;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Base class for all the setter tags without bodies 
 * in the preparedstatement package.
 * 
 * @author Morgan Delagrange
 */
public class BaseSetterTag extends TagSupport {
  
  protected int _position = 1;
  protected String _attributeName = null;
  
  /**
   * Sets the column number of the prepared statement.
   * 
   * @param position column index
   */
  public void setPosition(int position) {
    _position = position;
  }
  
  /**
   * Name of the page attribute that will be assigned
   * to the statement.
   * 
   * @param attributeName
   *               attribute name
   */
  public void setName(String attributeName) {
    _attributeName = attributeName;
  }
      
  /**
   * Gets the page attribute for the tag
   * 
   * @param name   name of the attribute
   * @return the page attribute
   * @exception JspTagException
   *                   thrown when the page attribute does not exist
   */
  protected Object getAttribute(String name) 
      throws JspTagException {
    Object object = pageContext.getAttribute(name);
    
    if (object == null) {
      throw new JspTagException("attribute " + name + " does not exist");
    }
      
    return object;
  }

  /**
   * get the PreparedStatement from the enclosing tag
   * 
   * @return the PreparedStatement
   * @exception JspTagException
   *                   thrown if no PreparedStatement exists
   */
  protected PreparedStatement getPreparedStatement() 
      throws JspTagException {
    try {
      PreparedStatementImplTag stmtTag = 
      (PreparedStatementImplTag) findAncestorWithClass(this, Class.forName("tags.dbtags.preparedstatement.PreparedStatementImplTag"));
      return stmtTag.getPreparedStatement();
    } catch (ClassNotFoundException e) {
      throw new JspTagException(e.toString());
    }
  }
  
  /**
   * Perfoms reflection to translate a String name to one of
   * the int values of java.sql.Types
   * 
   * @param fieldName name of the field in java.sql.Types
   * @return int value of the sql type
   * @exception JspTagException
   *                   thrown if the sql type does not exist, or if
   *                   reflection fails
   */
  protected int getSqlTypeForFieldName(String fieldName) 
      throws JspTagException {
    try {
      Class typesClass = Class.forName("java.sql.Types");
      Field typeField  = typesClass.getField(fieldName);
      return typeField.getInt(null);
    } catch (ClassNotFoundException e) {
      throw new JspTagException(e.toString());
    } catch (NoSuchFieldException e) {
      throw new JspTagException("Illegal argument: setnull tag could not find = '" + 
                         fieldName + "' field in the java.sql.Types class" + e.toString());
    } catch (IllegalAccessException e) {
      throw new JspTagException(e.toString());
    }
  }

  public void release() {
    _position = 1;
    _attributeName = null;
  }
}
