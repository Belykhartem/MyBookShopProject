<%@ page contentType="text/html; charset=windows-1251" language="java" import="java.util.*,
                                                                               java.sql.*,
                                                                               beans.BasicDataSourceDB,
                                                                               java.text.NumberFormat,
                                                                               java.text.ParseException" errorPage="error.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1251">
<link href="CSS/style.css" rel="stylesheet" type="text/css">

<%@ taglib uri='utilities-tags' prefix='util' %>
<%@ taglib uri='tokens-tags' prefix='tokens' %>
<%@ taglib uri='db-tags' prefix='sql' %>
<%@ taglib uri='app-tags' prefix='app' %>

<table width="100%"  border="0" cellspacing="0" cellpadding="0">
     <tr>
       <td width="10%"></td>
       <%
           if(request.getAttribute("renderParam")!=null){
       %>
       <td width="35%" class="voterz"><a href="shoping-action.dll?render=<%=request.getAttribute("renderParam")%>&scroll=<%=request.getAttribute("pageNumber")%>">Продолжить покупки</a> </td>
       <%
           }else{
       %>
       <td width="35%" class="voterz"><a href="shoping-action.dll?render=novinki">Продолжить покупки</a> </td>
       <%
           }
       %>
       <td width="10%"></td>
       <td width="35%" class="voterz"><a href="#">Оформить заказ</a> </td>
       <td width="10%"></td>
     </tr>
</table>


<%
    double total=0.0;
    int cnt=0;
    NumberFormat nform= NumberFormat.getInstance(Locale.US);
    nform.setMinimumIntegerDigits(1);
    nform.setMinimumFractionDigits(2);
    nform.setMaximumFractionDigits(2);

    NumberFormat nform_int= NumberFormat.getInstance(Locale.US);
    nform_int.setMinimumIntegerDigits(1);
    nform_int.setMinimumFractionDigits(0);
    nform_int.setMaximumFractionDigits(0);
%>


<form name="form1" method="post" action="shoping-action.dll?render=korzina&refresh=yes">

<table width="100%" height="165"  border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td height="165" align="center">
	<table width="94%"  border="0" cellpadding="0" cellspacing="0">
    <tr>
    <td width="100%" height="1" colspan="3" background="../graphics/razd.gif"></td>
  </tr>
</table>
	<table width="94%"  border="0" cellpadding="2" cellspacing="1">
  <tr>
    <td height="25" colspan="5" align="left" class="tk"></td>
    <td width="15%" align="right" class="tk"><a href="shoping-action.dll?render=korzina&remove=all">Очистить&nbsp;всё</a></td>
    <td width="2%" align="center"><a href="shoping-action.dll?render=korzina&remove=all"><img src="../graphics/remove.gif" width="13" height="13" border="0"></a></td>
  </tr>
</table>
	<table width="94%"  border="0" cellpadding="2" cellspacing="1" bgcolor="#99CCFF">
  <tr class="tk">
    <td width="4%" height="21" align="center">№</td>
    <td width="33%" align="left">Наименование</td>
    <td width="33%" align="left">Автор</td>
    <td width="10%" align="center">Размер,&nbsp;Кб</td>
    <td width="10%" align="center">Кол-во</td>
    <td width="8%" align="center">&nbsp;Цена,&nbsp;$&nbsp; </td>
    <td width="2%">&nbsp;</td>
  </tr>
  <app:iterateCart id='cartItem'>
  <%
      float price=cartItem.getPrice();

      int amount=0;
//      try {
          amount = nform_int.parse(nform_int.format(cartItem.getAmount())).intValue();
//      } catch (ParseException e) {
//          amount=1;
//      }

      String txtName="txt_"+cartItem.getSku();
      cnt=cnt+1;
  %>
  <tr bgcolor="#FFFFFF" class="fk">
    <td align="center"><%=cnt%></td>
    <td align="left"><%=cartItem.getName()%></td>
    <td align="left"><%=cartItem.getAuthor()%></td>
    <td align="center"><%=cartItem.getSize()%></td>
    <td align="center"><input name="<%=txtName%>" type="text" value="<%=amount%>" size="3" maxlength="3"></td>
    <td align="center"><%=nform.format(price)%></td>
    <td align="center"><a href="shoping-action.dll?render=korzina&remove=<%=cartItem.getSku()%>"><img src="../graphics/remove_row.gif" alt="Удалить" width="13" height="13" border="0"></a></td>
  </tr>
     <%
         total+=price*amount;
     %>
  </app:iterateCart>
  <tr bgcolor="#FFFFFF">
    <td colspan="5" align="left" class="tk">ИТОГО:</td>
    <td align="center" bgcolor="#99ccFF" class="tk"><%=nform.format(total)%></td>
    <td class="tk"><input name="refresh" type="image" value="yes" src="../graphics/refresh.gif" alt="Пересчитать"></td>
  </tr>
</table>



<table width="94%"  border="0" cellpadding="2" cellspacing="1">
  <tr>
    <td height="25" colspan="5" align="right" class="ck">После изменения количества нажмите кнопку </td>
    <td width="2%" class="tk"><img src="../graphics/refresh_gray.gif" width="13" height="13"></td>
    <td width="46%" align="left"><span class="ck">&quot;пересчитать&quot; справа от итоговой суммы </span></td>
  </tr>
</table>

<table width="94%"  border="0" cellpadding="0" cellspacing="0">
    <tr>
    <td width="100%" height="1" colspan="3" background="../graphics/razd.gif"></td>
  </tr>
</table>


	</td>
  </tr>
</table>
</form>


<p>&nbsp;</p>

