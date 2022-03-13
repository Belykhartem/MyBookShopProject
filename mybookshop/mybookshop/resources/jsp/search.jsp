<%@ page contentType="text/html; charset=windows-1251" language="java" import="java.util.*,
                                                                               java.sql.*,
                                                                               beans.BasicDataSourceDB" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1251">
<link href="CSS/style.css" rel="stylesheet" type="text/css">

<table width="100%"  border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="5%"></td>
        <td width="86%" valign="bottom" class="m_title_menu">&nbsp;</td>
        <td width="5%" height="25"></td>
      </tr>
      <tr>
        <td></td>
        <td align="center"><form name="form2" method="post" action="">
          <input  type="text" id="inp" value="Поиск" name="find"  size="16"
		    onblur="if (value=='' &amp;&amp; !this.enter) value='Поиск'; else this.enter=true;"
			onfocus="if (! this.enter) value='';">

			<span class="kolknig">&nbsp;</span><a href="#"><img src="../graphics/lupa.gif" width="16" height="16" border="0" align="middle"></a>
        </form></td>
        <td height="29"></td>
      </tr>
    </table>