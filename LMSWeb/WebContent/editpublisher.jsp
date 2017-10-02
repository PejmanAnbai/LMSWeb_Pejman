<%@page import="com.gcit.lms.entity.Publisher"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="java.util.List"%>
<%@include file="include.html"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%AdminService service = new AdminService();
Publisher publisher = service.readPublisherByPK(Integer.parseInt(request.getParameter("publisherId")));
List<Book> books = service.readBooks();
%>

<div class="container">
	<h1>Edit Publisher</h1>
	<form method="post" action="editPublisher">
		${statusMessage}
		<br/>Enter Publisher Name to Edit: <input type="text" name="publisherName" value="<%=publisher.getPublisherName()%>"><br />
		<br/>Enter Publisher Address to Edit: <input type="text" name="publisherAddress" value="<%=publisher.getPublisherAddress()%>"><br />
		<br/>Enter Publisher Phone to Edit: <input type="text" name="publisherPhone" value="<%=publisher.getPublisherPhone()%>"><br />
		<input type="hidden" name="publisherId" value="<%=publisher.getPublisherId()%>">
		<button type="submit" class="btn btn-primary btn-md">Update Publisher</button>
	</form>

</div>