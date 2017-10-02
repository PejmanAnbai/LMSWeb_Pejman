package com.gcit.lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import com.gcit.lms.dao.BookCopiesDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.LibraryBranchDAO;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookCopies;
import com.gcit.lms.entity.LibraryBranch;

public class LibrarianService {
	
	public Utilities util = new Utilities();
	public List<LibraryBranch> readAllBranches() throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			LibraryBranchDAO adao = new LibraryBranchDAO(conn);
			return adao.readAllBranches();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		return null;
	}
	public void updateLibraryBranch(LibraryBranch libraryBranch) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			LibraryBranchDAO ldao = new LibraryBranchDAO(conn);
			ldao.updateLibraryBranch(libraryBranch);
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
	public BookCopies readBookCopies(Integer bookId, Integer branchId) throws SQLException
	{
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookCopiesDAO adao = new BookCopiesDAO(conn);
			return adao.readBookCopies(bookId, branchId);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		
		return null;
	}
	public Book readBookByPK(Integer bookId) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookDAO adao = new BookDAO(conn);
			return adao.readBookByPK(bookId);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		
		return null;
	}
	public void updateNoOfCopies(BookCopies bookCopies, Integer noOfCopies) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookCopiesDAO bdao = new BookCopiesDAO(conn);
			if(bookCopies.getBook().getBookId()!=null &&
					bookCopies.getLibraryBranch().getBranchId() != null && bookCopies.getNoOfCopies() == null){
				bdao.updateBookCopies(bookCopies, noOfCopies);
			}
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
	public void updateNoOfCopies(Integer bookId, Integer branchId, Integer noOfCopies) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookCopiesDAO bdao = new BookCopiesDAO(conn);
			bdao.updateBookCopies(bookId, branchId, noOfCopies);
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
//	public List<BookCopies> readCopies(Book book, LibraryBranch libraryBranch) throws SQLException{
//		Connection conn = null;
//		try {
//			conn = util.getConnection();
//			BookCopiesDAO ldao = new BookCopiesDAO(conn);
//			return ldao.readBookCopies(book, libraryBranch);
//		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//		} finally{
//			if(conn!=null){
//				conn.close();
//			}
//		}
//		return null;
//	}
	public Integer getBranchCount() throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			LibraryBranchDAO adao = new LibraryBranchDAO(conn);
			return adao.getBranchCount();
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
	
	
	public LibraryBranch readBranchByPK(Integer branchId) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			LibraryBranchDAO gdao = new LibraryBranchDAO(conn);
			return gdao.readBranchByPK(branchId);
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
}
