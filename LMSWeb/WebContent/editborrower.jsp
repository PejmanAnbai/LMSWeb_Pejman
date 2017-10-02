<%@page import="com.gcit.lms.entity.Borrower"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="java.util.List"%>
<%@include file="include.html"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%AdminService service = new AdminService();
Borrower borrower = service.readBorrowerByPK(Integer.parseInt(request.getParameter("cardNo")));
List<Book> books = service.readBooks();
%>

<div class="container">
	<h1>Edit Borrower</h1>
	<form method="post" action="editBorrower">
		${statusMessage}
		<br/>Enter Borrower Name to Edit: <input type="text" name="name" value="<%=borrower.getName()%>"><br />
		<br/>Enter Borrower Address to Edit: <input type="text" name="address" value="<%=borrower.getAddress()%>"><br />
		<br/>Enter Borrower Phone to Edit: <input type="text" name="phone" value="<%=borrower.getPhone()%>"><br />
		<input type="hidden" name="cardNo" value="<%=borrower.getCardNo()%>">
		<button type="submit" class="btn btn-primary btn-md">Update Borrower</button>
	</form>

</div>