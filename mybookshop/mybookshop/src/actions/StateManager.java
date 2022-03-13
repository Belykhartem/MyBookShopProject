package actions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import actions.Action;
import actions.events.ActionEvent;
import actions.events.ActionListener;
import beans.Token;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 14.04.2004
 * Time: 9:57:07
 * To change this template use Options | File Templates.
 */
public class StateManager implements ActionListener {
   public void beforeAction(ActionEvent event)
                                       throws ServletException {
      Action action = (Action)event.getSource();
      HttpServletRequest request = event.getRequest();
      HttpSession session = request.getSession();
      String sessionToken = (String)session.getAttribute("token");

      if(action.isSensitive()) {
         String requestToken = (String)
                                request.getParameter("token");
         if(sessionToken == null || requestToken == null ||
                        !sessionToken.equals(requestToken)) {
            throw new ServletException(
                       "Sorry, but this is a sensitive page " +
                       "that can't be resubmitted.");
         }
      }
   }
   public void afterAction(ActionEvent event)
                                       throws ServletException {
      Action action = (Action)event.getSource();
      HttpServletRequest request = event.getRequest();
      HttpSession session = request.getSession();

      if(action.hasSensitiveForms()) {
         Token token = new Token(request);

         session.setAttribute("token", token.toString());
         request.setAttribute("token", token.toString());
      }
      if(action.isSensitive()) {
         session.removeAttribute("token");
      }
   }
}

