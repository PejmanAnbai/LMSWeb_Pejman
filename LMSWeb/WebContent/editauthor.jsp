<%@page import="com.gcit.lms.entity.Author"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="java.util.List"%>
<%@include file="include.html"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%AdminService service = new AdminService();
Author author = service.readAuthorByPK(Integer.parseInt(request.getParameter("authorId")));
List<Book> books = service.readBooks();
%>

<div class="container">
	<h1>Edit Author</h1>
	<form method="post" action="editAuthor">
		${statusMessage}
		<br/>Enter Author Name to Edit: <input type="text" name="authorName" value="<%=author.getAuthorName()%>"><br />
		<input type="hidden" name="authorId" value="<%=author.getAuthorId()%>">
		Select Books from list Below: <br/>
		<select multiple="multiple" size="10" name="bookIds">
			<option value="">Select Book to associate</option>
			<%for(Book b: books) {%>

				<option value=<%=b.getBookId()%> <%if((b.getAuthors()).contains(author)){%>selected<%}%>><%=b.getTitle() %></option>
			<%} %>
		</select>
		<button type="submit" class="btn btn-primary btn-md">Update Author</button>
	</form>

</div>