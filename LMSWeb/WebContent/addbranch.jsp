<%@include file="include.html"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%AdminService service = new AdminService();
List<Book> books = service.readBooks();
%>
<div class="container">
	<h2>Add New Library Branch</h2>
	<form method="post" action="addBranch">
	${statusMessage}
		<br/>Enter Library Branch Name: <input type="text" name="branchName"><br />
		<br/>Enter Library Branch Address: <input type="text" name="branchAddress"><br />
		Select Books from list Below to associate with the Branch: <br/>
		<select multiple class="form-control" multiple="multiple" size="10" name="bookIds">
			<option value="">Select Book to associate</option>
			<%for(Book b: books) {%>
			<option value=<%=b.getBookId()%>><%=b.getTitle() %></option>
			<%} %>
		</select>
		<button type="submit" class="btn btn-primary btn-md">Save Branch</button>
	</form>
</div>