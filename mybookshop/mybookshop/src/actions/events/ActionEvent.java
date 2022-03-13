package actions.events;

import actions.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 14.04.2004
 * Time: 9:51:13
 * To change this template use Options | File Templates.
 */
public class ActionEvent extends java.util.EventObject {
   public static final int BEFORE_ACTION=0, AFTER_ACTION=1;
   private int eventType;
   private HttpServletRequest request;
   private HttpServletResponse response;

   public ActionEvent(Action action, int eventType,
                      HttpServletRequest request,
                      HttpServletResponse response) {
      super(action);
      this.eventType = eventType;
      this.request = request;
      this.response = response;
   }
   public int getEventType() {
      return eventType;
   }
   public HttpServletRequest getRequest() {
      return request;
   }
   public HttpServletResponse getResponse() {
      return response;
   }
}
