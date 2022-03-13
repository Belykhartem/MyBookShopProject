package actions;

import beans.ShoppingCart;
import beans.Item;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;


/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 08.04.2004
 * Time: 15:15:35
 * To change this template use Options | File Templates.
 */
public class ShopingAction extends ActionBase implements beans.Constants {
    public ActionRouter perform(HttpServlet servlet, HttpServletRequest req, HttpServletResponse res)
            throws ServletException {

        HttpSession session=req.getSession();

        ShoppingCart cart = (ShoppingCart) session.getAttribute(SHOPPING_CART_KEY);
        if (cart == null) {
            cart = new ShoppingCart();

            synchronized (this) {
                session.setAttribute(SHOPPING_CART_KEY, cart);
            }
        }

        boolean isRefresh=false;
        boolean isRender=false;
        boolean isRemove=false;
        String renderValue=null;
        String removeValue=null;


        Hashtable h=new Hashtable();
        Enumeration e=req.getParameterNames();
        while(e.hasMoreElements()){
            String name=(String) e.nextElement();
            try {
                if(name.equalsIgnoreCase("refresh"))
                    isRefresh=true;
                if(name.equalsIgnoreCase("render")){
                    isRender=true;
                    renderValue=req.getParameterValues(name)[0];
                }

                if(name.equalsIgnoreCase("remove")){
                    isRemove=true;
                    removeValue=req.getParameterValues(name)[0];
                }


                if(name.substring(0,4).equalsIgnoreCase("txt_")){
                    String new_name=name.substring(4,name.length());
                    String value=req.getParameterValues(name)[0];
                    h.put(new_name, value);
                }
            } catch (Exception e1) {
                continue;
            }
        }

        Iterator it;
        Item item;


        if(isRefresh==true){

//            String _renderParam=(String) session.getAttribute("renderParam");
//            String _pageNumber=(String) session.getAttribute("pageNumber");
//            session.removeAttribute("renderParam");
//            session.removeAttribute("pageNumber");
//
//            req.setAttribute("renderParam",_renderParam);
//            req.setAttribute("pageNumber",_pageNumber);


            it=null;
            it = cart.getItems().iterator();
            while (it.hasNext()) {
                item = null;
                item = (Item) it.next();
                Float amount=null;
                try {
                    amount= Float.valueOf((String)h.get(String.valueOf(item.getSku())));
                    if(amount.floatValue()==Float.valueOf("0").floatValue()){
                        cart.removeItem(item);
                        it = cart.getItems().iterator();
                        continue;
                    }
                } catch (NumberFormatException e1) {
                    continue;
                }
                item.setAmount(amount.floatValue());
            }
        }


//        Map map = req.getParameterMap();
//        String[] remove_value = (String[]) map.get("remove");
//        String[] render_value = (String[]) map.get("render");




//        if ((render_value != null) & (remove_value != null)) {
         if ((isRender== true) & (isRemove==true)) {
            if (removeValue.equalsIgnoreCase("all")) {
                cart.clearItems();
            } else {
                int remove_value_int;
                try {
                    remove_value_int = Integer.parseInt(removeValue);
                } catch (NumberFormatException e1) {
                    e1.printStackTrace();  //To change body of catch statement use Options | File Templates.
                    return new ActionRouter("shoping-page");
                }
                it = null;
                it = cart.getItems().iterator();
                while (it.hasNext()) {
                    item = null;
                    item = (Item) it.next();
                    if (item.getSku() == remove_value_int) {
                        cart.removeItem(item);
                        it = cart.getItems().iterator();
                    }
                }
            }
        }


        return new ActionRouter("shoping-page");

    }
}
