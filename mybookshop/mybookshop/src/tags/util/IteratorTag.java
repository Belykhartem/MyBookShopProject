package tags.util;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 28.07.2004
 * Time: 14:32:34
 * To change this template use Options | File Templates.
 */


import java.util.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

public class IteratorTag extends BodyTagSupport {
   protected Collection collection;
   protected Object[] items;
   protected Object item, nextItem;
	protected int size, cnt;

	public void setId(String id) {
		this.id = id;
	}
   public void setCollection(Collection collection) {
      this.collection = collection;
		items = collection.toArray();
		size = collection.size();
		cnt = 0;
   }
   public int doStartTag() throws JspException {
      return size > 0 ? EVAL_BODY_TAG : SKIP_BODY;
   }
   public void doInitBody() throws JspException {
      item = items[cnt];
		nextItem = cnt+1 < size ? items[cnt+1] : item;
		cnt++;

      pageContext.setAttribute(getId(), item);
      pageContext.setAttribute("next", nextItem);
   }
   public int doAfterBody() throws JspException {
		if(cnt < size) {
      	item = items[cnt];
			nextItem = cnt+1 < size ? items[cnt+1] : item;
			cnt++;

      	pageContext.setAttribute(getId(), item);
      	pageContext.setAttribute("next", nextItem);
      	return EVAL_BODY_TAG;
      }
      else {
      	try {
        		if(bodyContent != null)
            	bodyContent.writeOut(getPreviousOut());
      	}
      	catch(java.io.IOException e) {
        		throw new JspException(e.getMessage());
      	}
         return SKIP_BODY;
		}
   }
   public void release() {
      collection = null;
      items = null;
		item = null;
		nextItem = null;
      size = cnt = 0;
   }
}