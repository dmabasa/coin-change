<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
<style>           
.blue-button{
	background: #25A6E1;
	filter: progid: DXImageTransform.Microsoft.gradient( startColorstr='#25A6E1',endColorstr='#188BC0',GradientType=0);
	padding:3px 5px;
	color:#fff;
	font-family:'Helvetica Neue',sans-serif;
	font-size:12px;
	border-radius:2px;
	-moz-border-radius:2px;
	-webkit-border-radius:4px;
	border:1px solid #1A87B9
}  

.commonerrorblock {
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
}
</st
   
table {
  font-family: "Helvetica Neue", Helvetica, sans-serif;
   width: 50%;
}
th {
  background: SteelBlue;
  color: white;
}
 td,th{
                border: 1px solid gray;
                width: 25%;
                text-align: left;
                padding: 5px 10px;
            }
</style>


<script>
    function isNumber(evt) {
        var iKeyCode = (evt.which) ? evt.which : evt.keyCode
        if (iKeyCode != 46 && iKeyCode > 31 && (iKeyCode < 48 || iKeyCode > 57))
            return false;

        return true;
    }    
</script>

</head>
<body>
<form:form method="post" modelAttribute="noteAmountPurchaseAmount" action="${contextPath}/change">
<form:errors path="*" element="div" cssClass="commonerrorblock" />
<form:hidden path="purchaseAmount" />
<table>
		<tr>
			<th colspan="2">Amount due : R${noteAmountPurchaseAmount.purchaseAmount}</th>
		</tr>
		<tr>
          	<td><label>Purchase Amount:</label></td>
          	<td><label>R${noteAmountPurchaseAmount.purchaseAmount}</label></td>
        </tr>
		<tr>
			<td><label>Captured Amount:</label></td>
          	<td>
          	<form:input path="noteAmount" size="30" maxlength="30" onkeypress="javascript:return isNumber(event)">
          	</form:input>
          	</td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit"
				class="blue-button" /></td>
		</tr>
	</table> 
</form:form>
</body>
</html>
