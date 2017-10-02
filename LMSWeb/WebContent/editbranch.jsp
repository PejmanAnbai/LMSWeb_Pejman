<%@page import="com.gcit.lms.entity.LibraryBranch"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="java.util.List"%>
<%@include file="include.html"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%AdminService service = new AdminService();
LibraryBranch libraryBranch = service.readBranchByPK(Integer.parseInt(request.getParameter("branchId")));
List<Book> books = service.readBooks();
%>

<div class="container">
	<h1>Edit Library Branch</h1>
	<form method="post" action="editBranch">
		${statusMessage}
		<br/>Enter Library Branch Name to Edit: <input type="text" name="branchName" value="<%=libraryBranch.getBranchName()%>"><br />
		<br/>Enter Library Branch Address to Edit: <input type="text" name="branchAddress" value="<%=libraryBranch.getBranchAddress()%>"><br />
		<input type="hidden" name="branchId" value="<%=libraryBranch.getBranchId()%>">
		Select Books from list Below: <br/>
		<select multiple="multiple" size="10" name="bookIds">
			<option value="">Select Book to associate</option>
			<%for(Book b: books) {%>

				<option value=<%=b.getBookId()%> <%if((libraryBranch.getBooks()).contains(b)){%>selected<%}%>><%=b.getTitle() %></option>
			<%} %>
		</select>
		<button type="submit" class="btn btn-primary btn-md">Update Library Branch</button>
	</form>

</div>