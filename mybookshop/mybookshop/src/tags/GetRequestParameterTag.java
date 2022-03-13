package tags;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class GetRequestParameterTag extends TagSupport {
   private String property;

   public void setProperty(String property) {
      this.property = property;
   }
     public int doStartTag() throws JspException {
      ServletRequest req = pageContext.getRequest();
      String value = req.getParameter(property);

      try {
         pageContext.getOut().print(value == null ? "" : value);
      }
      catch(java.io.IOException ex) {
         throw new JspException(ex.getMessage());
      }
      return SKIP_BODY;
     }
}
