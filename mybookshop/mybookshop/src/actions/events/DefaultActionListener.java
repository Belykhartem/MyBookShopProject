package actions.events;

import javax.servlet.ServletException;
import java.io.FileWriter;
import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 14.04.2004
 * Time: 9:52:09
 * To change this template use Options | File Templates.
 */
public class DefaultActionListener implements ActionListener {
   private FileWriter writer;

   public DefaultActionListener(String filename) {
      try {
         this.writer = new FileWriter(new File(filename));
      }
      catch(java.io.IOException ex) {}
   }
   public void close() {
      try {
         writer.close();
      }
      catch(java.io.IOException ex) {}
   }
   public void beforeAction(ActionEvent event)
                  throws ServletException {
      try {
         writer.write("before action");
      }
      catch(java.io.IOException ex) {}
   }
   public void afterAction(ActionEvent event)
                  throws ServletException {
      try {
         writer.write("after action");
      }
      catch(java.io.IOException ex) {}
   }
}
