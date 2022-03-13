package tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

public class RemoveSessionTokenTag extends TagSupport {
   public int doEndTag() throws JspException {
      pageContext.removeAttribute("token",
                                  PageContext.SESSION_SCOPE);
     return SKIP_BODY; 
  }
}
