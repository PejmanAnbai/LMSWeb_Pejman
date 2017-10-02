<%@page import="com.gcit.lms.entity.LibraryBranch"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="java.util.List"%>
<%@include file="include.html"%>
<%@page import="com.gcit.lms.service.LibrarianService"%>
<%LibrarianService service = new LibrarianService();
LibraryBranch libraryBranch = service.readBranchByPK((Integer)request.getAttribute("branchId"));
List<Book> books = service.readBranchBooks(libraryBranch);
%>

<div class="container">
	<h1>Edit Library Branch</h1>
	<form method="post" action="editLibrarianBranch">
		${statusMessage}
		<br/>Enter Library Branch Name to Edit: <input type="text" name="branchName" value="<%=libraryBranch.getBranchName()%>"><br />
		<br/>Enter Library Branch Address to Edit: <input type="text" name="branchAddress" value="<%=libraryBranch.getBranchAddress()%>"><br />
		<input type="hidden" name="branchId" value="<%=libraryBranch.getBranchId()%>">
		<button type="submit" class="btn btn-primary btn-md">Update Library Branch</button>
	</form>

</div>