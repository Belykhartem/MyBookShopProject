package beans;

import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.JspWriter;
import javax.servlet.http.HttpServletResponse;
import java.util.ResourceBundle;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 27.04.2004
 * Time: 12:31:42
 * To change this template use Options | File Templates.
 */
public class ScrollBean {
    private int position, pageSize, pageNumber, pageOnSheet, realPageOnSheet, gposition,
    gpositionall, rowCountAll, rowCount, startPageNumber, finishPageNumber, nextValue;
    private boolean isFwd, isBack, isGFwd, isGBack;
    private ResourceBundle bundleApplication;


    public ScrollBean() {
        this(1, 49, 10);
    }


    public ScrollBean(int start, int pageSize, int pageOnSheet) {
        this.position = start;
        this.pageSize = pageSize;
        this.pageOnSheet = pageOnSheet;

        this.realPageOnSheet=0;
        this.gposition = 1;
        this.gpositionall=0;
        this.pageNumber=0;
        this.isGBack=false;
        this.isGBack=false;

        this.startPageNumber=0;
        this.finishPageNumber=0;
        this.nextValue=0;
    }

    public synchronized void scroll(String direction) {
        try {
            pageNumber = Integer.valueOf(direction).intValue();
        } catch (NumberFormatException e) {
            pageNumber = 0;
//            e.printStackTrace();  //To change body of catch statement use Options | File Templates.
        }


        if ("fwd".equalsIgnoreCase(direction)) {
            position += pageSize + 1;
            gposition= ((int)Math.floor(position/((pageSize+1)*pageOnSheet))+1);

        } else if ("back".equalsIgnoreCase(direction)) {
            position -= pageSize + 1;
            position = (position < 1) ? 1 : position;
            gposition= ((int)Math.floor(position/((pageSize+1)*pageOnSheet))+1);

        } else if (pageNumber != 0) {
            position = pageNumber * (pageSize + 1) - (pageSize);
            position = (position < 1) ? 1 : position;

            gposition= ((int)Math.floor(position/((pageSize+1)*pageOnSheet))+1);



        } else if ("gfwd".equalsIgnoreCase(direction)) {
            gposition = gposition + 1;
            position=(pageSize + 1)*(gposition*pageOnSheet-pageOnSheet)+1;

        } else if ("gback".equalsIgnoreCase(direction)) {
            gposition = gposition - 1;
            gposition = (gposition < 1) ? 1 : gposition;
            position=(pageSize + 1)*(gposition*pageOnSheet-pageOnSheet)+1;

        } else {
            position = 1;
            gposition = 1;
        }

        if (pageNumber==0)
            pageNumber=(position+pageSize)/(pageSize+1);

    }

    public synchronized void setPosition(int position) {
        this.pageSize = position;
        this.gposition = 1;
    }

    public synchronized void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public synchronized void setPageOnSheet(int pageOnSheet) {
        this.pageOnSheet = pageOnSheet;
    }

    public synchronized void setBundleApplication(ResourceBundle bundleApplication) {
        this.bundleApplication = bundleApplication;
        this.pageSize = Integer.valueOf(bundleApplication.getString("NotesOnSheet")).intValue();
        this.pageOnSheet = Integer.valueOf(bundleApplication.getString("PageOnSheet")).intValue();
    }

    public synchronized int getPosition() {
        return position;
    }

    public synchronized int getEndPosition() {
        return position + pageSize;
    }

    public synchronized int getPageSize() {
        return pageSize;
    }

    public synchronized int getPageOnSheet() {
        return pageOnSheet;
    }

    public synchronized int getRealPageOnSheet() {
        return realPageOnSheet;
    }

    public synchronized int getGposition() {
        return gposition;
    }

    public synchronized int getGpositionall() {
        return gpositionall;
    }

    public synchronized int getPageNumber() {
        return pageNumber;
    }

    public synchronized int getNextValue() {
        return nextValue;
    }

    public synchronized boolean getIsFwd(PageContext pageContext) {
        isFwd=((Boolean)pageContext.getAttribute("tags.dbtags.resultset.isfwd")).booleanValue();
        return isFwd;
    }
     public synchronized boolean getIsBack(PageContext pageContext) {
        isBack=((Boolean)pageContext.getAttribute("tags.dbtags.resultset.isback")).booleanValue();
        return isBack;
    }

    public synchronized boolean getIsGFwd() {
        return isGFwd;
    }

     public synchronized boolean getIsGBack() {
        return isGBack;
    }

     public synchronized int getStartPageNumber() {
        return startPageNumber;
    }

     public synchronized int getFinishPageNumber() {
        return finishPageNumber;
    }

     public synchronized int getRowCountAll(PageContext pageContext) {
        rowCountAll=((Integer)pageContext.getAttribute("tags.dbtags.resultset.rowcountall")).intValue();
        return rowCountAll;
    }

     public synchronized int getRowCount(PageContext pageContext) {
        rowCount=((Integer)pageContext.getAttribute("tags.dbtags.resultset.rowcount")).intValue();
        return rowCount;
    }



    public synchronized void getNavPane(PageContext pageContext, boolean printNav) throws IOException {

        int nextvalue_1, nextvalue_2, rowcountall, x1, x2,
                gpositionfullq_1, gpositionfullw_1, gpositionfull_1, x3_1, x4_1,
                gpositionfullq_2, gpositionfullw_2, gpositionfull_2, x3_2, x4_2;

        this.isGBack=false;
        this.isGFwd=false;


        JspWriter out=pageContext.getOut();
        HttpServletResponse  response= (HttpServletResponse)pageContext.getResponse();


        rowcountall= getRowCountAll(pageContext);
        x1=(int) Math.floor(rowcountall/((pageSize+1)*pageOnSheet));
        x2=rowcountall%((pageSize+1)*pageOnSheet);
        gpositionall=x2==0?x1:x1+1;

        nextvalue_1=0;
        gpositionfullq_1=(gposition+1)*(pageSize+1)*pageOnSheet;
        gpositionfullq_1=gpositionfullq_1<=rowcountall?gpositionfullq_1:rowcountall;
        gpositionfullw_1=(gposition)*(pageSize+1)*pageOnSheet;
        gpositionfull_1=gpositionfullq_1-gpositionfullw_1;

        x3_1=(int) Math.floor(gpositionfull_1/(pageSize+1));
        x4_1=gpositionfull_1%(pageSize+1);
        nextvalue_1=x4_1==0?x3_1:x3_1+1;
        nextValue=nextvalue_1;


        nextvalue_2=0;
        gpositionfullq_2=(gposition)*(pageSize+1)*pageOnSheet;
        gpositionfullq_2=gpositionfullq_2<=rowcountall?gpositionfullq_2:rowcountall;
        gpositionfullw_2=(gposition-1)*(pageSize+1)*pageOnSheet;
        gpositionfull_2=gpositionfullq_2-gpositionfullw_2;

        x3_2=(int) Math.floor(gpositionfull_2/(pageSize+1));
        x4_2=gpositionfull_2%(pageSize+1);
        nextvalue_2=x4_2==0?x3_2:x3_2+1;
        realPageOnSheet=nextvalue_2;

        startPageNumber = (gposition * pageOnSheet - pageOnSheet) + 1;
        finishPageNumber = startPageNumber + nextvalue_2-1;

        if (gposition != 1)
            this.isGBack=true;


        if (gposition < gpositionall)
            this.isGFwd=true;


//        if (getRowCount(pageContext)==0)
//            throw new NullPointerException("Вы попали на секретную страницу!");


        if (printNav==true)
            this.printNavPane(pageContext);

    }



    private synchronized void printNavPane(PageContext pageContext) throws IOException {
        String renderParam=pageContext.getRequest().getParameter("render");
        JspWriter out=pageContext.getOut();
        HttpServletResponse  response= (HttpServletResponse)pageContext.getResponse();

        String url_back=response.encodeURL("shoping-action.dll?render="+renderParam+"&scroll=back");
        String url_fwd=response.encodeURL("shoping-action.dll?render="+renderParam+"&scroll=fwd");

        out.println("<p>");

        if (getIsBack(pageContext)==true)
            out.print("<a href='"+url_back+"'>&lt;BACK;</a>");

        if (getIsFwd(pageContext)==true)
            out.print("<a href='"+url_fwd+"'>NEXT&gt;</a>");

        out.println("</p>");

        out.println("<table width='600' border='1'>");
        out.println("<tr>");

        String nav_ref;
        for (int i = getStartPageNumber(); i <= getFinishPageNumber(); i++) {
            if (i == getPageNumber())
                nav_ref = "[" + String.valueOf(i) + "]";
            else
                nav_ref = "<a href=" + response.encodeURL("shoping-action.dll?render="+renderParam+"&scroll=" + i) + ">" + i;

            out.println("<td  class='qqq'>"+nav_ref+"</td>");
        }

        out.println("</tr>");
        out.println("</table>");
        out.println("<p>");


        if (getIsGBack()==true) {
            String url_gback = response.encodeURL("shoping-action.dll?render="+renderParam+"&scroll=gback");
            out.print("<a href='" + url_gback + "'>&lt;Предыдущие " + getPageOnSheet() + ";</a>");
        }


        if (getIsGFwd()==true) {
            String url_gfwd = response.encodeURL("shoping-action.dll?render="+renderParam+"&scroll=gfwd");
            out.print("<a href='" + url_gfwd + "'>Следующие " + getNextValue() + "&gt</a>");
        }

        out.println("</p>");

        out.println("<p>&nbsp;</p>");

        out.println(getRealPageOnSheet());
        out.println(getGposition());
        out.println(getRowCountAll(pageContext));
        out.println(getRowCount(pageContext));
        out.println(getGpositionall());



    }

}
