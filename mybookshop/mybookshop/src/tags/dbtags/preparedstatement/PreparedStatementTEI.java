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

import javax.servlet.jsp.tagext.TagData;
import javax.servlet.jsp.tagext.TagExtraInfo;
import javax.servlet.jsp.tagext.VariableInfo;

/**
 * TagExtraInfo for the preparedstatement tag.  This
 * TagExtraInfo specifies that the PreparedStatementImplTag
 * assigns a java.sql.PreparedStatement object to the
 * "id" attribute within the begin and end tags.  However, see
 * {@link PreparedStatementImplTag} for more details.
 * 
 * @author Morgan Delagrange
 * @see PreparedStatementImplTag
 */

public class PreparedStatementTEI extends TagExtraInfo {

  public final VariableInfo[] getVariableInfo(TagData data)
  {
    return new VariableInfo[]
    {
      new VariableInfo(
                      data.getAttributeString("id"),
                      "java.sql.PreparedStatement",
                      true,
                      VariableInfo.NESTED 
                      ),
    };
  }
}

