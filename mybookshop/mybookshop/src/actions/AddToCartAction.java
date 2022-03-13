package actions;

import beans.ShoppingCart;
import beans.Item;
import beans.BasicDataSourceDB;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Iterator;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 27.07.2004
 * Time: 12:51:13
 * To change this template use Options | File Templates.
 */
public class AddToCartAction extends ActionBase implements beans.Constants {
    private Connection conn;
    private Statement stmt;
    private ResultSet rset;

    public ActionRouter perform(HttpServlet servlet, HttpServletRequest req, HttpServletResponse res)
            throws ServletException {

        HttpSession session=req.getSession();


        String kodBook=req.getParameter("kb");
        int scu=Integer.valueOf(kodBook).intValue();

        ShoppingCart cart=(ShoppingCart) session.getAttribute(SHOPPING_CART_KEY);

        if(cart==null) {
            throw new ServletException("No cart found");
        }

        Iterator it=cart.getItems().iterator();
        boolean bookWasInCart=false;

        while(it.hasNext()) {
            Item item=(Item) it.next();
            if(item.getSku()==scu){
                bookWasInCart=true;
                item.setAmount(item.getAmount()+1);
            }
        }

        if(!bookWasInCart){
        conn=null;
        stmt=null;
        rset=null;
        String sql_query="select * from products where products_id="+scu;
        BasicDataSourceDB ds=(BasicDataSourceDB) servlet.getServletContext().getAttribute("ds");
        try {
            conn=ds.getConnection();
            stmt=conn.createStatement();
            stmt.executeQuery(sql_query);
            rset=stmt.getResultSet();
            while (rset.next()) {
                cart.addItem(new Item(rset.getInt("products_id"), rset.getString("title"), rset.getString("author"), rset.getFloat("new_price"), rset.getInt("size"), 1));
            }

        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use Options | File Templates.
        }
        try {
            rset.close();
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use Options | File Templates.
        }
        try {
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use Options | File Templates.
        }
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use Options | File Templates.
        }

//        if(!bookWasInCart){
//            cart.addItem(new Item(scu, "Персональный компьютер", "Пидор Пидоров", (float)10.36, 45897, 0));
        }

        String _renderParam=(String) session.getAttribute("renderParam");
        String _pageNumber=(String) session.getAttribute("pageNumber");
        session.removeAttribute("renderParam");
        session.removeAttribute("pageNumber");

        req.setAttribute("renderParam",_renderParam);
        req.setAttribute("pageNumber",_pageNumber);

        req.setAttribute("kb",kodBook);
        return new ActionRouter("shoping-page");
    }
}