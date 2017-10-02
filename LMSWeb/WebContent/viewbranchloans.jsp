<%@include file="include.html"%>
<%@page import="com.gcit.lms.entity.BookLoans"%>
<%@page import="com.gcit.lms.service.BorrowerService"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.entity.Publisher"%>
<%@page import="com.gcit.lms.entity.Author"%>
<%
	BorrowerService service = new BorrowerService();
	Integer totalCount = service.getBookLoansCount((Integer)request.getAttribute("branchId"), (Integer)request.getAttribute("cardNo"));
	int numOfPages = 0;
	if(totalCount%10 > 0){
		numOfPages = totalCount/10 +1;
	}else{
		numOfPages = totalCount/10;
	}
	int pageIndex = 0;
	if(request.getParameter("pageNo")!=null)
		pageIndex = Integer.parseInt(request.getParameter("pageNo"));
	else
		pageIndex = 1;
	List<BookLoans> loans = new ArrayList<>();
	if(request.getAttribute("books")!=null){
		loans = (List<BookLoans>)request.getAttribute("bookLoans");
	}else{
		loans = service.readBookLoans((Integer)request.getAttribute("branchId"),(Integer)request.getAttribute("cardNo"), 1);
	}
%>
<%
	if (request.getAttribute("statusMessage") != null) {
		out.println(request.getAttribute("statusMessage"));
	}
%>
<div class="container">
	<h1>List of your Book loans in LMS&nbsp;&nbsp;&nbsp;&nbsp; Total BookLoans in LMS: <%=totalCount%> Books</h1>
	<nav aria-label="Page navigation example">
		<ul class="pagination">
			<li class="page-item"><a class="page-link" href="<%if(pageIndex<=numOfPages && pageIndex>1){%>pageLoansCheckIn?pageNo=<%=pageIndex-1%><%} %>"
				aria-label="Previous"> <span aria-hidden="true">&laquo;</span> <span
					class="sr-only">Previous</span>
			</a></li>
			<%for(int i=1; i<=numOfPages; i++){ %>
			<li class="page-item"><a class="page-link" href="pageLoansCheckIn?pageNo=<%=i%>"><%=i%></a></li>
			<%} %>
			<li class="page-item"><a class="page-link" href="<%if(pageIndex<numOfPages){%>pageLoansCheckIn?pageNo=<%=pageIndex+1%><%} %>"
				aria-label="Next"> <span aria-hidden="true">&raquo;</span> <span
					class="sr-only">Next</span>
			</a></li>
		</ul>
	</nav>
	<table class="table table-striped">
		<tr>
			<th>#</th>
			<th>Book Title</th>
			<th>DateOut</th>
			<th>DueDate</th>
			<th>Check In</th>
		</tr>
		<%
			for (BookLoans b : loans) {
		%>
		<tr>
			<td><%=loans.indexOf(b) + 1%></td>
			<td><%=b.getBook().getTitle()%></td>
			<td>
				<%=b.getDateOut()%>
			</td>
			<td><%=b.getDueDate()%></td>
			<td><button type="button"
					onclick="javascript:location.href='bookCheckIn?bookId=<%=b.getBook().getBookId()%>'"
					class="btn btn-primary btn-sm">CheckIn</button></td>

		</tr>
		<%
			}
		%>
	</table>
</div>