<%@include file="include.html"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%AdminService service = new AdminService();
List<Book> books = service.readBooks();
%>
<div class="container">
	<h2>Add New Borrower</h2>
	<form method="post" action="addBorrower">
	${statusMessage}
		<br/>Enter Borrower Name: <input type="text" name="name"><br />
		<br/>Enter Borrower Address: <input type="text" name="address"><br />
		<br/>Enter Borrower Phone: <input type="text" name="phone"><br />
		<button type="submit" class="btn btn-primary btn-md">Save borrower</button>
	</form>
</div>