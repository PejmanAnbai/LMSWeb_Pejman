package com.gcit.lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.BookCopiesDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.BookLoansDAO;
import com.gcit.lms.dao.BorrowerDAO;
import com.gcit.lms.dao.GenreDAO;
import com.gcit.lms.dao.LibraryBranchDAO;
import com.gcit.lms.dao.PublisherDAO;
import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookCopies;
import com.gcit.lms.entity.BookLoans;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Genre;
import com.gcit.lms.entity.LibraryBranch;
import com.gcit.lms.entity.Publisher;

public class AdminService {
	
	public Utilities util = new Utilities();
	
	public void saveAuthor(Author author) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			if(author.getAuthorId()!=null){
				adao.updateAuthor(author);
				adao.deleteBookAuthor(author);
			}else{
				int id = adao.saveAuthorWithID(author);
				author.setAuthorId(id);
			}
			adao.saveBookAuthor(author);
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
	
	
	public Integer getAuthorsCount() throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			return adao.getAuthorsCount();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		
		return null;
	}
	
	public List<Author> readAuthors(String searchString, Integer pageNo) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			return adao.readAuthors(searchString, pageNo);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		
		return null;
	}
	public List<Author> readAuthors() throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			return adao.readAuthors();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		
		return null;
	}
	
	public Author readAuthorByPK(Integer authorId) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			return adao.readAuthorByPK(authorId);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		
		return null;
	}
	
	public void deleteAuthor(Author author) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			if(author.getAuthorId()!=null){
				adao.deleteAuthor(author);
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
	
//	public List<Author> readAuthors(String searchString) throws SQLException{
//		Connection conn = null;
//		try {
//			conn = util.getConnection();
//			AuthorDAO adao = new AuthorDAO(conn);
//			return adao.readAuthors(searchString);
//		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//		} finally{
//			if(conn!=null){
//				conn.close();
//			}
//		}
//		
//		return null;
//	}
	public void saveGenre(Genre genre) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			GenreDAO gdao = new GenreDAO(conn);
			if(genre.getGenreId()!=null){
				gdao.updateGenre(genre);
				gdao.deleteBookGenre(genre);
			}else{
				int id = gdao.saveGenreID(genre);
				genre.setGenreId(id);				
			}
			gdao.saveBookGenre(genre);
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
	
	public Integer getGenresCount() throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			GenreDAO adao = new GenreDAO(conn);
			return adao.getGenresCount();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		
		return null;
	}
	public Genre readGenreByPK(Integer genreId) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			GenreDAO gdao = new GenreDAO(conn);
			return gdao.readGenreByPK(genreId);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		
		return null;
	}
	public List<Genre> readGenres() throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			GenreDAO adao = new GenreDAO(conn);
			return adao.readGenres();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		
		return null;
	}
	public List<Genre> readGenres(String searchString, Integer pageNo) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			GenreDAO adao = new GenreDAO(conn);
			return adao.readGenres(searchString, pageNo);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		
		return null;
	}
	
	public void deleteGenre(Genre genre) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			GenreDAO gdao = new GenreDAO(conn);
			if(genre.getGenreId()!=null){
				gdao.deleteGenre(genre);
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
	
	public List<Genre> readGenre(String searchString) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			GenreDAO gdao = new GenreDAO(conn);
			return gdao.readGenresByName(searchString);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		
		return null;
	}
	public void savePublisher(Publisher publisher) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			PublisherDAO pdao = new PublisherDAO(conn);
			if(publisher.getPublisherId()!=null){
				pdao.updatePublisher(publisher);
			}else{
				pdao.savePublisher(publisher);
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
	public Publisher readPublisherByPK(Integer publisherId) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			PublisherDAO gdao = new PublisherDAO(conn);
			return gdao.readPublisherByPK(publisherId);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		
		return null;
	}
	public Integer getPublisherCount() throws SQLException{
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
	public List<Publisher> readPublishers(String searchString, Integer pageNo) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			PublisherDAO adao = new PublisherDAO(conn);
			return adao.readPublishers(searchString, pageNo);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		
		return null;
	}
	public List<Publisher> readPublishers() throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			PublisherDAO adao = new PublisherDAO(conn);
			return adao.readPublisher(null);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		
		return null;
	}
	public void deletePublisher(Publisher publisher) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			PublisherDAO pdao = new PublisherDAO(conn);
			if(publisher.getPublisherId()!=null){
				pdao.deletePublisher(publisher);
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
	
	public List<Publisher> readPublisher(String searchString) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			PublisherDAO pdao = new PublisherDAO(conn);
			return pdao.readPublisher(searchString);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		
		return null;
	}
	public void saveBook(Book book) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookDAO bdao = new BookDAO(conn);
			if(book.getBookId()!=null){
				bdao.updateBook(book);
				bdao.deleteBookGenre(book);
				bdao.deleteBookAuthor(book);
			}else{
				int id = bdao.saveBookID(book);
				book.setBookId(id);
			}
			bdao.saveBookGenre(book);
			bdao.saveBookAuthor(book);
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
	public Integer getBooksCount() throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookDAO adao = new BookDAO(conn);
			return adao.getBooksCount();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		
		return null;
	}
	
	public List<Book> readBooks(String searchString, Integer pageNo) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookDAO adao = new BookDAO(conn);
			return adao.readBooks(searchString, pageNo);
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
	
	public void deleteBook(Book book) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookDAO bdao = new BookDAO(conn);
			if(book.getBookId()!=null){
				bdao.deleteBook(book);
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
	public List<Book> readBooks() throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookDAO bdao = new BookDAO(conn);
			return bdao.readAllBooks();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		
		return null;
	}
	public List<Book> readBook(String searchString) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookDAO bdao = new BookDAO(conn);
			return bdao.readBooksByTitle(searchString);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		
		return null;
	}
	public void saveBranch(LibraryBranch libraryBranch) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			LibraryBranchDAO ldao = new LibraryBranchDAO(conn);
			if(libraryBranch.getBranchId()!=null){
				ldao.updateLibraryBranch(libraryBranch);
				ldao.deleteBranchBooks(libraryBranch);
			}else{
				int id = ldao.saveLibraryBranchID(libraryBranch);
				libraryBranch.setBranchId(id);
			}
			ldao.saveBranchBooks(libraryBranch);
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
			BookDAO adao = new BookDAO(conn);
			return adao.readBranchBooks(libraryBranch);
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
	public void deleteBranch(LibraryBranch libraryBranch) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			LibraryBranchDAO ldao = new LibraryBranchDAO(conn);
			if(libraryBranch.getBranchId()!=null){
				ldao.deleteLibraryBranch(libraryBranch);
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
	public void saveBorrower(Borrower borrower) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			BorrowerDAO bdao = new BorrowerDAO(conn);
			if(borrower.getCardNo()!=null){
				bdao.updateBorrower(borrower);
			}else{
				bdao.saveBorrower(borrower);
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
	public Integer getBorrowerCount() throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			BorrowerDAO adao = new BorrowerDAO(conn);
			return adao.getBorrowersCount();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		
		return null;
	}
	public List<Borrower> readBorrowers(String searchString, Integer pageNo) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			BorrowerDAO adao = new BorrowerDAO(conn);
			return adao.readBorrowers(searchString, pageNo);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		
		return null;
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
	public void deleteBorrower(Borrower borrower) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			BorrowerDAO bdao = new BorrowerDAO(conn);
			if(borrower.getCardNo()!=null){
				bdao.deleteBorrower(borrower);
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
	
	public List<Borrower> readBorrower(String searchString) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			BorrowerDAO bdao = new BorrowerDAO(conn);
			return bdao.readBorrowerByName(searchString);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		
		return null;
	}
	public void deleteBookLoan(BookLoans bookLoans) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookLoansDAO bdao = new BookLoansDAO(conn);
			if(bookLoans.getBook().getBookId()!=null && bookLoans.getBorrower().getCardNo() != null &&
					bookLoans.getLibraryBranch().getBranchId() != null && bookLoans.getDateIn() == null){
				bdao.deleteBookLoans(bookLoans);
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
	public void updateDueDate(BookLoans bookLoans) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookLoansDAO bdao = new BookLoansDAO(conn);
			if(bookLoans.getBook().getBookId()!=null && bookLoans.getBorrower().getCardNo() != null &&
					bookLoans.getLibraryBranch().getBranchId() != null && bookLoans.getDateIn() == null){
				bdao.updateDueDate(bookLoans);
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
	
	public List<BookLoans> readBookLoans() throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookLoansDAO bdao = new BookLoansDAO(conn);
			return bdao.readAllBookLoans();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		return null;
	}
	public Integer getBookLoansCount() throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookLoansDAO adao = new BookLoansDAO(conn);
			return adao.getBookLoansCount();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		
		return null;
	}
	public List<BookLoans> readBookLoans(String searchString, Integer pageNo) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookLoansDAO adao = new BookLoansDAO(conn);
			return adao.readBookLoans(searchString, pageNo);
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
}
