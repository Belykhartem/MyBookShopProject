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

import javax.servlet.jsp.tagext.TagData;
import javax.servlet.jsp.tagext.TagExtraInfo;

/**
 * TagExtraInfo for all getter tags.  Insures that each
 * getter tag uses <i>either</i> the position attribute
 * or the colName attribute.
 *
 * @author Marius Scurtescu
 */
public class BaseGetterTEI extends TagExtraInfo {

  public boolean isValid (TagData data)
  {
    boolean bPosition = false;
    boolean bName = false;

    if (data.getAttribute("position") != null) {
      bPosition = true;
    }

    if (data.getAttribute("colName") != null) {
      bName = true;
    }

    if (bPosition && bName) {
      System.out.println("Error: Get tags must not set _both_ the colName and position attributes");
      return false;
    }

    if (!bPosition && !bName) {
      System.out.println("Error: Get tags must set _either_ the colName or the position attribute");
      return false;
    }

    return true;
  }
}

