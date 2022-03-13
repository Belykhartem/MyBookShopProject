package beans;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 28.07.2004
 * Time: 10:11:01
 * To change this template use Options | File Templates.
 */

import java.util.Iterator;
import java.util.Vector;

public class Inventory implements java.io.Serializable {
   final protected Vector items;

   public Inventory() {
      items = new Vector();
   }
   public void addItem(Item item) {
      items.add(item);
   }
   public void removeItem(Item item) {
      items.remove(item);
   }

   public void clearItems() {
      items.clear();
   }
   public Vector getItems() {
      return items;
   }
}