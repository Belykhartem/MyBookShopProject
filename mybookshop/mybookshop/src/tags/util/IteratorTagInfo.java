package tags.util;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 28.07.2004
 * Time: 14:37:18
 * To change this template use Options | File Templates.
 */

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

public class IteratorTagInfo extends TagExtraInfo {
   public VariableInfo[] getVariableInfo(TagData data) {
      return new VariableInfo[] {
         new VariableInfo(data.getId(), // scripting var's name
               "java.lang.Object", // variable's type
               true, // whether variable is created
               VariableInfo.NESTED), // scope

         new VariableInfo("next", // scripting var's name
               "java.lang.Object", // variable's type
               true, // whether variable is created
               VariableInfo.NESTED), // scope
      };
   }
}
