<%@include file="include.html"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.entity.Author"%>
<%@page import="com.gcit.lms.entity.Genre"%>
<%@page import="com.gcit.lms.entity.Publisher"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%AdminService service = new AdminService();
List<Author> authors = service.readAuthors();
List<Genre> genres = service.readGenres();
List<Publisher> publishers = service.readPublishers();
%>
<div class="container">
	<h2>Add New Book</h2>
	<form method="post" action="addBook">
	${statusMessage}
		<br/>Enter Book Title: <input type="text" name="title"><br />
		Select Authors from list Below: <br/>
		<select multiple class="form-control" multiple="multiple" size="10" name="authorIds">
			<%for(Author a: authors) {%>
			<option value=<%=a.getAuthorId()%>><%=a.getAuthorName() %></option>
			<%} %>
		</select><br/>
			Select Genres from list Below: <br/>
			<select multiple class="form-control" multiple="multiple" size="10" name="genreIds">
				<%for(Genre a: genres) {%>
				<option value=<%=a.getGenreId()%>><%=a.getGenreName() %></option>
				<%} %>
			</select>
		<br/>
		Select a Publisher from list Below: <br/>
		<select size="1" name="publisherId">
			<%for(Publisher a: publishers) {%>
			<option value=<%=a.getPublisherId()%>><%=a.getPublisherName() %></option>
			<%} %>
		</select>
		<br/>
		<button type="submit" class="btn btn-primary btn-md">Save Book</button><br/>
	</form>
</div>