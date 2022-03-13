package actions.events;

import javax.servlet.ServletException;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 14.04.2004
 * Time: 9:50:12
 * To change this template use Options | File Templates.
 */


public interface ActionListener {
   public void beforeAction(ActionEvent event)
                            throws ServletException;
   public void afterAction(ActionEvent event)
                           throws ServletException;
}
