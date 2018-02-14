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
</head>
<body>

<form:form method="GET" action="${contextPath}/reset">
<!-- </br> -->
<h3>Breakdown of cash to dispense is:</h3>
<c:if test="${!empty changeList}">
	<table class="tg">
	<tr>
		<th width="80">Number of coins/notes</th>
		<th width="120">Amount</th>
	</tr>
	<c:forEach items="${changeList}" var="change">
		<tr>
			<td>${change.count}</td>
			<td>${change.denominator}</td>
		</tr>
	</c:forEach>
	
	<tr>
			<td colspan="2"><input type="submit"
				class="blue-button" value="Reset"/></td>
		</tr>
	</table>
</c:if>
</form:form>
</body>
</html>
