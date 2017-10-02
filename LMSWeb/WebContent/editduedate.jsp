<%@page import="com.gcit.lms.entity.Borrower"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="com.gcit.lms.entity.LibraryBranch"%>
<%@page import="com.gcit.lms.entity.BookLoans"%>
<%@page import="java.util.List"%>
<%@include file="include.html"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%
	AdminService service = new AdminService();
	BookLoans bookLoans = service.readBookLoanByPK(Integer.parseInt(request.getParameter("bookId")),
			Integer.parseInt(request.getParameter("branchId")),
			Integer.parseInt(request.getParameter("borrowerId")));
%>

<div class="container">
	<h1>Edit DueDate</h1>
	<form method="post" action="editBookLoans">
		${statusMessage} <br />Enter a new due date: <input type="text"
			name="duedate" value=""><br /> <input
			type="hidden" name="bookId"
			value="<%=bookLoans.getBook().getBookId()%>"> <input
			type="hidden" name="branchId"
			value="<%=bookLoans.getLibraryBranch().getBranchId()%>"> <input
			type="hidden" name="borrowerId"
			value="<%=bookLoans.getBorrower().getCardNo()%>">

		<button type="submit" class="btn btn-primary btn-md">Update
			DueDate</button>
	
	</form>

</div>