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
package tags.stringtags;

import javax.servlet.jsp.JspException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.NumberUtils;

/**
 * Get a specified substring from a larger String based 
 * on the start index and end-before index in the larger 
 * String.
 *
 * <dl>
 * <dt>start</dt><dd>
 *             Start index.
 *             Required.
 * </dd>
 * <dt>end</dt><dd>
 *             Index to end before.
 *             Required.
 * </dd>
 * </dl>
 * 
 * @author bayard@generationjava.com
 */
public class SubstringTag extends StringTagSupport {

    private String end;
    private String start;

    public SubstringTag() {
        super();
    }

    /**
     * Get the start property
     *
     * @return String property
     */
    public String getStart() {
        return this.start;
    }

    /**
     * Set the start property
     *
     * @param start String property
     */
    public void setStart(String start) {
        this.start = start;
    }


    /**
     * Get the end property
     *
     * @return String property
     */
    public String getEnd() {
        return this.end;
    }

    /**
     * Set the end property
     *
     * @param end String property
     */
    public void setEnd(String end) {
        this.end = end;
    }



    public String changeString(String text) throws JspException {
        if(end == null) {
            return StringUtils.substring(text, NumberUtils.stringToInt(start) );
        } else {
            return StringUtils.substring(text, NumberUtils.stringToInt(start), NumberUtils.stringToInt(end) );
        }
    }

    public void initAttributes() {

        this.start = "0";

        this.end = null;

    }


}
