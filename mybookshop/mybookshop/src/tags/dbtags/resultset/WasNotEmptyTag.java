
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


/**
 * <p>Executes its body if the last ResultSet tag received more than 0 rows
 * from the database.  You must be after a ResultSet tag and inside
 * a StatementTag or PreparedStatementTag, or an error will be generated.</p>
 * 
 * <p>JSP Tag Lib Descriptor
 * <pre>
 * &lt;name>wasNotEmpty&lt;/name>
 * &lt;tagclass>tags.dbtags.statement.WasNotEmptyTag&lt;/tagclass>
 * &lt;bodycontent>JSP&lt;/bodycontent>
 * &lt;info>
 * Executes its body if the last ResultSet tag received more than 0 rows
 * from the database.  You must be after a ResultSet tag and inside
 * a StatementTag or PreparedStatementTag, or an error will be generated.
 * &lt;/info>
 * </pre>
 * 
 * @author Morgan Delagrange
 * @see WasEmptyTag
 */
public class WasNotEmptyTag extends WasEmptyTag {

  /**
   * Constructor instructs the superclass to only
   * execute the body if the last ResultSet returned 
   * more than 0 rows.
   */
  public WasNotEmptyTag() {
    setValue(false);
  }

}

