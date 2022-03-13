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

package tags.dbtags.connection;

import beans.BasicDataSourceDB;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.sql.DataSource;

/**
 * <p>JSP tag connection, used to get a
 * java.sql.Connection object from the DriverManager.</p>
 *
 * <p>JSP Tag Lib Descriptor
 * <pre>
 * &lt;name>connection&lt;/name>
 * &lt;tagclass>tags.dbtags.connection.ConnectionTag&lt;/tagclass>
 * &lt;bodycontent>JSP&lt;/bodycontent>
 * &lt;teiclass>tags.dbtags.connection.ConnectionTEI&lt;/teiclass>
 * &lt;info>Opens a connection based on either a url in the body of the tag
 * or by using the "datasource" tag attribute to reference to a
 * javax.sql.DataSource page attribute.  driver (optional),
 * userid (optional), and password (optional) are also set in the
 * body of the tag.&lt;/info>
 *   &lt;attribute>
 *     &lt;name>id&lt;/name>
 *     &lt;required>true&lt;/required>
 *     &lt;rtexprvalue>false&lt;/rtexprvalue>
 *   &lt;/attribute>
 *   &lt;attribute>
 *     &lt;name>jndiName&lt;/name>
 *     &lt;required>false&lt;/required>
 *     &lt;rtexprvalue>false&lt;/rtexprvalue>
 *   &lt;/attribute>
 *   &lt;attribute>
 *     &lt;name>dataSource</name>
 *     &lt;required>false</required>
 *     &lt;rtexprvalue>false</rtexprvalue>
 *   &lt;/attribute>
 * </pre>
 *
 * @author Morgan Delagrange
 */
public class ConnectionTag extends BodyTagSupport {

  private String _dataSourceName = null;
  private String _url      = null;
  private String _driver   = null;
  private String _userId   = null;
  private String _password = null;
  private String _jndiName = null;
  private String _dbName = null;

  /**
   * Set the name of a javax.sql.DataSource page attribute
   * which can create a database connection
   *
   * @param dataSourceName
   *               name of a javax.sql.DataSource page attribute
   */
  public void setDataSource(String dataSourceName) {
    _dataSourceName = dataSourceName;
  }


  public void setdbName(String dbName) {
    _dbName = dbName;
  }
  /**
   * URL of the database to access.
   *
   * @param url    database URL
   */
  public void setUrl(String url) {
    _url = url;
  }

  /**
   * java.sql.Driver for the database.  This method is
   * optional if you have already loaded the Driver in
   * the JVM.
   *
   * @param driver class name of the java.sql.Driver
   */
  public void setDriver(String driver) {
    _driver = driver;
  }

  /**
   * jndi named datasource used for connecting to the database via a jndi lookup
   *
   * @param jndiName jndi name for the jdbc datasourcer
   */
  public void setJndiName(String jndiName) {
    _jndiName = jndiName;
  }

  /**
   * User id for the database.  Optional if the user id
   * is already encoded in the URL, or if it is not
   * required by the database.
   *
   * @param userId user id for the database.
   */
  public void setUserId(String userId) {
    _userId = userId;
  }

  /**
   * Password for the database.  Optional if the
   * password is already encoded in the URL, or if
   * it is not required by the database.
   *
   * @param password password for the given user id
   */
  public void setPassword(String password) {
    _password = password;
  }

  public int doStartTag() throws JspTagException {
    return EVAL_BODY_TAG;
  }

  public int doAfterBody() throws JspTagException
  {
    // Automatically trim the contents of this tag
    try {
      getPreviousOut().write(getBodyContent().getString().trim());
    } catch (IOException e) {
      throw new JspTagException(e.toString());
    }
    return EVAL_PAGE;
  }

  public int doEndTag() throws JspTagException{

    BasicDataSourceDB dataSource = null;

    try {
      if (_driver != null) {
        Class.forName(_driver);
      }

      Connection conn = null;

      if (_jndiName != null) {
	try {
          Context ctx = new InitialContext();
	  dataSource = (BasicDataSourceDB)ctx.lookup(_jndiName);
	} catch (NamingException ne) {
	    throw new JspTagException(ne.toString());
	}
	if (_userId != null) {
          conn = dataSource.getConnection(_userId, _password);
        } else {
          conn = dataSource.getConnection();
        }
      } else if (_dataSourceName != null) {
        dataSource = (BasicDataSourceDB) pageContext.findAttribute(_dataSourceName);
        if ( dataSource == null ) {
          throw new JspTagException("Did not find a DataSource bean named " + _dataSourceName );
        }
        
        if (_userId != null) {
          conn = dataSource.getConnection(_userId, _password);
        } else {
            if (_dbName != null)
                conn = dataSource.getConnection(_dbName);
            else
                conn = dataSource.getConnection();
        }
      } else if (_url != null) {
        if (_userId != null) {
          conn = DriverManager.getConnection(_url, _userId, _password);
        } else {
          conn = DriverManager.getConnection(_url);
        }
      } else {
        throw new JspTagException("Cannot connect to database, no database URL or DataSource.");
      }

      pageContext.setAttribute(getId(),conn);
    } catch (SQLException e) {
      throw new JspTagException(e.toString());
    } catch (ClassNotFoundException e) {
      throw new JspTagException("Driver class '" + _driver + "' not found\n" +
                                e.toString());
    }

    return EVAL_PAGE;
  }

  public void release() {
    _dataSourceName = null;
    _url      = null;
    _driver   = null;
    _userId   = null;
    _password = null;
  }

}
