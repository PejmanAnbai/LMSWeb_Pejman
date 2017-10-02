package com.gcit.lms.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.LibraryBranch;
import com.gcit.lms.service.BorrowerService;

@WebServlet({ "/checkIn", "/checkOut", "/pageBorrowerBranch", "/receiveBorrower", "/pageBookCheckOut", "/bookCheckOut",
		"/pageLoansCheckIn", "/bookCheckIn"})
public class BorrowerServlet extends HttpServlet{

	private static final long serialVersionUID = 7066535929376629325L;
	
	private Integer cardNo = 0;
	private Integer branchId = 0;
	private BorrowerService borrowerService = new BorrowerService();
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BorrowerServlet() {
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
		String redirectUrl = "/viewborrowerbranches.jsp";
		switch (reqUrl) {
		case "/checkOut":
			redirectUrl = "/viewbranchbooks.jsp";
			redirectUrl = checkOut(request, response, redirectUrl);
			break;
		case "/checkIn":
			redirectUrl = "/viewborrowerbranches.jsp";
			redirectUrl = checkIn(request, response, redirectUrl);
			break;
		case "/pageBorrowerBranch":
			redirectUrl = "/viewborrowerbranches.jsp";
			redirectUrl = pageBranch(request, response, redirectUrl);
			break;
		case "/pageBookCheckOut":
			redirectUrl = "/viewbranchbooks.jsp";
			redirectUrl = pageBookCheckOut(request, response, redirectUrl);
			break;
		case "/bookCheckOut":
			redirectUrl = "/viewborrowerbranches.jsp";
			redirectUrl = bookCheckOut(request, response, redirectUrl);
			break;
		case "/pageLoansCheckIn":
			redirectUrl = "/viewborrowerbranches.jsp";
			redirectUrl = pageLoansCheckIn(request, response, redirectUrl);
			break;
		case "/bookCheckIn":
			redirectUrl = "/viewborrowerbranches.jsp";
			redirectUrl = bookCheckIn(request, response, redirectUrl);
			break;
		default:
			break;
		}
		RequestDispatcher rd = request.getRequestDispatcher(redirectUrl);
		rd.forward(request, response);
	}

	private String bookCheckIn(HttpServletRequest request, HttpServletResponse response, String redirectUrl) {
		String message = "Successfully Checked In!";
		if(request.getParameter("bookId")!=null)
		{
			Book b = new Book();
			LibraryBranch l = new LibraryBranch();
			Borrower br = new Borrower();
			b.setBookId(Integer.parseInt(request.getParameter("bookId")));
			l.setBranchId(branchId);
			br.setCardNo(cardNo);
			try {
				borrowerService.returnBook(l, b, br);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		request.setAttribute("statusMessage", message);
		return redirectUrl;
	}
	private String bookCheckOut(HttpServletRequest request, HttpServletResponse response, String redirectUrl) {
		String message = "Successfully borrowed!";
		if(request.getParameter("bookId")!=null)
		{
			Book b = new Book();
			LibraryBranch l = new LibraryBranch();
			Borrower br = new Borrower();
			b.setBookId(Integer.parseInt(request.getParameter("bookId")));
			l.setBranchId(branchId);
			br.setCardNo(cardNo);
			try {
				if(borrowerService.readNoOfCopies(b, l) != null && borrowerService.readNoOfCopies(b, l) > 0)
				{
					if(borrowerService.readBookLoanByPK(Integer.parseInt(request.getParameter("bookId")), branchId, cardNo) == null)
							borrowerService.borrowBook(l, b, br);
					else
						message = "You have already borrowed this book!";
				}
				else
				{
					message = "No more copies of this book is currently avaible in this branch!";
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		request.setAttribute("statusMessage", message);
		return redirectUrl;
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String reqUrl = request.getRequestURI().substring(request.getContextPath().length(),
				request.getRequestURI().length());
		String redirectUrl = "/viewborrowerbranches.jsp";
		switch (reqUrl) {
		case "/receiveBorrower":
			redirectUrl = "/viewborrowerbranches.jsp";
			redirectUrl = receiveBorrower(request, redirectUrl, false);
			break;
		default:
			break;
		}

		RequestDispatcher rd = request.getRequestDispatcher(redirectUrl);
		rd.forward(request, response);

	}
	private String receiveBorrower(HttpServletRequest request, String redirectUrl, boolean b) {
		String message ="Card Number Accepted";
		if(request.getParameter("cardNo")!=null && !request.getParameter("cardNo").isEmpty())
		{
			Borrower borrower = null;
			try {
				borrower = borrowerService.readBorrowerByPK(Integer.parseInt(request.getParameter("cardNo")));
			} catch (NumberFormatException | SQLException e) {
				e.printStackTrace();
			}
			if(borrower == null) {
				message = "Invalid Card Number!";
				redirectUrl = "/receiveborrower.jsp";
			}
			else
			{
				cardNo = Integer.parseInt(request.getParameter("cardNo"));
			}
		}
		else {
			message = "No Card Found";
			redirectUrl = "/receiveborrower.jsp";
		}
		
		request.setAttribute("statusMessage", message);
		return redirectUrl;
	}
	private String pageBranch(HttpServletRequest request, HttpServletResponse response, String redirectUrl) {
		if (request.getParameter("pageNo") != null) {
			Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
			try {
				request.setAttribute("branches", borrowerService.readBranches(null, pageNo));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return redirectUrl;
		}

		return null;
	}
	private String checkIn(HttpServletRequest request, HttpServletResponse response, String redirectUrl) {
		String message ="";
		if(request.getParameter("branchId")!=null)
		{
			branchId = Integer.parseInt(request.getParameter("branchId"));
			try {
				if(borrowerService.readBookLoans(branchId, cardNo, null)!=null)
				{
					request.setAttribute("branchId", branchId);
					request.setAttribute("cardNo", cardNo);
					redirectUrl="/viewbranchloans.jsp";
				}
				else
				{
					message = "You have not loaned any books from this branch!";
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		request.setAttribute("statusMessage", message);
		return redirectUrl;
	}
	private String pageLoansCheckIn(HttpServletRequest request, HttpServletResponse response, String redirectUrl) {
		if (request.getParameter("pageNo") != null) {
			Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
			try {
				request.setAttribute("bookLoans", borrowerService.readBookLoans(branchId, cardNo, pageNo));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return redirectUrl;
		}
		return null;
	}
	private String checkOut(HttpServletRequest request, HttpServletResponse response, String redirectUrl) {
		
		if(request.getParameter("branchId")!=null)
		{
			branchId = Integer.parseInt(request.getParameter("branchId"));
			request.setAttribute("branchId", branchId);
			return redirectUrl;
		}
		return null;
	}
	private String pageBookCheckOut(HttpServletRequest request, HttpServletResponse response, String redirectUrl) {
		if (request.getParameter("pageNo") != null) {
			Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
			try {
				request.setAttribute("books", borrowerService.readBranchBooks(branchId, pageNo));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return redirectUrl;
		}
		return null;
	}
}
