package tags.app;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 28.07.2004
 * Time: 14:39:59
 * To change this template use Options | File Templates.
 */


import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import beans.ShoppingCart;

public class CartIteratorTag extends tags.util.IteratorTag
                               implements beans.Constants {
   public int doStartTag() throws JspException {
     ShoppingCart cart = (ShoppingCart)pageContext.getAttribute(
                             SHOPPING_CART_KEY,
                           PageContext.SESSION_SCOPE);
      if(cart == null) {
         throw new JspException("CartIteratorTag can't find " +
                                "cart");
      }
      setCollection(cart.getItems());
      return super.doStartTag();
   }
}

