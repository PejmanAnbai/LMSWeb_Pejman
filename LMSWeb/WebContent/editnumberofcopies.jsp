<%@page import="com.gcit.lms.entity.LibraryBranch"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="com.gcit.lms.entity.BookCopies"%>
<%@page import="java.util.List"%>
<%@include file="include.html"%>
<%@page import="com.gcit.lms.service.LibrarianService"%>
<%LibrarianService service = new LibrarianService();
LibraryBranch libraryBranch = service.readBranchByPK((Integer)request.getAttribute("branchId"));
Book book = service.readBookByPK((Integer)request.getAttribute("bookId"));
BookCopies bookCopies = service.readBookCopies((Integer)request.getAttribute("bookId"), (Integer)request.getAttribute("branchId"));

%>

<div class="container">
	<h1>Edit Number Of Copies: <%=book.getTitle()%></h1>
	<form method="post" action="editNumOfCopies">
		${statusMessage}
		<br/>Number of Copies of this Book: <%=book.getTitle()%><br />
		<br/>Current Number Of Copies: <%=bookCopies.getNoOfCopies()%><br />
		<br/>Enter Number Of copies you want to add: <input type="text" name="bookCopies" value=""><br />
		<input type="hidden" name="branchId" value="<%=libraryBranch.getBranchId()%>">
		<input type="hidden" name="bookId" value="<%=book.getBookId()%>">
		<button type="submit" class="btn btn-primary btn-md">Update Book Copies</button>
	</form>

</div>