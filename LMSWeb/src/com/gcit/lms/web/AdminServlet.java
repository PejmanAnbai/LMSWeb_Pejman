package com.gcit.lms.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookLoans;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Genre;
import com.gcit.lms.entity.LibraryBranch;
import com.gcit.lms.entity.Publisher;
import com.gcit.lms.service.AdminService;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet({ "/addAuthor", "/deleteAuthor", "/editAuthor", "/pageAuthors", "/addGenre", "/deleteGenre", "/editGenre",
		"/pageGenre", "/addPublisher", "/deletePublisher", "/editPublisher", "/pagePublisher", "/addBorrower",
		"/deleteBorrower", "/editBorrower", "/pageBorrower", "/addBranch", "/deleteBranch", "/editBranch",
		"/pageBranch", "/addBook", "/deleteBook", "/editBook", "/pageBook", "/deleteBookLoan", "/editBookLoans",
		"/pageBookLoans" })
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AdminService adminService = new AdminService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String reqUrl = request.getRequestURI().substring(request.getContextPath().length(),
				request.getRequestURI().length());
		String redirectUrl = "/viewauthors.jsp";
		switch (reqUrl) {
		case "/deleteAuthor":
			redirectUrl = "/viewauthors.jsp";
			redirectUrl = deleteAuthor(request, response, redirectUrl);
			break;
		case "/pageAuthors":
			redirectUrl = "/viewauthors.jsp";
			redirectUrl = pageAuthors(request, response, redirectUrl);
			break;
		case "/deleteGenre":
			redirectUrl = "/viewgenres.jsp";
			redirectUrl = deleteGenre(request, response, redirectUrl);
			break;
		case "/pageGenre":
			redirectUrl = "/viewgenres.jsp";
			redirectUrl = pageGenre(request, response, redirectUrl);
			break;
		case "/deletePublisher":
			redirectUrl = "/viewpublishers.jsp";
			redirectUrl = deletePublisher(request, response, redirectUrl);
			break;
		case "/pagePublisher":
			redirectUrl = "/viewpublishers.jsp";
			redirectUrl = pagePublisher(request, response, redirectUrl);
			break;
		case "/deleteBorrower":
			redirectUrl = "/viewborrowers.jsp";
			redirectUrl = deleteBorrower(request, response, redirectUrl);
			break;
		case "/pageBorrower":
			redirectUrl = "/viewborrowers.jsp";
			redirectUrl = pageBorrower(request, response, redirectUrl);
			break;
		case "/deleteBranch":
			redirectUrl = "/viewbranches.jsp";
			redirectUrl = deleteBranch(request, response, redirectUrl);
			break;
		case "/pageBranch":
			redirectUrl = "/viewbranches.jsp";
			redirectUrl = pageBranch(request, response, redirectUrl);
			break;
		case "/deleteBook":
			redirectUrl = "/viewbooks.jsp";
			redirectUrl = deleteBook(request, response, redirectUrl);
			break;
		case "/pageBook":
			redirectUrl = "/viewbooks.jsp";
			redirectUrl = pageBook(request, response, redirectUrl);
			break;
		case "/deleteBookLoan":
			redirectUrl = "/viewbookloans.jsp";
			redirectUrl = deleteBookLoan(request, response, redirectUrl);
			break;
		case "/pageBookLoans":
			redirectUrl = "/viewbookloans.jsp";
			redirectUrl = pageBookLoan(request, response, redirectUrl);
			break;

		default:
			break;
		}
		RequestDispatcher rd = request.getRequestDispatcher(redirectUrl);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String reqUrl = request.getRequestURI().substring(request.getContextPath().length(),
				request.getRequestURI().length());
		String redirectUrl = "/viewauthors.jsp";
		switch (reqUrl) {
		case "/addAuthor":
			redirectUrl = "/viewauthors.jsp";
			redirectUrl = addAuthor(request, redirectUrl, false);
			break;
		case "/editAuthor":
			redirectUrl = "/viewauthors.jsp";
			redirectUrl = addAuthor(request, redirectUrl, true);
			break;
		case "/addGenre":
			redirectUrl = "/viewgenres.jsp";
			redirectUrl = addGenre(request, redirectUrl, false);
			break;
		case "/editGenre":
			redirectUrl = "/viewgenres.jsp";
			redirectUrl = addGenre(request, redirectUrl, true);
			break;
		case "/addPublisher":
			redirectUrl = "/viewpublishers.jsp";
			redirectUrl = addPublisher(request, redirectUrl, false);
			break;
		case "/editPublisher":
			redirectUrl = "/viewpublishers.jsp";
			redirectUrl = addPublisher(request, redirectUrl, true);
			break;
		case "/addBorrower":
			redirectUrl = "/viewborrowers.jsp";
			redirectUrl = addBorrower(request, redirectUrl, false);
			break;
		case "/editBorrower":
			redirectUrl = "/viewborrowers.jsp";
			redirectUrl = addBorrower(request, redirectUrl, true);
			break;
		case "/addBranch":
			redirectUrl = "/viewbranches.jsp";
			redirectUrl = addBranch(request, redirectUrl, false);
			break;
		case "/editBranch":
			redirectUrl = "/viewbranches.jsp";
			redirectUrl = addBranch(request, redirectUrl, true);
			break;
		case "/addBook":
			redirectUrl = "/viewbooks.jsp";
			redirectUrl = addBook(request, redirectUrl, false);
			break;
		case "/editBook":
			redirectUrl = "/viewbooks.jsp";
			redirectUrl = addBook(request, redirectUrl, true);
			break;
		case "/editBookLoans":
			redirectUrl = "/viewbookloans.jsp";
			redirectUrl = addBookLoans(request, redirectUrl, true);
			break;

		default:
			break;
		}

		RequestDispatcher rd = request.getRequestDispatcher(redirectUrl);
		rd.forward(request, response);

	}

	private String addAuthor(HttpServletRequest request, String redirectUrl, Boolean editMode) {
		Author author = new Author();
		String message = "Author added Sucessfully";

		if (request.getParameter("authorName") != null && !request.getParameter("authorName").isEmpty()) {
			if (request.getParameter("authorName").length() > 45) {
				message = "Author Name cannot be more than 45 chars";
				redirectUrl = "/addauthor.jsp";
			} else {
				author.setAuthorName(request.getParameter("authorName"));
				String[] bookIds = request.getParameterValues("bookIds");
				List<Book> books = new ArrayList<>();
				if (bookIds != null) {
					for (int i = 0; i < bookIds.length; i++) {
						Book temp = new Book();
						temp.setBookId(Integer.parseInt(bookIds[i]));
						books.add(temp);
					}
					author.setBooks(books);
				}
				if (editMode) {
					author.setAuthorId(Integer.parseInt(request.getParameter("authorId")));
				}
				try {
					adminService.saveAuthor(author);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} else {
			message = "Author Name cannot be Empty";
			redirectUrl = "/addauthor.jsp";
		}
		request.setAttribute("statusMessage", message);
		return redirectUrl;
	}

	private String deleteAuthor(HttpServletRequest request, HttpServletResponse response, String redirectUrl)
			throws ServletException, IOException {
		String message = "Author deleted Sucessfully";
		if (request.getParameter("authorId") != null) {
			Integer authorId = Integer.parseInt(request.getParameter("authorId"));
			Author author = new Author();
			author.setAuthorId(authorId);
			try {
				adminService.deleteAuthor(author);
			} catch (SQLException e) {
				e.printStackTrace();
				message = "Author deleted failed. Contact Admin";
			}
			request.setAttribute("statusMessage", message);
			return redirectUrl;
		}

		return null;
	}

	private String pageAuthors(HttpServletRequest request, HttpServletResponse response, String redirectUrl)
			throws ServletException, IOException {
		if (request.getParameter("pageNo") != null) {
			Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
			try {
				request.setAttribute("authors", adminService.readAuthors(null, pageNo));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return redirectUrl;
		}

		return null;
	}

	private String addGenre(HttpServletRequest request, String redirectUrl, boolean editMode) {
		Genre genre = new Genre();
		String message = "Genre added Sucessfully";

		if (request.getParameter("genre_name") != null && !request.getParameter("genre_name").isEmpty()) {
			if (request.getParameter("genre_name").length() > 45) {
				message = "Genre Name cannot be more than 45 chars";
				redirectUrl = "/addgenre.jsp";
			} else {
				genre.setGenreName(request.getParameter("genre_name"));
				String[] bookIds = request.getParameterValues("bookIds");
				List<Book> books = new ArrayList<>();
				if (bookIds != null) {
					for (int i = 0; i < bookIds.length; i++) {
						Book temp = new Book();
						temp.setBookId(Integer.parseInt(bookIds[i]));
						books.add(temp);
					}
					genre.setBooks(books);
				}
				if (editMode) {
					genre.setGenreId(Integer.parseInt(request.getParameter("genre_id")));
				}
				try {
					adminService.saveGenre(genre);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} else {
			message = "Genre Name cannot be Empty";
			redirectUrl = "/addgenre.jsp";
		}
		request.setAttribute("statusMessage", message);
		return redirectUrl;
	}

	private String pageGenre(HttpServletRequest request, HttpServletResponse response, String redirectUrl) {
		if (request.getParameter("pageNo") != null) {
			Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
			try {
				request.setAttribute("genres", adminService.readAuthors(null, pageNo));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return redirectUrl;
		}

		return null;
	}

	private String deleteGenre(HttpServletRequest request, HttpServletResponse response, String redirectUrl) {
		String message = "Genre deleted Sucessfully";
		if (request.getParameter("genre_id") != null) {
			Integer genreId = Integer.parseInt(request.getParameter("genre_id"));
			Genre genre = new Genre();
			genre.setGenreId(genreId);
			try {
				adminService.deleteGenre(genre);
			} catch (SQLException e) {
				e.printStackTrace();
				message = "Genre deleted failed. Contact Admin";
			}
			request.setAttribute("statusMessage", message);
			return redirectUrl;
		}
		return null;
	}

	private String addPublisher(HttpServletRequest request, String redirectUrl, boolean editMode) {
		Publisher publisher = new Publisher();
		String message = "Publisher added Sucessfully";

		if (request.getParameter("publisherName") != null && !request.getParameter("publisherName").isEmpty()) {
			if (request.getParameter("publisherName").length() > 45) {
				message = "Publisher Name cannot be more than 45 chars";
				redirectUrl = "/addpublisher.jsp";
			} else {
				publisher.setPublisherName(request.getParameter("publisherName"));
				publisher.setPublisherAddress(request.getParameter("publisherAddress"));
				publisher.setPublisherPhone(request.getParameter("publisherPhone"));
				String[] bookIds = request.getParameterValues("bookIds");
				List<Book> books = new ArrayList<>();
				if (bookIds != null) {
					for (int i = 0; i < bookIds.length; i++) {
						Book temp = new Book();
						temp.setBookId(Integer.parseInt(bookIds[i]));
						books.add(temp);
					}
					publisher.setBooks(books);
				}
				if (editMode) {
					publisher.setPublisherId(Integer.parseInt(request.getParameter("publisherId")));
				}
				try {
					adminService.savePublisher(publisher);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} else {
			message = "Publisher Name cannot be Empty";
			redirectUrl = "/addpublisher.jsp";
		}
		request.setAttribute("statusMessage", message);
		return redirectUrl;
	}

	private String pagePublisher(HttpServletRequest request, HttpServletResponse response, String redirectUrl) {
		if (request.getParameter("pageNo") != null) {
			Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
			try {
				request.setAttribute("publishers", adminService.readPublishers(null, pageNo));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return redirectUrl;
		}

		return null;
	}

	private String deletePublisher(HttpServletRequest request, HttpServletResponse response, String redirectUrl) {
		String message = "Publisher deleted Sucessfully";
		if (request.getParameter("publisherId") != null) {
			Integer publisherId = Integer.parseInt(request.getParameter("publisherId"));
			Publisher publisher = new Publisher();
			publisher.setPublisherId(publisherId);
			try {
				adminService.deletePublisher(publisher);
			} catch (SQLException e) {
				e.printStackTrace();
				message = "Publisher deleted failed. Contact Admin";
			}
			request.setAttribute("statusMessage", message);
			return redirectUrl;
		}
		return null;
	}

	private String addBorrower(HttpServletRequest request, String redirectUrl, boolean editMode) {
		Borrower borrower = new Borrower();
		String message = "Borrower added Sucessfully";

		if (request.getParameter("name") != null && !request.getParameter("name").isEmpty()) {
			if (request.getParameter("name").length() > 45) {
				message = "Borrower Name cannot be more than 45 chars";
				redirectUrl = "/addborrower.jsp";
			} else {
				borrower.setName(request.getParameter("name"));
				borrower.setAddress(request.getParameter("address"));
				borrower.setPhone(request.getParameter("phone"));
				// String[] bookIds = request.getParameterValues("bookLoans");
				// List<Book> books = new ArrayList<>();
				// if (bookIds != null) {
				// for (int i = 0; i < bookIds.length; i++) {
				// Book temp = new Book();
				// temp.setBookId(Integer.parseInt(bookIds[i]));
				// books.add(temp);
				// }
				// publisher.setBooks(books);
				// }
				if (editMode) {
					borrower.setCardNo(Integer.parseInt(request.getParameter("cardNo")));
				}
				try {
					adminService.saveBorrower(borrower);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} else {
			message = "Borrower Name cannot be Empty";
			redirectUrl = "/addborrower.jsp";
		}
		request.setAttribute("statusMessage", message);
		return redirectUrl;
	}

	private String pageBorrower(HttpServletRequest request, HttpServletResponse response, String redirectUrl) {
		if (request.getParameter("pageNo") != null) {
			Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
			try {
				request.setAttribute("borrowers", adminService.readBorrowers(null, pageNo));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return redirectUrl;
		}

		return null;
	}

	private String deleteBorrower(HttpServletRequest request, HttpServletResponse response, String redirectUrl) {
		String message = "Borrower deleted Sucessfully";
		if (request.getParameter("cardNo") != null) {
			Integer cardNo = Integer.parseInt(request.getParameter("cardNo"));
			Borrower borrower = new Borrower();
			borrower.setCardNo(cardNo);
			try {
				adminService.deleteBorrower(borrower);
			} catch (SQLException e) {
				e.printStackTrace();
				message = "Borrower deleted failed. Contact Admin";
			}
			request.setAttribute("statusMessage", message);
			return redirectUrl;
		}
		return null;
	}

	private String addBranch(HttpServletRequest request, String redirectUrl, boolean editMode) {
		LibraryBranch branch = new LibraryBranch();
		String message = "Branch added Sucessfully";

		if (request.getParameter("branchName") != null && !request.getParameter("branchName").isEmpty()) {
			if (request.getParameter("branchName").length() > 45) {
				message = "Branch Name cannot be more than 45 chars";
				redirectUrl = "/addbranch.jsp";
			} else {
				branch.setBranchName(request.getParameter("branchName"));
				branch.setBranchAddress(request.getParameter("branchAddress"));
				String[] bookIds = request.getParameterValues("bookIds");
				List<Book> books = new ArrayList<>();
				if (bookIds != null) {
					for (int i = 0; i < bookIds.length; i++) {
						Book temp = new Book();
						temp.setBookId(Integer.parseInt(bookIds[i]));
						books.add(temp);
					}
					branch.setBooks(books);
				}
				if (editMode) {
					branch.setBranchId(Integer.parseInt(request.getParameter("branchId")));
				}
				try {
					adminService.saveBranch(branch);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} else {
			message = "Branch Name cannot be Empty";
			redirectUrl = "/addbranch.jsp";
		}
		request.setAttribute("statusMessage", message);
		return redirectUrl;
	}

	private String pageBranch(HttpServletRequest request, HttpServletResponse response, String redirectUrl) {
		if (request.getParameter("pageNo") != null) {
			Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
			try {
				request.setAttribute("branches", adminService.readBranches(null, pageNo));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return redirectUrl;
		}

		return null;
	}

	private String deleteBranch(HttpServletRequest request, HttpServletResponse response, String redirectUrl) {
		String message = "Branch deleted Sucessfully";
		if (request.getParameter("branchId") != null) {
			Integer branchId = Integer.parseInt(request.getParameter("branchId"));
			LibraryBranch branch = new LibraryBranch();
			branch.setBranchId(branchId);
			try {
				adminService.deleteBranch(branch);
			} catch (SQLException e) {
				e.printStackTrace();
				message = "Branch deleted failed. Contact Admin";
			}
			request.setAttribute("statusMessage", message);
			return redirectUrl;
		}
		return null;
	}

	private String addBook(HttpServletRequest request, String redirectUrl, Boolean editMode) {
		Book book = new Book();
		String message = "Book added Sucessfully";

		if (request.getParameter("title") != null && !request.getParameter("title").isEmpty()) {
			if (request.getParameter("title").length() > 45) {
				message = "Book Name cannot be more than 45 chars";
				redirectUrl = "/addbook.jsp";
			} else {
				book.setTitle(request.getParameter("title"));
				String[] authorIds = request.getParameterValues("authorIds");
				List<Author> authors = new ArrayList<>();
				if (authorIds != null) {
					for (int i = 0; i < authorIds.length; i++) {
						Author temp = new Author();
						temp.setAuthorId(Integer.parseInt(authorIds[i]));
						authors.add(temp);
					}
					book.setAuthors(authors);
				}
				if (editMode) {
					book.setBookId(Integer.parseInt(request.getParameter("bookId")));
				}
				try {
					book.setPublisher(
							adminService.readPublisherByPK((Integer.parseInt(request.getParameter("publisherId")))));
					adminService.saveBook(book);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} else {
			message = "Book title cannot be Empty";
			redirectUrl = "/addbook.jsp";
		}
		request.setAttribute("statusMessage", message);
		return redirectUrl;
	}

	private String deleteBook(HttpServletRequest request, HttpServletResponse response, String redirectUrl)
			throws ServletException, IOException {
		String message = "Book deleted Sucessfully";
		if (request.getParameter("bookId") != null) {
			Integer bookId = Integer.parseInt(request.getParameter("bookId"));
			Book book = new Book();
			book.setBookId(bookId);
			try {
				adminService.deleteBook(book);
			} catch (SQLException e) {
				e.printStackTrace();
				message = "Book deleted failed. Contact Admin";
			}
			request.setAttribute("statusMessage", message);
			return redirectUrl;
		}

		return null;
	}

	private String pageBook(HttpServletRequest request, HttpServletResponse response, String redirectUrl)
			throws ServletException, IOException {

		if (request.getParameter("pageNo") != null) {
			Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
			try {
				request.setAttribute("books", adminService.readBooks(null, pageNo));
			} catch (SQLException e) {
				e.printStackTrace();
			}

			return redirectUrl;
		}

		return null;
	}
	private String addBookLoans(HttpServletRequest request, String redirectUrl, Boolean editMode) {
		BookLoans loans = new BookLoans();
		String message = "BookLoans updated Sucessfully";

		if (request.getParameter("duedate") != null && !request.getParameter("duedate").isEmpty()) {
			if (request.getParameter("duedate").length() > 11) {
				message = "Number of days cannot be more than 11 digits";
				redirectUrl = "/viewbookloans.jsp";
			} else {
				loans.setDueDate("curdate() + INTERVAL "+ request.getParameter("duedate") +" DAY");
//				List<Author> authors = new ArrayList<>();
//				if (authorIds != null) {
//					for (int i = 0; i < authorIds.length; i++) {
//						Author temp = new Author();
//						temp.setAuthorId(Integer.parseInt(authorIds[i]));
//						authors.add(temp);
//					}
//					book.setAuthors(authors);
//				}
				if (editMode) {
					Book b = new Book();
					b.setBookId(Integer.parseInt(request.getParameter("bookId")));
					loans.setBook(b);
					Borrower br = new Borrower();
					br.setCardNo(Integer.parseInt(request.getParameter("borrowerId")));
					loans.setBorrower(br);
					LibraryBranch l = new LibraryBranch();
					l.setBranchId(Integer.parseInt(request.getParameter("branchId")));
					loans.setLibraryBranch(l);
				}
				try {
//					book.setPublisher(
//							adminService.readPublisherByPK((Integer.parseInt(request.getParameter("publisherId")))));
					adminService.updateDueDate(loans);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} else {
			message = "Due date cannot be Empty";
			redirectUrl = "/viewbookloans.jsp";
		}
		request.setAttribute("statusMessage", message);
		return redirectUrl;
	}

	private String deleteBookLoan(HttpServletRequest request, HttpServletResponse response, String redirectUrl)
			throws ServletException, IOException {
		String message = "Book loan deleted Sucessfully";
		if (request.getParameter("bookId") != null) {
			BookLoans loans = new BookLoans();
			Book b = new Book();
			b.setBookId((Integer.parseInt(request.getParameter("bookId"))));
			loans.setBook(b);
			Borrower br = new Borrower();
			br.setCardNo((Integer.parseInt(request.getParameter("borrowerId"))));
			loans.setBorrower(br);
			LibraryBranch l = new LibraryBranch();
			l.setBranchId(Integer.parseInt(request.getParameter("branchId")));
			loans.setLibraryBranch(l);
			
			try {
				adminService.deleteBookLoan(loans);
			} catch (SQLException e) {
				e.printStackTrace();
				message = "Book loan deleted failed. Contact Admin";
			}
			request.setAttribute("statusMessage", message);
			return redirectUrl;
		}

		return null;
	}

	private String pageBookLoan(HttpServletRequest request, HttpServletResponse response, String redirectUrl)
			throws ServletException, IOException {

		if (request.getParameter("pageNo") != null) {
			Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
			try {
				request.setAttribute("bookLoans", adminService.readBookLoans(null, pageNo));
			} catch (SQLException e) {
				e.printStackTrace();
			}

			return redirectUrl;
		}

		return null;
	}
}
