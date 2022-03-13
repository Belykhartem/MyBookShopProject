<%@ page contentType="text/html; charset=windows-1251" language="java" import="java.util.*,
                                                                               java.sql.*,
                                                                               beans.BasicDataSourceDB" errorPage="error.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1251">
<link href="CSS/style.css" rel="stylesheet" type="text/css">



<%! String _renderParam="";
    String _renderValue="";
    ResourceBundle _bandle_menu;
    Properties _props_catalog;
%>

<%
    _renderParam=""; _renderValue="";
    if(request.getAttribute("kb")!=null)
        _renderParam="korzina";
    else
        _renderParam=request.getParameter("render");

//    _renderParam=(_renderParam==null)?"#":_renderParam;




    _bandle_menu=(ResourceBundle) application.getAttribute("menu-mappings");
    _props_catalog=(Properties) application.getAttribute("props-catalog-mappings");

    try{
    _renderValue=_bandle_menu.getString(_renderParam);
        } catch(Exception ex) {
        _renderValue=null;
    }
    if (_renderValue==null)
        try{
            _renderValue=_props_catalog.getProperty(_renderParam);
        } catch(Exception ex) {
            throw new NullPointerException("Вы попали на секретную страницу!");
        }
    if (_renderValue==null){
//             _renderParam=null;
            throw new NullPointerException("Вы попали на секретную страницу!");

    }

%>



<html>

<jsp:include page="head.jsp" flush="true"/>

<body ONLOAD="preloadImages();">
<table width="750" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
        <td width="141" rowspan="2" background="../graphics/logotip.gif"></td>
        <td width="1" bgcolor="#3399ff"><img src="../graphics/blank.gif" width="1" height="1"></td>
        <td width="605">
            <table width="604" height="93" border="0" cellpadding="0" cellspacing="0" bgcolor="#99CCFF">
                <tr>
                    <td height="10" background="../graphics/hold.gif" bgcolor="#99ccff"></td>
                </tr>
                <tr>
                    <td height="70" align="center" bgcolor="#99ccff">

<%--	                <jsp:include page="main_menu_pic.jsp" flush="true"/>--%>
                        <jsp:include page="main_menu_pic.jsp" flush="true"><jsp:param name="render" value="<%=_renderParam%>"/></jsp:include>

		            </td>
                </tr>
                <tr>
                    <td height="10" background="../graphics/hold.gif" bgcolor="#99ccff"></td>
                </tr>
            </table>
        </td>
    </tr>
    <tr>
        <td width="1" bgcolor="#3399FF"><img src="../graphics/blank.gif" width="1" height="1"></td>
        <td height="18">
            <table width="604" height="100%" border="0" cellpadding="0" cellspacing="0">
                <tr>
                    <td align="center" bgcolor="#99ccff">

<%--		                <jsp:include page="main_menu_text.jsp" flush="true"/>--%>
                        <jsp:include page="main_menu_text.jsp" flush="true"><jsp:param name="render" value="<%=_renderParam%>"/></jsp:include>

		            </td>
                </tr>
            </table>
	    </td>
    </tr>
    <tr>
        <td width="141" height="425" valign="top">
	        <table width="101%" border="0" cellpadding="0" cellspacing="0">
                <tr>
                    <td width="9" height="152" background="../graphics/q_2.gif"></td>
                    <td width="100%" bgcolor="#99ccff">

<%--		               <jsp:include page="catalog.jsp" flush="true"/>--%>
                        <jsp:include page="catalog.jsp" flush="true"><jsp:param name="render" value="<%=_renderParam%>"/></jsp:include>


		            </td>
                </tr>
                <tr>
                    <td><img src="../graphics/q_1.gif"></td>
                    <td background="../graphics/q_3.gif"></td>
                </tr>
	        </table>

<%--	        <jsp:include page="search.jsp" flush="true"/>--%>
                <jsp:include page="search.jsp" flush="true"><jsp:param name="render" value="<%=_renderParam%>"/></jsp:include>


        </td>
        <td width="1" bgcolor="#3399ff"><img src="../graphics/blank.gif" width="1" height="1"></td>
        <td height="425" valign="top">

	        <jsp:include page="title.jsp" flush="true"><jsp:param name="render" value="<%=_renderParam%>"/></jsp:include>


            <%if (_renderParam.equalsIgnoreCase("korzina")) {%>
                    <jsp:include page="korzina.jsp" flush="true"><jsp:param name="render" value="<%=_renderParam%>"/></jsp:include>
            <%}else{%>
                    <jsp:include page="body.jsp" flush="true"><jsp:param name="render" value="<%=_renderParam%>"/></jsp:include>
            <%}%>

        </td>
    </tr>
    <tr>
        <td width="141"></td>
        <td width="1" bgcolor="#3399ff"><img src="../graphics/blank.gif" width="1" height="1"></td>
        <td height="1" bgcolor="#3399ff"></td>
    </tr>
    <tr>
        <td width="141" height="52">&nbsp;</td>
        <td width="1"><img src="../graphics/blank.gif" width="1" height="1"></td>
        <td align="right" valign="top">
            <table width="20%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td height="25" align="center" valign="middle" bgcolor="#3399ff" class="m_domen">www.ramblet.ru</td>
                </tr>
            </table>
        </td>
    </tr>
</table>


<jsp:include page="map_shoping.jsp" flush="true"/>


</body>
</html>
