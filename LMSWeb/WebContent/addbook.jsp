<%@include file="include.html"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.entity.Author"%>
<%@page import="com.gcit.lms.entity.Publisher"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%AdminService service = new AdminService();
List<Author> authors = service.readAuthors();
List<Publisher> publishers = service.readPublishers();
%>
<div class="container">
	<h2>Add New Book</h2>
	<form method="post" action="addBook">
	${statusMessage}
		<br/>Enter Book Title: <input type="text" name="title"><br />
		Select Authors from list Below: <br/>
		<select multiple="multiple" size="10" name="authorIds">
			<option value="">Select Author to associate</option>
			<%for(Author a: authors) {%>
			<option value=<%=a.getAuthorId()%>><%=a.getAuthorName() %></option>
			<%} %>
		</select>
		<br/>
		Select a Publisher from list Below: <br/>
		<select size="10" name="publisherId">
			<option value="">Select Publisher to associate</option>
			<%for(Publisher a: publishers) {%>
			<option value=<%=a.getPublisherId()%>><%=a.getPublisherName() %></option>
			<%} %>
		</select>
		<br/>
		<button type="submit" class="btn btn-primary btn-md">Save Book</button>
	</form>
</div>