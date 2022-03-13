<%@ page contentType="text/html; charset=windows-1251" language="java" import="java.util.*,
                                                                               java.sql.*,
                                                                               beans.BasicDataSourceDB" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1251">
<link href="CSS/style.css" rel="stylesheet" type="text/css">
<%@ taglib uri='db-tags' prefix='sql' %>

<%! String _renderParam="";
    Properties props_catalogcnt;
%>
<%
    _renderParam=request.getParameter("render");
    _renderParam=(_renderParam==null)?"#":_renderParam;

    props_catalogcnt=new Properties();
%>
<sql:connection id="conn_catlogcnt" dataSource="ds"></sql:connection>
<sql:statement id="stmt_catlogcnt" conn="conn_catlogcnt">
        <sql:query>
            SELECT * from catalogcnt
        </sql:query>

    <sql:resultSet id="rset_catlogcnt">
         <%
             props_catalogcnt.setProperty(rset_catlogcnt.getString("razdel"),rset_catlogcnt.getString("cnt"));
         %>
    </sql:resultSet>
</sql:statement>
<sql:closeConnection conn="conn_catlogcnt"/>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td height="45" colspan="3" class="m_title_l">Каталог литературы:</td>
  </tr>
          <tr>
            <td width="7%" height="20" align="center" valign="bottom" class="m_catalog1"><img src="../graphics/w_1.gif" width="6" height="3"></td>

            <% if(_renderParam.equalsIgnoreCase("tehno")==false) {%>
            <td height="78%" colspan="1"  onClick="javascript:go('shoping-action.dll?render=tehno')" onMouseOver="MM_changeProp('span1','','style.fontWeight','bold','SPAN');MM_changeProp('span1_a','','style.color','#000099','SPAN')" onMouseOut="MM_changeProp('span1','','style.fontWeight','normal','SPAN');MM_changeProp('span1_a','','style.color','#3399ff','SPAN')"><span class="m_catalog1" id="span1">Техническая</span></td>
            <td width="15%" height="20" class="kolknig"><span id="span1_a"><%=props_catalogcnt.getProperty("tehno")%></span></td>
            <% } else {%>
			<td height="78%" colspan="1"><span class="m_catalog1_a">Техническая</span></td>
            <td width="15%" height="20" class="kolknig_a"><%=props_catalogcnt.getProperty("tehno")%></td>
            <% } %>

          </tr>
          <tr>
            <td width="7%" height="20" class="m_catalog1"></td>

            <% if(_renderParam.equalsIgnoreCase("comp")==false) {%>
            <td width="78%" height="20" onClick="javascript:go('shoping-action.dll?render=comp')" onMouseOver="MM_changeProp('span1_1','','style.fontWeight','bold','SPAN');MM_changeProp('span1_1_a','','style.color','#000099','SPAN')" onMouseOut="MM_changeProp('span1_1','','style.fontWeight','normal','SPAN');MM_changeProp('span1_1_a','','style.color','#3399ff','SPAN')"><span class="m_catalog2" id="span1_1">Компьютерная</span></td>
            <td width="15%" height="20" class="kolknig"><span id="span1_1_a"><%=props_catalogcnt.getProperty("comp")%></span></td>
            <% } else {%>
            <td width="78%" height="20"><span class="m_catalog2_a">Компьютерная</span></td>
            <td width="15%" height="20" class="kolknig_a"><%=props_catalogcnt.getProperty("comp")%></td>
            <% } %>

          </tr>
          <tr>
            <td width="7%" height="20"></td>

            <% if(_renderParam.equalsIgnoreCase("rteh")==false) {%>
            <td width="78%" height="20" onClick="javascript:go('shoping-action.dll?render=rteh')" onMouseOver="MM_changeProp('span1_2','','style.fontWeight','bold','SPAN');MM_changeProp('span1_2_a','','style.color','#000099','SPAN')" onMouseOut="MM_changeProp('span1_2','','style.fontWeight','normal','SPAN');MM_changeProp('span1_2_a','','style.color','#3399ff','SPAN')"><span class="m_catalog2" id="span1_2">Радиотехническая</span></td>
            <td width="15%" height="20" class="kolknig"><span id="span1_2_a"><%=props_catalogcnt.getProperty("rteh")%></span></td>
            <% } else {%>
            <td width="78%" height="20"><span class="m_catalog2_a">Радиотехническая</span></td>
            <td width="15%" height="20" class="kolknig_a"><%=props_catalogcnt.getProperty("rteh")%></td>
            <% } %>
          </tr>
          <tr>
            <td width="7%" height="20"></td>

            <% if(_renderParam.equalsIgnoreCase("ateh")==false) {%>
           	<td width="78%" height="20" onClick="javascript:go('shoping-action.dll?render=ateh')" onMouseOver="MM_changeProp('span1_3','','style.fontWeight','bold','SPAN');MM_changeProp('span1_3_a','','style.color','#000099','SPAN')" onMouseOut="MM_changeProp('span1_3','','style.fontWeight','normal','SPAN');MM_changeProp('span1_3_a','','style.color','#3399ff','SPAN')"><span class="m_catalog2" id="span1_3">Автомототехника</span></td>
            <td width="15%" height="20" class="kolknig"><span id="span1_3_a"><%=props_catalogcnt.getProperty("ateh")%></span></td>
            <% } else {%>
           	<td width="78%" height="20"><span class="m_catalog2_a">Автомототехника</span></td>
            <td width="15%" height="20" class="kolknig_a"><%=props_catalogcnt.getProperty("ateh")%></td>
            <% } %>
          </tr>

          <tr>
            <td width="7%" height="20"></td>

            <% if(_renderParam.equalsIgnoreCase("build")==false) {%>
           	<td width="78%" height="20" onClick="javascript:go('shoping-action.dll?render=build')" onMouseOver="MM_changeProp('span1_4','','style.fontWeight','bold','SPAN');MM_changeProp('span1_4_a','','style.color','#000099','SPAN')" onMouseOut="MM_changeProp('span1_4','','style.fontWeight','normal','SPAN');MM_changeProp('span1_4_a','','style.color','#3399ff','SPAN')"><span class="m_catalog2" id="span1_4">Строительство</span></td>
            <td width="15%" height="20" class="kolknig"><span id="span1_4_a"><%=props_catalogcnt.getProperty("build")%></span></td>
            <% } else {%>
           	<td width="78%" height="20"><span class="m_catalog2_a">Строительство</span></td>
            <td width="15%" height="20" class="kolknig_a"><%=props_catalogcnt.getProperty("build")%></td>
            <% } %>
          </tr>


          <tr>
            <td width="7%" height="8"></td>
            <td width="78%" height="8" class="m_catalog2"></td>
            <td width="15%" height="8" class="kolknig"></td>
          </tr>
          <tr>
            <td width="7%" height="20" align="center" valign="bottom" class="m_catalog1"><img src="../graphics/w_1.gif" width="6" height="3"></td>

            <% if(_renderParam.equalsIgnoreCase("science")==false) {%>
            <td height="78%" onClick="javascript:go('shoping-action.dll?render=science')" onMouseOver="MM_changeProp('span2','','style.fontWeight','bold','SPAN');MM_changeProp('span2_a','','style.color','#000099','SPAN')" onMouseOut="MM_changeProp('span2','','style.fontWeight','normal','SPAN');MM_changeProp('span2_a','','style.color','#3399ff','SPAN')"><span class="m_catalog1" id="span2">Научная</span></td>
            <td width="15%" height="20" class="kolknig"><span id="span2_a"><%=props_catalogcnt.getProperty("science")%></span></td>
            <% } else {%>
            <td height="78%"><span class="m_catalog1_a">Научная</span></td>
            <td width="15%" height="20" class="kolknig_a"><%=props_catalogcnt.getProperty("science")%></td>
            <% } %>
          </tr>
          <tr>
            <td width="7%" height="20"></td>

            <% if(_renderParam.equalsIgnoreCase("econom")==false) {%>
           	<td width="78%" height="20" onClick="javascript:go('shoping-action.dll?render=econom')" onMouseOver="MM_changeProp('span2_1','','style.fontWeight','bold','SPAN');MM_changeProp('span2_1_a','','style.color','#000099','SPAN')" onMouseOut="MM_changeProp('span2_1','','style.fontWeight','normal','SPAN');MM_changeProp('span2_1_a','','style.color','#3399ff','SPAN')"><span class="m_catalog2" id="span2_1">Экономика</span></td>
            <td width="15%" height="20" class="kolknig"><span id="span2_1_a"><%=props_catalogcnt.getProperty("econom")%></span></td>
            <% } else {%>
            <td width="78%" height="20"><span class="m_catalog2_a">Экономика</span></td>
            <td width="15%" height="20" class="kolknig_a"><%=props_catalogcnt.getProperty("econom")%></td>
            <% } %>
          </tr>
          <tr>
            <td width="7%" height="20"></td>

            <% if(_renderParam.equalsIgnoreCase("med")==false) {%>
           	<td width="78%" height="20" onClick="javascript:go('shoping-action.dll?render=med')" onMouseOver="MM_changeProp('span2_2','','style.fontWeight','bold','SPAN');MM_changeProp('span2_2_a','','style.color','#000099','SPAN')" onMouseOut="MM_changeProp('span2_2','','style.fontWeight','normal','SPAN');MM_changeProp('span2_2_a','','style.color','#3399ff','SPAN')"><span class="m_catalog2" id="span2_2">Медицина</span></td>
            <td width="15%" height="20" class="kolknig"><span id="span2_2_a"><%=props_catalogcnt.getProperty("med")%></span></td>
            <% } else {%>
           	<td width="78%" height="20"><span class="m_catalog2_a">Медицина</span></td>
            <td width="15%" height="20" class="kolknig_a"><%=props_catalogcnt.getProperty("med")%></td>
            <% } %>
          </tr>
		  <tr>
            <td width="7%" height="20"></td>

            <% if(_renderParam.equalsIgnoreCase("psych")==false) {%>
           	<td width="78%" height="20" onClick="javascript:go('shoping-action.dll?render=psych')" onMouseOver="MM_changeProp('span2_3','','style.fontWeight','bold','SPAN');MM_changeProp('span2_3_a','','style.color','#000099','SPAN')" onMouseOut="MM_changeProp('span2_3','','style.fontWeight','normal','SPAN');MM_changeProp('span2_3_a','','style.color','#3399ff','SPAN')"><span class="m_catalog2" id="span2_3">Психология</span></td>
            <td width="15%" height="20" class="kolknig"><span id="span2_3_a"><%=props_catalogcnt.getProperty("psych")%></span></td>
            <% } else {%>
           	<td width="78%" height="20"><span class="m_catalog2_a" id="span2_3">Психология</span></td>
            <td width="15%" height="20" class="kolknig_a"><%=props_catalogcnt.getProperty("psych")%></td>
            <% } %>

          </tr>
		  <tr>
            <td width="7%" height="20"></td>

            <% if(_renderParam.equalsIgnoreCase("history")==false) {%>
           	<td width="78%" height="20" onClick="javascript:go('shoping-action.dll?render=history')" onMouseOver="MM_changeProp('span2_4','','style.fontWeight','bold','SPAN');MM_changeProp('span2_4_a','','style.color','#000099','SPAN')" onMouseOut="MM_changeProp('span2_4','','style.fontWeight','normal','SPAN');MM_changeProp('span2_4_a','','style.color','#3399ff','SPAN')"><span class="m_catalog2" id="span2_4">История</span></td>
            <td width="15%" height="20" class="kolknig"><span id="span2_4_a"><%=props_catalogcnt.getProperty("history")%></span></td>
            <% } else {%>
           	<td width="78%" height="20"><span class="m_catalog2_a">История</span></td>
            <td width="15%" height="20" class="kolknig_a"><%=props_catalogcnt.getProperty("history")%></td>
            <% } %>
          </tr>
		  <tr>
            <td width="7%" height="20"></td>

            <% if(_renderParam.equalsIgnoreCase("philosophy")==false) {%>
           	<td width="78%" height="20" onClick="javascript:go('shoping-action.dll?render=philosophy')" onMouseOver="MM_changeProp('span2_5','','style.fontWeight','bold','SPAN');MM_changeProp('span2_5_a','','style.color','#000099','SPAN')" onMouseOut="MM_changeProp('span2_5','','style.fontWeight','normal','SPAN');MM_changeProp('span2_5_a','','style.color','#3399ff','SPAN')"><span class="m_catalog2" id="span2_5">Философия</span></td>
            <td width="15%" height="20" class="kolknig"><span id="span2_5_a"><%=props_catalogcnt.getProperty("philosophy")%></span></td>
            <% } else {%>
            <td width="78%" height="20"><span class="m_catalog2_a">Философия</span></td>
            <td width="15%" height="20" class="kolknig_a"><%=props_catalogcnt.getProperty("philosophy")%></td>
            <% } %>
          </tr>
		  <tr>
            <td width="7%" height="8"></td>
            <td width="78%" height="8" class="m_catalog2"></td>
            <td width="15%" height="8" class="kolknig"></td>
          </tr>
		  <tr>
            <td width="7%" height="20" align="center" valign="bottom" class="m_catalog1"><img src="../graphics/w_1.gif" width="6" height="3"></td>

            <% if(_renderParam.equalsIgnoreCase("doc")==false) {%>
			<td height="78%" onClick="javascript:go('shoping-action.dll?render=doc')" onMouseOver="MM_changeProp('span3','','style.fontWeight','bold','SPAN');MM_changeProp('span3_a','','style.color','#000099','SPAN')" onMouseOut="MM_changeProp('span3','','style.fontWeight','normal','SPAN');MM_changeProp('span3_a','','style.color','#3399ff','SPAN')"><span class="m_catalog1" id="span3">Документ-ая</span></td>
            <td width="15%" height="20" class="kolknig"><span id="span3_a"><%=props_catalogcnt.getProperty("doc")%></span></td>
            <% } else {%>
            <td height="78%"><span class="m_catalog1_a">Документ-ая</span></td>
            <td width="15%" height="20" class="kolknig_a"><%=props_catalogcnt.getProperty("doc")%></td>
            <% } %>

          </tr>
		  <tr>
            <td width="7%" height="8"></td>
            <td width="78%" height="8" class="m_catalog2"></td>
            <td width="15%" height="8" class="kolknig"></td>
          </tr>
		  <tr>
            <td width="7%" height="20" align="center" valign="bottom" class="m_catalog1"><img src="../graphics/w_1.gif" width="6" height="3"></td>

            <% if(_renderParam.equalsIgnoreCase("child")==false) {%>
			<td height="78%" onClick="javascript:go('shoping-action.dll?render=child')" onMouseOver="MM_changeProp('span4','','style.fontWeight','bold','SPAN');MM_changeProp('span4_a','','style.color','#000099','SPAN')" onMouseOut="MM_changeProp('span4','','style.fontWeight','normal','SPAN');MM_changeProp('span4_a','','style.color','#3399ff','SPAN')"><span class="m_catalog1" id="span4">Детская</span></td>
            <td width="15%" height="20" class="kolknig"><span id="span4_a"><%=props_catalogcnt.getProperty("child")%></span></td>
            <% } else {%>
			<td height="78%"><span class="m_catalog1_a">Детская</span></td>
            <td width="15%" height="20" class="kolknig_a"><%=props_catalogcnt.getProperty("child")%></td>
            <% } %>


          </tr>
		  <tr>
            <td width="7%" height="8"></td>
            <td width="78%" height="8" class="m_catalog2"></td>
            <td width="15%" height="8" class="kolknig"></td>
          </tr>

</table>
