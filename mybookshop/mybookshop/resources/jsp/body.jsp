<%@ page contentType="text/html; charset=windows-1251" language="java" import="java.util.*,
                                                                               java.sql.*,
                                                                               beans.BasicDataSourceDB" errorPage="error.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1251">
<link href="CSS/style.css" rel="stylesheet" type="text/css">

<%@ taglib uri='utilities-tags' prefix='util' %>
<%@ taglib uri='tokens-tags' prefix='tokens' %>
<%@ taglib uri='db-tags' prefix='sql' %>


<jsp:useBean id='scroller' class='beans.ScrollBean'
                           scope='session'>
<%--  <jsp:setProperty name='scroller' property='position' value='1'/>--%>
<%--  <jsp:setProperty name='scroller' property='pageSize' value='24'/>--%>
  <jsp:setProperty name='scroller' property='bundleApplication' value='<%=application.getAttribute("application-mappings")%>'/>
</jsp:useBean>


<%! String _renderParam="";
    String _sql_query="";
    String _razdel="";
    String _vid="";
    String _url="";
    int _min_id;
    int _max_id;
    int _id;
%>
<%
    _renderParam=request.getParameter("render");
    session.setAttribute("renderParam",_renderParam);
    scroller.scroll(request.getParameter("scroll"));

    _min_id=0; _max_id=0; _id=0;
    _razdel=(_renderParam==null)?"":"%"+_renderParam+"%";
    _vid=(_renderParam==null)?"":_renderParam;
%>

<sql:connection id="conn1" dataSource="ds"></sql:connection>


<table width="100%"  border="0" cellspacing="0" cellpadding="0">
    <sql:statement id="stmt1" conn="conn1">
        <sql:query>
            SELECT * from products where (razdel LIKE "<%=_razdel%>") or (vid="<%=_vid%>")
        </sql:query>

    <sql:resultSet id="rset1" startRow="<%= scroller.getPosition() %>" endRow="<%= scroller.getEndPosition() %>">
    <tr>
          <td height="2" colspan="7"></td>
    </tr>
	<tr>
        <td width="1%" height="23"></td>
        <td width="8%" align="center" valign="middle"><img src="../graphics/bs.gif" width="19" height="20"></td>
        <td width="1%" class="zz">&nbsp;</td>
<%--        title--%>
        <%
            if (_min_id==0)
                _min_id=rset1.getRow();
            _max_id=rset1.getRow();
        %>

        <td width="78%"><span class="zz80"><%=rset1.getRow()%>.</span> <a href="#" class="zz"><sql:getColumn  colName="title"/></a></td>
        <td width="1%" class="zz">&nbsp;</td>
        <td width="10%">&nbsp;</td>
        <td width="1%">&nbsp;</td>
    </tr>
    <tr>
        <td></td>
<%--        picture--%>
        <td align="center" valign="middle"><a href="#"><img src=<%="../"+rset1.getString("picture")%> alt=<%="Переход&nbsp;на&nbsp;страницу:&nbsp;"+rset1.getString("title").replaceAll(" ","&nbsp;")%> width="40" height="57" border="1"></a></td>
        <td>&nbsp;</td>
        <td valign="top">
            <table width="100%" height="100%"  border="0" cellpadding="0" cellspacing="0">
<%--                author--%>
                <tr>
                    <td class="zz80b"><sql:getColumn  colName="author"/></td>
                </tr>
                <tr>
                    <td height="7"></td>
                </tr>
<%--                new_price--%>
                <tr>
                    <td><p><span class="zz80">цена: </span><span class="zz80_brown">$<sql:getColumn  colName="new_price"/></span></p></td>
                </tr>
                <tr>
                    <td height="7"></td>
                </tr>
<%--                size--%>
				<tr>
                    <td><p><span class="zz80">размер: </span><span class="zz80_brown"><sql:getColumn  colName="size"/> Кбайт</span></p></td>
                </tr>
                <tr>
                    <td height="7"></td>
                </tr>
				
				
				
<%--                publisher--%>
                <tr>
                    <td class="zz80"><sql:getColumn  colName="publisher"/></td>
                </tr>
            </table>
        </td>
<%--                korzina--%>
        <td>&nbsp;</td>
        <td align="center" valign="middle"><a href="<%= response.encodeURL("addtocart-action.dll?kb="+rset1.getString("products_id"))%>"><img src="../graphics/korzina.gif" alt="Добавить в корзину" width="60" height="60" border="0"></a></td>
        <td>&nbsp;</td>
    </tr>
    <tr>
        <td height="10" colspan="7"></td>
    </tr>
<%--    annotation--%>
    <tr>
        <td></td>
        <td colspan="3" class="zz80"><%=rset1.getString("annotation").substring(0,(rset1.getInt("substr_annotation")<rset1.getString("annotation").length())?rset1.getInt("substr_annotation"):rset1.getString("annotation").length())%> ... <a href="#">дальше</a> </td>
        <td colspan="3">&nbsp;</td>
    </tr>
    <tr>
        <td height="10" colspan="7"></td>
    </tr>
    <tr>
        <td height="1"></td>
        <td height="1" colspan="5" background="../graphics/razd.gif"></td>
        <td height="1"></td>
    </tr>
	<tr>
        <td height="2" colspan="7"></td>
    </tr>

    </sql:resultSet>
    </sql:statement>

</table>

<sql:closeConnection conn="conn1"/>

<%--<p>--%>
          <%
                scroller.getNavPane(pageContext, false);
          %>
<%--</p>--%>

<%!
    String url_back="";
    String url_fwd="";
    String nav_ref="";
    String nav_ref_fwd="";
    String nav_ref_back="";
    int cellWidth=12, cellWidth1=0;
%>
<table width="100%"  border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td height="15" colspan='3' class="nav_i">
            &nbsp;&nbsp;<strong>всего <%=scroller.getRowCountAll(pageContext)%></strong> (показаны <%=_min_id%>-<%=_max_id%>)
        </td>
    </tr>

    <tr>
        <td colspan='3'><table width="100%"  border="0" cellspacing="0" cellpadding="0">
            <tr>
                      <td height="1" width="1%"></td>
                      <td height="1"  width="98%"  background="../graphics/razd.gif"></td>
                      <td height="1" width="1%"></td>
            </tr>

        </table></td>

    </tr>
	        <tr>
                      <td height="20" width="33%"></td>
                      <td height="20"  width="34%"></td>
                      <td height="20" width="33%"></td>
            </tr>

    <tr>
      <td  class='nav'>
      <%
              if (scroller.getIsGBack()==true) {
                  String url_gback = response.encodeURL("shoping-action.dll?render="+_renderParam+"&scroll=gback");
                  out.print("<a href='" + url_gback + "'>&lt;Предыдущие " + scroller.getPageOnSheet() + "</a>");
              }
          %></td>


        <td>
        <%
            url_back=response.encodeURL("shoping-action.dll?render="+_renderParam+"&scroll=back");
            url_fwd=response.encodeURL("shoping-action.dll?render="+_renderParam+"&scroll=fwd");

//            out.println("<p>");

            nav_ref_fwd="<a href='"+url_fwd+"'>&gt;&gt;</a>";
            nav_ref_back="<a href='"+url_back+"'>&lt;&lt;</a>";

        //    if (scroller.getIsBack(pageContext)==true)
        //        out.print("<a href='"+url_back+"'>&lt;BACK;</a>");
        //
        //    if (scroller.getIsFwd(pageContext)==true)
        //        out.print("<a href='"+url_fwd+"'>NEXT&gt;</a>");

//            out.println("</p>");

            out.println("<table border='0' cellspacing='0' cellpadding='0' align='center'>");

            out.println("<tr>");

            if (scroller.getIsBack(pageContext)==true)
                out.println("<td width="+cellWidth1+" height='20' align='center' nowrap class='nav_a'>"+nav_ref_back+"</td>");
            else
                out.println("<td width="+cellWidth1+" height='20' align='center' nowrap class='blank'>"+"&lt;&lt;"+"</td>");


            for (int i = scroller.getStartPageNumber(); i <= scroller.getFinishPageNumber(); i++) {
                if (i == scroller.getPageNumber()){
                    session.setAttribute("pageNumber",String.valueOf(i));
                    nav_ref = "[" + String.valueOf(i) + "]";
                }
                else
                    nav_ref = "<a href=" + response.encodeURL("shoping-action.dll?render="+_renderParam+"&scroll=" + i) + ">" + i;

                cellWidth1=(cellWidth+(String.valueOf(i).length()-1)*6)+8;

                out.println("<td width="+cellWidth1+" height='20' align='center' nowrap class='nav_a'>"+nav_ref+"</td>");
            }

            if (scroller.getIsFwd(pageContext)==true)
                out.println("<td width="+cellWidth1+" height='20' align='center' nowrap class='nav_a'>"+nav_ref_fwd+"</td>");
            else
                out.println("<td width="+cellWidth1+" height='20' align='center' nowrap class='blank'>"+"&gt;&gt;"+"</td>");

            out.println("</tr>");
            out.println("</table>");
        %>
        </td>

        <td class='nav'>
        <%
            if (scroller.getIsGFwd()==true) {
                String url_gfwd = response.encodeURL("shoping-action.dll?render="+_renderParam+"&scroll=gfwd");
                out.print("<a href='" + url_gfwd + "'>Следующие " + scroller.getNextValue() + "&gt</a>");
            }
        %>
        </td>
    </tr>
	        <tr>
                      <td colspan=3 height="20"></td>

            </tr>

</table>

