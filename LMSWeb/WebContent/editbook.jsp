<%@page import="com.gcit.lms.entity.Author"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="com.gcit.lms.entity.Publisher"%>
<%@page import="java.util.List"%>
<%@include file="include.html"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%AdminService service = new AdminService();
Book book = service.readBookByPK(Integer.parseInt(request.getParameter("bookId")));
List<Author> authors = service.readAuthors();
List<Publisher> publishers = service.readPublishers();
%>

<div class="container">
	<h1>Edit Book</h1>
	<form method="post" action="editBook">
		${statusMessage}
		<br/>Enter Book Title to Edit: <input type="text" name="title" value="<%=book.getTitle()%>"><br />
		Select Authors from list Below: <br/>
		<select multiple="multiple" size="10" name="authorIds">
			<option value="">Select Author to associate</option>
			<%for(Author a: authors) {%>

				<option value=<%=a.getAuthorId()%> <%if((a.getBooks()).contains(book)){%>selected<%}%>><%=a.getAuthorName() %></option>
			<%} %>
		</select> <br/>
		Select a Publisher from list Below: <br/>
		<select size="10" name="publisherId">
			<option value="">Select Publisher to associate</option>
			<%for(Publisher a: publishers) {%>
			<option value=<%=a.getPublisherId()%> <%if(a.getBooks().contains(book)){ %>selected<%}%>><%=a.getPublisherName() %></option>
			<%} %>
		</select>
		<br/>
		<input type="hidden" name="bookId" value="<%=book.getBookId()%>">
		<button type="submit" class="btn btn-primary btn-md">Update Book</button>
	</form>

</div>