<%@include file="include.html"%>
<%@page import="com.gcit.lms.entity.Borrower"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="com.gcit.lms.entity.LibraryBranch"%>
<%@page import="com.gcit.lms.entity.BookLoans"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%
	AdminService service = new AdminService();
	Integer totalCount = service.getBookLoansCount();
	int numOfPages = 0;
	if(totalCount%10 > 0){
		numOfPages = totalCount/10 +1;
	}else{
		numOfPages = totalCount/10;
	}
	List<BookLoans> bookLoans = new ArrayList<>();
	if(request.getAttribute("bookLoans")!=null){
		bookLoans = (List<BookLoans>)request.getAttribute("bookLoans");
	}else{
		bookLoans = service.readBookLoans(null, 1);
	}
%>
<%
	if (request.getAttribute("statusMessage") != null) {
		out.println(request.getAttribute("statusMessage"));
	}
%>
<div class="container">
	<h1>List of Book Loans in LMS&nbsp;&nbsp;&nbsp;&nbsp; Total Borrowers in LMS: <%=totalCount%> Book Loans</h1>
	<nav aria-label="Page navigation example">
		<ul class="pagination">
			<li class="page-item"><a class="page-link" href="#"
				aria-label="Previous"> <span aria-hidden="true">&laquo;</span> <span
					class="sr-only">Previous</span>
			</a></li>
			<%for(int i=1; i<=numOfPages; i++){ %>
			<li class="page-item"><a class="page-link" href="pageBookLoans?pageNo=<%=i%>"><%=i%></a></li>
			<%} %>
			<li class="page-item"><a class="page-link" href="#"
				aria-label="Next"> <span aria-hidden="true">&raquo;</span> <span
					class="sr-only">Next</span>
			</a></li>
		</ul>
	</nav>
	<table class="table table-striped">
		<tr>
			<th>#</th>
			<th>Borrower Name</th>
			<th>Book Name</th>
			<th>Branch Name</th>
			<th>Date Out</th>
			<th>Due Date</th>
			<th>Date In</th>
		</tr>
		<%
		for (BookLoans a : bookLoans) {
			String temp = a.getDateOut();
			String temp2 = a.getDueDate();
			String temp3 = a.getDateIn();
		%>
		<tr>
			<td><%=bookLoans.indexOf(a) + 1%></td>
			<td><%=a.getBorrower().getName()%></td>
			<td><%=a.getBook().getTitle()%></td>
			<td><%=a.getLibraryBranch().getBranchName()%></td>
			<td><%
				if(temp!= null){
					out.println(temp);
				}%>
			</td>
			<td><%
				if(temp2!= null){
					out.println(temp2);
				}%></td>
			<td><%
				if(temp3!= null){
					out.println(temp3);
				}%></td>
			<td><button type="button"
					onclick="javascript:location.href='editduedate.jsp?bookId=<%=a.getBook().getBookId()%>&branchId=<%=a.getLibraryBranch().getBranchId()%>&borrowerId=<%=a.getBorrower().getCardNo()%>'"
					class="btn btn-primary btn-sm">Edit</button></td>
			<td><button type="button"
					onclick="javascript:location.href='deleteBookLoan?bookId=<%=a.getBook().getBookId()%>&branchId=<%=a.getLibraryBranch().getBranchId()%>&borrowerId=<%=a.getBorrower().getCardNo()%>'"
					class="btn btn-danger btn-sm">Delete</button></td>
		</tr>
		<%
			}
		%>
	</table>
</div>