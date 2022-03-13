package tags;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class EncodeURLTag extends TagSupport {
   private String url;

   public void setUrl(String url) {
      this.url = url;
   }
     public int doStartTag() throws JspException {
      HttpServletResponse res = (HttpServletResponse)
                                 pageContext.getResponse();
      try {
         PrintWriter writer = res.getWriter();
         writer.print(res.encodeURL(url));
         writer.flush();
      }
      catch(java.io.IOException ex) {
         throw new JspException(ex.getMessage());
      }
      return SKIP_BODY;
     }
}
