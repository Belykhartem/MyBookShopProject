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

<table width="604" height="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td align="center" bgcolor="#99ccff"><table width="600" border="0" cellspacing="0" cellpadding="0">
          <tr align="center">
            <td width="74"><img src="../graphics/g_1.gif" width="65" height="1"></td>
            <td width="76"><img src="../graphics/g_1.gif" width="65" height="1"></td>
            <td width="77"><img src="../graphics/g_1.gif" width="65" height="1"></td>
            <td width="73"><img src="../graphics/g_1.gif" width="65" height="1"></td>
            <td width="78"><img src="../graphics/g_1.gif" width="65" height="1"></td>
            <td width="76"><img src="../graphics/g_1.gif" width="65" height="1"></td>
            <td width="69"><img src="../graphics/g_1.gif" width="65" height="1"></td>
            <td width="77"><img src="../graphics/g_1.gif" width="65" height="1"></td>
          </tr>
          <tr align="center">

            <% if(_renderParam.equalsIgnoreCase("novosti")==false) {%>
            <td><span class="m_title_menu" id="span_m_1"  onClick="javascript:go('shoping-action.dll?render=novosti')" onMouseOver="MM_changeProp('span_m_1','','style.textDecoration','underline','SPAN')" onMouseOut="MM_changeProp('span_m_1','','style.textDecoration','none','SPAN')">Новости</span></td>
            <% } else {%>
            <td><span class="m_title_menu_a">Новости</span></td>
            <% } %>

            <% if(_renderParam.equalsIgnoreCase("bestseller")==false) {%>
            <td><span class="m_title_menu" id="span_m_2" onClick="javascript:go('shoping-action.dll?render=bestseller')" onMouseOver="MM_changeProp('span_m_2','','style.textDecoration','underline','SPAN')" onMouseOut="MM_changeProp('span_m_2','','style.textDecoration','none','SPAN')">Бестселлеры</span></td>
            <% } else {%>
            <td><span class="m_title_menu_a">Бестселлеры</span></td>
            <% } %>

            <% if(_renderParam.equalsIgnoreCase("novinki")==false) {%>
            <td><span class="m_title_menu" id="span_m_3" onClick="javascript:go('shoping-action.dll?render=novinki')" onMouseOver="MM_changeProp('span_m_3','','style.textDecoration','underline','SPAN')" onMouseOut="MM_changeProp('span_m_3','','style.textDecoration','none','SPAN')">Новинки</span></td>
            <% } else {%>
            <td><span class="m_title_menu_a">Новинки</span></td>
            <% } %>

            <% if(_renderParam.equalsIgnoreCase("rasprod")==false) {%>
            <td><span class="m_title_menu" id="span_m_4" onClick="javascript:go('shoping-action.dll?render=rasprod')" onMouseOver="MM_changeProp('span_m_4','','style.textDecoration','underline','SPAN')" onMouseOut="MM_changeProp('span_m_4','','style.textDecoration','none','SPAN')">Распродажа</span></td>
            <% } else {%>
            <td><span class="m_title_menu_a">Распродажа</span></td>
            <% } %>

            <% if(_renderParam.equalsIgnoreCase("zagruzka")==false) {%>
            <td><span class="m_title_menu" id="span_m_5" onClick="javascript:go('shoping-action.dll?render=zagruzka')" onMouseOver="MM_changeProp('span_m_5','','style.textDecoration','underline','SPAN')" onMouseOut="MM_changeProp('span_m_5','','style.textDecoration','none','SPAN')">Загрузка</span></td>
            <% } else {%>
            <td><span class="m_title_menu_a">Загрузка</span></td>
            <% } %>


            <% if(_renderParam.equalsIgnoreCase("help")==false) {%>
            <td><span class="m_title_menu" id="span_m_6" onClick="javascript:go('shoping-action.dll?render=help')" onMouseOver="MM_changeProp('span_m_6','','style.textDecoration','underline','SPAN')" onMouseOut="MM_changeProp('span_m_6','','style.textDecoration','none','SPAN')">Помощь</span></td>
            <% } else {%>
            <td><span class="m_title_menu_a">Помощь</span></td>
            <% } %>

            <% if(_renderParam.equalsIgnoreCase("kontakty")==false) {%>
            <td><span class="m_title_menu" id="span_m_7" onClick="javascript:go('shoping-action.dll?render=kontakty')" onMouseOver="MM_changeProp('span_m_7','','style.textDecoration','underline','SPAN')" onMouseOut="MM_changeProp('span_m_7','','style.textDecoration','none','SPAN')">Контакты</span></td>
            <% } else {%>
            <td><span class="m_title_menu_a">Контакты</span></td>
            <% } %>

            <% if(_renderParam.equalsIgnoreCase("korzina")==false) {%>
            <td><span class="m_title_menu" id="span_m_8" onClick="javascript:go('shoping-action.dll?render=korzina')" onMouseOver="MM_changeProp('span_m_8','','style.textDecoration','underline','SPAN')" onMouseOut="MM_changeProp('span_m_8','','style.textDecoration','none','SPAN')">Корзина</span></td>
            <% } else {%>
            <td><span class="m_title_menu_a">Корзина</span></td>
            <% } %>

          </tr>
        </table></td>
      </tr>
    </table>
