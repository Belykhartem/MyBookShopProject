package tags;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;
import beans.Token;

public class CreateTokensTag extends TagSupport {
   private String property;

   public int doEndTag() throws JspException {
      ServletRequest request = pageContext.getRequest();

      try {
         Token token = new Token((HttpServletRequest)request);
  
         pageContext.setAttribute("token", token.toString(),
                                    PageContext.SESSION_SCOPE);

         pageContext.setAttribute("token", token.toString(),
                                    PageContext.REQUEST_SCOPE);
      }
      catch(Exception ex) {
         throw new JspException(ex.getMessage());
      }
      return EVAL_PAGE; 
   }
}
