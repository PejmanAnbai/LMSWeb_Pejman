package com.gcit.lms.web;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.gcit.lms.entity.LibraryBranch;
import com.gcit.lms.service.LibrarianService;

@WebServlet({ "/dispayBranches", "/selectedBranch", "/pageLibrarianBranch", "/moveToEdit", "/editLibrarianBranch",
		"/editCopies", "/pageLibrarianBooks", "/editNumberOfCopies", "/editNumOfCopies" })
public class LibrarianServlet extends HttpServlet {

	private static final long serialVersionUID = -6688300013004311686L;

	public LibrarianServlet() {
		super();
	}

	Integer branchId = 0;
	LibrarianService service = new LibrarianService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String reqUrl = request.getRequestURI().substring(request.getContextPath().length(),
				request.getRequestURI().length());
		String redirectUrl = "/viewlibrarianbranches.jsp";
		switch (reqUrl) {
		case "/pageLibrarianBranch":
			redirectUrl = "/viewlibrarianbranches.jsp";
			redirectUrl = pageBranch(request, response, redirectUrl);
			break;
		case "/selectedBranch":
			redirectUrl = "/viewlibrarianbranches.jsp";
			redirectUrl = selectedBranch(request, response, redirectUrl);
			break;
		case "/moveToEdit":
			redirectUrl = "/librarian.jsp";
			redirectUrl = moveToEdit(request, response, redirectUrl);
			break;
		case "/editCopies":
			redirectUrl = "/librarian.jsp";
			redirectUrl = editCopies(request, response, redirectUrl);
			break;
		case "/pageLibrarianBooks":
			redirectUrl = "/viewlibrarianbranchbooks.jsp";
			redirectUrl = pageLibrarianBooks(request, response, redirectUrl);
			break;
		case "/editNumberOfCopies":
			redirectUrl = "/librarian.jsp";
			redirectUrl = editNumberOfCopies(request, response, redirectUrl);
			break;
		default:
			break;
		}
		RequestDispatcher rd = request.getRequestDispatcher(redirectUrl);
		rd.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String reqUrl = request.getRequestURI().substring(request.getContextPath().length(),
				request.getRequestURI().length());
		String redirectUrl = "/viewlibrarianbranches.jsp";
		switch (reqUrl) {
		case "/editLibrarianBranch":
			redirectUrl = "/librarian.jsp";
			redirectUrl = editLibrarianBranch(request, redirectUrl);
			break;
		case "/editNumOfCopies":
			redirectUrl = "/librarian.jsp";
			redirectUrl = editNumOfCopies(request, redirectUrl);
			break;
		default:
			break;
		}

		RequestDispatcher rd = request.getRequestDispatcher(redirectUrl);
		rd.forward(request, response);

	}

	private String editNumOfCopies(HttpServletRequest request, String redirectUrl) {
		String message = "Editing number of copies was successful!";
		if(request.getParameter("bookCopies")!=null)
		{
			try {
				service.updateNoOfCopies(Integer.parseInt(request.getParameter("bookId")), branchId,
						Integer.parseInt(request.getParameter("bookCopies")));
			} catch (NumberFormatException | SQLException e) {
				e.printStackTrace();
			}
		}
		else {
			request.setAttribute("bookId", Integer.parseInt(request.getParameter("bookId")));
			request.setAttribute("branchId", Integer.parseInt(request.getParameter("branchId")));
			message="Number of copies was not changed.";
		}
		request.setAttribute("statusMessage", message);
		return redirectUrl;
	}
	private String editNumberOfCopies(HttpServletRequest request, HttpServletResponse response, String redirectUrl) {
		if (request.getParameter("bookId") != null) {
			
			request.setAttribute("bookId", Integer.parseInt(request.getParameter("bookId")));
			request.setAttribute("branchId", branchId);
			redirectUrl = "editnumberofcopies.jsp";
		}

		return redirectUrl;
	}

	private String pageLibrarianBooks(HttpServletRequest request, HttpServletResponse response, String redirectUrl) {
		if (request.getParameter("pageNo") != null) {
			Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
			try {
				request.setAttribute("books", service.readBranchBooks(branchId, pageNo));
				request.setAttribute("branchId", branchId);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return redirectUrl;
		}

		return null;
	}

	private String editCopies(HttpServletRequest request, HttpServletResponse response, String redirectUrl) {
		if (request.getParameter("branchId") != null) {
			request.setAttribute("branchId", branchId);
			redirectUrl = "viewlibrarianbranchbooks.jsp";
		}

		return redirectUrl;
	}

	private String moveToEdit(HttpServletRequest request, HttpServletResponse response, String redirectUrl) {
		if (request.getParameter("branchId") != null) {
//			branchId = Integer.parseInt(request.getParameter("branchId"));
			request.setAttribute("branchId", branchId);
			redirectUrl = "editlibrarianbranch.jsp";
		}

		return redirectUrl;
	}

	private String editLibrarianBranch(HttpServletRequest request, String redirectUrl) {
		String message = "Branch Edited Sucessfully";
		LibraryBranch branch = new LibraryBranch();
		if (request.getParameter("branchName") != null && !request.getParameter("branchName").isEmpty()) {
			if (request.getParameter("branchName").length() > 45) {
				message = "Branch Name cannot be more than 45 chars";
				redirectUrl = "/editlibrarianbranch.jsp";
			} else {
				branch.setBranchName(request.getParameter("branchName"));
				branch.setBranchAddress(request.getParameter("branchAddress"));
				branch.setBranchId(Integer.parseInt(request.getParameter("branchId")));

				try {
					service.updateLibraryBranch(branch);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} else {
			message = "Branch Name cannot be Empty";
			redirectUrl = "/editlibrarianbranch.jsp";
		}
		request.setAttribute("branchId", branchId);
		request.setAttribute("statusMessage", message);
		return redirectUrl;
	}

	private String pageBranch(HttpServletRequest request, HttpServletResponse response, String redirectUrl) {
		if (request.getParameter("pageNo") != null) {
			Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
			try {
				request.setAttribute("branches", service.readBranches(null, pageNo));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return redirectUrl;
		}
		return null;
	}

	private String selectedBranch(HttpServletRequest request, HttpServletResponse response, String redirectUrl) {
		if (request.getParameter("branchId") != null) {
			branchId = Integer.parseInt(request.getParameter("branchId"));
			request.setAttribute("branchId", branchId);
			redirectUrl = "librarian.jsp";
		}

		return redirectUrl;
	}

}
