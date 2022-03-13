package actions;

import java.util.ResourceBundle;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 08.04.2004
 * Time: 10:36:50
 * To change this template use Options | File Templates.
 */

//Маршрутизаторы действий сохраняются в неизменном виде.
public class ActionRouter {
   private final String key;
   private final boolean isForward;

   public ActionRouter(String key) {
      this(key, true); // forward by default
   }
   public ActionRouter(String key, boolean isForward) {
      this.key = key;
      this.isForward = isForward;
   }

   // This method is called by the action servlet

   public synchronized void route(GenericServlet servlet,
                                  HttpServletRequest req,
                                  HttpServletResponse res)
                    throws ServletException, java.io.IOException {
      ResourceBundle bundle = (ResourceBundle)servlet.
                                 getServletContext().
                                 getAttribute("action-mappings");
      String url = (String)bundle.getObject(key);

      if(isForward) {
         servlet.getServletContext().getRequestDispatcher(
         res.encodeURL(url)).forward(req, res);
      }
      else {
         res.sendRedirect(res.encodeRedirectURL(url));
      }
   }
}
