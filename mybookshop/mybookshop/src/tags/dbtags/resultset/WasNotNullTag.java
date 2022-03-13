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
 * <p>Executes its body if the last getColumn tag did not receive
 * a null from the database.  You must be inside a resultset tag and there
 * must be a previous getColumn tag, or an error will be generated.</p>
 * 
 * <p>JSP Tag Lib Descriptor
 * <pre>
 * &lt;name>wasNotNull&lt;/name>
 * &lt;tagclass>tags.dbtags.resultset.WasNotNullTag&lt;/tagclass>
 * &lt;bodycontent>JSP&lt;/bodycontent>
 * &lt;info>Executes its body if the last getColumn tag 
 * did not receive a null value from the database.  You 
 * must be inside a resultset tag and there must
 * be a previous getColumn tag, or an error will be 
 * generated.&lt;/info>
 * </pre>
 * 
 * @author Morgan Delagrange
 * @see WasNullTag
 */
public class WasNotNullTag extends WasNullTag {

  /**
   * Constructor instructs the superclass to only
   * execute the body if the last getColumn returned null.
   */
  public WasNotNullTag() {
    setValue(false);
  }

}

