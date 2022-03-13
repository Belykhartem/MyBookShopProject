package tags;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class TokenTag extends TagSupport {
   private String property;

   public int doStartTag() throws JspException {
     ServletRequest req = pageContext.getRequest();
     String value = (String)req.getAttribute("token");

    if(value == null)
      throw new JspException("No token in request scope");

     try {
       pageContext.getOut().print("<input type='hidden' " + 
         "name='token' " + "value ='" + value + "'>");
     }
     catch(java.io.IOException ex) {
       throw new JspException(ex.getMessage());
     }
     return SKIP_BODY; 
  }
}
