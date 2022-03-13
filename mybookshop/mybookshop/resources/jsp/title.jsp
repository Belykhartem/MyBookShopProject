<%@ page contentType="text/html; charset=windows-1251" language="java" import="java.util.*,
                                                                               java.sql.*,
                                                                               beans.BasicDataSourceDB" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1251">
<link href="CSS/style.css" rel="stylesheet" type="text/css">

<%! String _renderParam="";
    String _renderValue="";
    ResourceBundle _bandle_menu;
    Properties _props_catalog;
%>
<%
    _renderParam=request.getParameter("render");
//    _renderParam=(_renderParam==null)?"":_renderParam;

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
            _renderValue="";
        }
    _renderValue=_renderValue.replaceAll(" ","&nbsp;");


%>


<table width="100%"  border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="600" height="9"></td>
        <td width="9" height="9" align="center"><img src="../graphics/z_0_g.gif" width="9" height="1"></td>
        <td height="9"></td>
        <td width="9" height="9" align="center"><img src="../graphics/z_0_g.gif" width="9" height="1"></td>
        <td width="5" height="9"></td>
        </tr>
      <tr>
        <td width="600" height="9" align="center"><img src="../graphics/z_0_v.gif" width="1" height="9"></td>
        <td width="9" height="9" align="center"><img src="../graphics/z_lt.gif"></td>
        <td height="9" align="center" background="../graphics/z_t.gif"><img src="../graphics/z_0_v.gif" width="1" height="9"></td>
        <td width="9" height="9" align="center"><img src="../graphics/z_rt.gif" width="9" height="9"></td>
        <td width="5" height="9"><img src="../graphics/z_0_v.gif" width="1" height="9"></td>
        </tr>
      <tr>
        <td width="600" height="17">&nbsp;</td>
        <td width="9" align="center" background="../graphics/z_l.gif"><img src="../graphics/z_0_g.gif" width="9" height="1"></td>
        <td class="m_title_14">&nbsp;<%=_renderValue%></td>
        <td width="9" align="center" background="../graphics/z_r.gif"><img src="../graphics/z_0_g.gif" width="9" height="1"></td>
        <td width="5"></td>
        </tr>
      <tr>
        <td width="600" height="9" align="center"><img src="../graphics/z_0_v.gif" width="1" height="9"></td>
        <td width="9" height="9" align="center"><img src="../graphics/z_lb.gif"></td>
        <td height="9" align="center" background="../graphics/z_b.gif"><img src="../graphics/z_0_v.gif" width="1" height="9"></td>
        <td width="9" height="9" align="center"><img src="../graphics/z_rb.gif" width="9" height="9"></td>
        <td width="5" height="9"><img src="../graphics/z_0_v.gif" width="1" height="9"></td>
        </tr>
      <tr>
        <td width="600" height="9"></td>
        <td width="9" height="9" align="center"><img src="../graphics/z_0_g.gif" width="9" height="1"></td>
        <td height="9" align="center"></td>
        <td width="9" height="9" align="center"><img src="../graphics/z_0_g.gif" width="9" height="1"></td>
        <td width="5" height="9"></td>
  </tr>
    </table>
