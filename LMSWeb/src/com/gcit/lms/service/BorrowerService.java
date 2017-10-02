package com.gcit.lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.gcit.lms.dao.BookCopiesDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.BookLoansDAO;
import com.gcit.lms.dao.BorrowerDAO;
import com.gcit.lms.dao.LibraryBranchDAO;
import com.gcit.lms.dao.PublisherDAO;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookCopies;
import com.gcit.lms.entity.BookLoans;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.LibraryBranch;

public class BorrowerService {
	
	public Utilities util = new Utilities();
	
	public List<LibraryBranch> readBranch(String searchString) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			LibraryBranchDAO ldao = new LibraryBranchDAO(conn);
			return ldao.readLibraryBranchByName(searchString);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		return null;
	}
	public Integer getBranchCount() throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			PublisherDAO adao = new PublisherDAO(conn);
			return adao.getPublisherCount();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		
		return null;
	}
	public List<LibraryBranch> readBranches(String searchString, Integer pageNo) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			LibraryBranchDAO adao = new LibraryBranchDAO(conn);
			return adao.readBranches(searchString, pageNo);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		
		return null;
	}
	
	public List<Book> readBranchBooks(LibraryBranch libraryBranch) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookDAO bdao = new BookDAO(conn);
			return bdao.readBranchBooks(libraryBranch);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		return null;
	}
	
	public Integer readNoOfCopies(Book book, LibraryBranch libraryBranch) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookCopiesDAO bdao = new BookCopiesDAO(conn);
			if(book.getBookId()!=null && libraryBranch.getBranchId()!=null && bdao.readBookCopies(book, libraryBranch)!=null)
				return bdao.readBookCopies(book, libraryBranch).getNoOfCopies();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		return null;
	}
	public Integer getBookCount(Integer branchId) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookDAO adao = new BookDAO(conn);
			return adao.getBooksCount(branchId);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		
		return null;
	}
	public Integer getBookLoansCount(Integer branchId, Integer cardNo) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookLoansDAO adao = new BookLoansDAO(conn);
			return adao.getBookLoansCount(branchId, cardNo);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		
		return null;
	}
	public List<Book> readBranchBooks(Integer branchId, Integer pageNo) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookDAO adao = new BookDAO(conn);
			return adao.readBooks(branchId, pageNo);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		
		return null;
	}
	
	public void borrowBook(LibraryBranch libraryBranch, Book book, Borrower borrower) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookLoans bookLoans = new BookLoans();
			bookLoans.setBook(book);
			bookLoans.setBorrower(borrower);
			bookLoans.setLibraryBranch(libraryBranch);
			BookLoansDAO ldao = new BookLoansDAO(conn);
			BookCopiesDAO bdao = new BookCopiesDAO(conn);
			ldao.saveBookLoansWithOutDateIn(bookLoans);	
			BookCopies bookCopies = bdao.readBookCopies(book, libraryBranch);
			bdao.updateBookCopies(bookCopies, bookCopies.getNoOfCopies()-1);
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
	}
	public void returnBook(LibraryBranch libraryBranch, Book book, Borrower borrower) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookLoans bookLoans = new BookLoans();
			bookLoans.setBook(book);
			bookLoans.setBorrower(borrower);
			bookLoans.setLibraryBranch(libraryBranch);
			BookLoansDAO ldao = new BookLoansDAO(conn);
			BookCopiesDAO bdao = new BookCopiesDAO(conn);
			ldao.updateDateIn(bookLoans);
			BookCopies bookCopies = bdao.readBookCopies(book, libraryBranch);
			bdao.updateBookCopies(bookCopies, bookCopies.getNoOfCopies()+1);
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
	}
	public Borrower readBorrowerByPK(Integer cardNo) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			BorrowerDAO gdao = new BorrowerDAO(conn);
			return gdao.readBorrowerByPK(cardNo);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		
		return null;
	}
	
	public BookLoans readBookLoanByPK(Integer bookId, Integer branchId, Integer cardNo) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookLoansDAO gdao = new BookLoansDAO(conn);
			return gdao.readBookLoanByPK(bookId, branchId, cardNo);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		
		return null;
	}
	public List<BookLoans> readBookLoans(Integer branchId, Integer cardNo, Integer pageNo) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookLoansDAO gdao = new BookLoansDAO(conn);
			return gdao.readBookLoans(branchId, cardNo, pageNo);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		
		return null;
	}
}
