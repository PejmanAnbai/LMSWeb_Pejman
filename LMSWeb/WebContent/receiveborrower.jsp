<%@include file="include.html"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.service.BorrowerService"%>
<%BorrowerService service = new BorrowerService();
%>
<div class="container">
	<h2>Welcome to Library</h2>
	<form method="post" action="receiveBorrower">
	${statusMessage}
		<br/>Enter Your Card Number: <input type="text" name="cardNo"><br />
		<button type="submit" class="btn btn-primary btn-md">Enter</button>
	</form>
</div>