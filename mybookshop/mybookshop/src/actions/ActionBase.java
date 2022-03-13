package actions;

import actions.events.ActionListener;
import actions.events.ActionEvent;

import javax.servlet.ServletException;
import java.util.Vector;
import java.util.Enumeration;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 14.04.2004
 * Time: 9:53:59
 * To change this template use Options | File Templates.
 */
public abstract class ActionBase implements Action,
        ActionListener {
   private Vector listeners = new Vector();

   public void addActionListener(ActionListener listener) {
      listeners.addElement(listener);
   }
   public void removeActionListener(ActionListener listener) {
      listeners.remove(listener);
   }
   public boolean isSensitive() { // override this method
      return false;               // if this action is sensitive
   }
   public boolean hasSensitiveForms() { // override this method
      return false;               // if you have sensitive content
   }
   public void beforeAction(ActionEvent event)
                                        throws ServletException {
      fireEvent(event);
   }
   public void afterAction(ActionEvent event)
                                        throws ServletException {
      fireEvent(event);
   }
   protected void fireEvent(ActionEvent event)
                                       throws ServletException {
      Enumeration it = listeners.elements();

      while(it.hasMoreElements()) {
         ActionListener listener =
                        (ActionListener)it.nextElement();

         switch(event.getEventType()) {
            case ActionEvent.BEFORE_ACTION:
                         listener.beforeAction(event);
                         break;
            case ActionEvent.AFTER_ACTION:
                     listener.afterAction(event);
                     break;
         }
      }
   }
}

