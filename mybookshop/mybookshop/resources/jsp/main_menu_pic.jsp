<%@ page contentType="text/html; charset=windows-1251" language="java" import="java.util.*,
                                                                               java.sql.*,
                                                                               beans.BasicDataSourceDB" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1251">
<link href="CSS/style.css" rel="stylesheet" type="text/css">

<%! String _renderParam="";

%>
<%
    _renderParam=request.getParameter("render");
//    _renderParam=(_renderParam==null)?"#":_renderParam;
%>

    <table width="600" border="0" cellspacing="0" cellpadding="0">
    <tr align="center">

    <% if(_renderParam.equalsIgnoreCase("novosti")==false) {%>
    <td height="69"><A HREF="#"
	ONMOUSEOVER="toggleImages('', new Array('novosti_3', '../graphics/novosti_2.gif')); return true;"
	ONMOUSEOUT="toggleImages('novosti_3', new Array('novosti_3', '../graphics/novosti_0.gif'), '', new Array('novosti_3', '../graphics/novosti_3.gif')); return true;"
	ONMOUSEUP="selected='novosti_3'; changeImages('novosti_3', '../graphics/novosti_0.gif'); return true;">
	<IMG SRC="../graphics/novosti_3.gif" ALT="Новости" NAME="novosti_3" WIDTH=65 HEIGHT=65 BORDER=0 usemap="#novosti_3Map"></A></td>
    <% } else {%>
    <td><IMG SRC="../graphics/novosti_0.gif" ALT="Новости"  WIDTH=65 HEIGHT=65 BORDER=0></td>
    <% } %>


	<% if(_renderParam.equalsIgnoreCase("bestseller")==false) {%>
    <td><A HREF="#"
    ONMOUSEOVER="toggleImages('', new Array('bestseller_3', '../graphics/bestseller_2.gif')); return true;"
    ONMOUSEOUT="toggleImages('bestseller_3', new Array('bestseller_3', '../graphics/bestseller_0.gif'), '', new Array('bestseller_3', '../graphics/bestseller_3.gif')); return true;"
    ONMOUSEUP="selected='bestseller_3'; changeImages('bestseller_3', '../graphics/bestseller_0.gif'); return true;">
    <IMG SRC="../graphics/bestseller_3.gif" ALT="Бестселлеры" NAME="bestseller_3" WIDTH=65 HEIGHT=65 BORDER=0 usemap="#bestseller_3Map"></A></td>
    <% } else {%>
    <td><IMG SRC="../graphics/bestseller_0.gif" ALT="Бестселлеры"  WIDTH=65 HEIGHT=65 BORDER=0></td>
    <% } %>

    <% if(_renderParam.equalsIgnoreCase("novinki")==false) {%>
    <td><A HREF="#"
    ONMOUSEOVER="toggleImages('', new Array('novinki_3', '../graphics/novinki_2.gif')); return true;"
    ONMOUSEOUT="toggleImages('novinki_3', new Array('novinki_3', '../graphics/novinki_0.gif'), '', new Array('novinki_3', '../graphics/novinki_3.gif')); return true;"
    ONMOUSEUP="selected='novinki_3'; changeImages('novinki_3', '../graphics/novinki_0.gif'); return true;">
    <IMG SRC="../graphics/novinki_3.gif" ALT="Новинки" NAME="novinki_3" WIDTH=65 HEIGHT=65 BORDER=0 usemap="#novinki_3Map"></A></td>
    <% } else {%>
    <td><IMG SRC="../graphics/novinki_0.gif" ALT="Новинки"  WIDTH=65 HEIGHT=65 BORDER=0></td>
    <% } %>

    <% if(_renderParam.equalsIgnoreCase("rasprod")==false) {%>
 	<td><A HREF="#"
	ONMOUSEOVER="toggleImages('', new Array('rasprod_3', '../graphics/rasprod_2.gif')); return true;"
	ONMOUSEOUT="toggleImages('rasprod_3', new Array('rasprod_3', '../graphics/rasprod_0.gif'), '', new Array('rasprod_3', '../graphics/rasprod_3.gif')); return true;"
	ONMOUSEUP="selected='rasprod_3'; changeImages('rasprod_3', '../graphics/rasprod_0.gif'); return true;">
	<IMG SRC="../graphics/rasprod_3.gif" ALT="Распродажа" NAME="rasprod_3" WIDTH=65 HEIGHT=65 BORDER=0 usemap="#rasprod_3Map"></A></td>
    <% } else {%>
    <td><IMG SRC="../graphics/rasprod_0.gif" ALT="Распродажа"  WIDTH=65 HEIGHT=65 BORDER=0></td>
    <% } %>


    <% if(_renderParam.equalsIgnoreCase("zagruzka")==false) {%>
    <td><A HREF="#"
	ONMOUSEOVER="toggleImages('', new Array('zagruzka_3', '../graphics/zagruzka_2.gif')); return true;"
	ONMOUSEOUT="toggleImages('zagruzka_3', new Array('zagruzka_3', '../graphics/zagruzka_0.gif'), '', new Array('zagruzka_3', '../graphics/zagruzka_3.gif')); return true;"
	ONMOUSEUP="selected='zagruzka_3'; changeImages('zagruzka_3', '../graphics/zagruzka_0.gif'); return true;">
	<IMG SRC="../graphics/zagruzka_3.gif" ALT="Загрузка" NAME="zagruzka_3" WIDTH=65 HEIGHT=65 BORDER=0 usemap="#zagruzka_3Map"></A></td>
    <% } else {%>
    <td><IMG SRC="../graphics/zagruzka_0.gif" ALT="Загрузка"  WIDTH=65 HEIGHT=65 BORDER=0></td>
    <% } %>


    <% if(_renderParam.equalsIgnoreCase("help")==false) {%>
 	<td><A HREF="#"
	ONMOUSEOVER="toggleImages('', new Array('help_3', '../graphics/help_2.gif')); return true;"
	ONMOUSEOUT="toggleImages('help_3', new Array('help_3', '../graphics/help_0.gif'), '', new Array('help_3', '../graphics/help_3.gif')); return true;"
	ONMOUSEUP="selected='help_3'; changeImages('help_3', '../graphics/help_0.gif'); return true;">
	<IMG SRC="../graphics/help_3.gif" ALT="Помощь" NAME="help_3" WIDTH=65 HEIGHT=65 BORDER=0 usemap="#help_3Map"></A></td>
    <% } else {%>
    <td><IMG SRC="../graphics/help_0.gif" ALT="Помощь"  WIDTH=65 HEIGHT=65 BORDER=0></td>
    <% } %>

    <% if(_renderParam.equalsIgnoreCase("kontakty")==false) {%>
 	<td><A HREF="#"
	ONMOUSEOVER="toggleImages('', new Array('kontakty_3', '../graphics/kontakty_2.gif')); return true;"
	ONMOUSEOUT="toggleImages('kontakty_3', new Array('kontakty_3', '../graphics/kontakty_0.gif'), '', new Array('kontakty_3', '../graphics/kontakty_3.gif')); return true;"
	ONMOUSEUP="selected='kontakty_3'; changeImages('kontakty_3', '../graphics/kontakty_0.gif'); return true;">
	<IMG SRC="../graphics/kontakty_3.gif" ALT="Контакты" NAME="kontakty_3" WIDTH=65 HEIGHT=65 BORDER=0 usemap="#kontakty_3Map"></A></td>
    <% } else {%>
    <td><IMG SRC="../graphics/kontakty_0.gif" ALT="Контакты"  WIDTH=65 HEIGHT=65 BORDER=0></td>
    <% } %>


    <% if(_renderParam.equalsIgnoreCase("korzina")==false) {%>
 	<td><A HREF="#"
	ONMOUSEOVER="toggleImages('', new Array('korzina_3', '../graphics/korzina_2.gif')); return true;"
	ONMOUSEOUT="toggleImages('korzina_3', new Array('korzina_3', '../graphics/korzina_0.gif'), '', new Array('korzina_3', '../graphics/korzina_3.gif')); return true;"
	ONMOUSEUP="selected='korzina_3'; changeImages('korzina_3', '../graphics/korzina_0.gif'); return true;">
	<IMG SRC="../graphics/korzina_3.gif" ALT="Корзина" NAME="korzina_3" WIDTH=65 HEIGHT=65 BORDER=0 usemap="#korzina_3Map"></A></td>
    <% } else {%>
    <td><IMG SRC="../graphics/korzina_0.gif" ALT="Корзина"  WIDTH=65 HEIGHT=65 BORDER=0></td>
    <% } %>

            </tr>
        </table>