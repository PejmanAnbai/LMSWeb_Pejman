<%@page import="com.gcit.lms.entity.Genre"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="java.util.List"%>
<%@include file="include.html"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%AdminService service = new AdminService();
Genre genre = service.readGenreByPK(Integer.parseInt(request.getParameter("genre_id")));
List<Book> books = service.readBooks();
%>

<div class="container">
	<h1>Edit Genre</h1>
	<form method="post" action="editGenre">
		${statusMessage}
		<br/>Enter Genre Name to Edit: <input type="text" name="genre_name" value="<%=genre.getGenreName()%>"><br />
		<input type="hidden" name="genre_id" value="<%=genre.getGenreId()%>">
		Select Books from list Below: <br/>
		<select multiple class="form-control" multiple="multiple" size="10" name="bookIds">
			<option value="">Select Book to associate</option>
			<%for(Book b: books) {%>

				<option value=<%=b.getBookId()%> <%if((b.getGenres()).contains(genre)){%>selected<%}%>><%=b.getTitle() %></option>
			<%} %>
		</select>
		<button type="submit" class="btn btn-primary btn-md">Update Genre</button>
	</form>

</div>