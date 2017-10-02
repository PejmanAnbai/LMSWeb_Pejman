package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookCopies;
import com.gcit.lms.entity.LibraryBranch;
public class BookCopiesDAO extends BaseDAO<BookCopies>{
	
	public BookCopiesDAO(Connection conn) {
		super(conn);
	}

	public void saveBookCopies(BookCopies bookCopies) throws SQLException {
		save("INSERT INTO tbl_book_copies noOfCopies VALUES (?, ?, ?)", new Object[] { bookCopies.getBook().getBookId(), bookCopies.getLibraryBranch().getBranchId(), bookCopies.getNoOfCopies() });
	}
	
//	public void saveBookAuthor(BookCopies bookCopies) throws SQLException {
//		for(Author a: book.getAuthors()){
//			save("INSERT INTO tbl_book_authors VALUES (?, ?)", new Object[] { book.getBookId(), a.getAuthorId()});
//		}
//	}
	
//	public Integer saveBookID(BookCopies bookCopies) throws SQLException {
//		return saveWithID("INSERT INTO tbl_book (bookName) VALUES (?)", new Object[] { book.getTitle() });
//	}

	public void updateBookCopies(BookCopies bookCopies, Integer noOfCopies) throws SQLException {
		save("UPDATE tbl_book_copies SET noOfCopies = ? WHERE bookId = ? AND branchId =?", new Object[] { noOfCopies,  bookCopies.getBook().getBookId(), bookCopies.getLibraryBranch().getBranchId()});
	}
	public void updateBookCopies(Integer bookId, Integer branchId, Integer noOfCopies) throws SQLException {
		save("UPDATE tbl_book_copies SET noOfCopies = ? WHERE bookId = ? AND branchId =?", new Object[] { noOfCopies,  bookId, branchId});
	}

	public void deleteBookCopies(BookCopies bookCopies) throws SQLException {
		save("DELETE FROM tbl_book_copies WHERE bookId = ? AND branchId =?", new Object[] { bookCopies.getBook().getBookId(), bookCopies.getLibraryBranch().getBranchId() });
	}

//	public List<BookCopies> readAllBooks() throws SQLException {
//		return readAll("SELECT * FROM tbl_book", null);
//	}
	
	public BookCopies readBookCopies(Book book, LibraryBranch libraryBranch) throws SQLException {
		if(readAll("SELECT * FROM tbl_book_copies WHERE bookId = ? AND branchId = ?", new Object[]{book.getBookId(), libraryBranch.getBranchId()}).size()>0)
			return readAll("SELECT * FROM tbl_book_copies WHERE bookId = ? AND branchId = ?", new Object[]{book.getBookId(), libraryBranch.getBranchId()}).get(0);
		return null;
	}
	public BookCopies readBookCopies(Integer bookId, Integer branchId) throws SQLException {
		if(readAll("SELECT * FROM tbl_book_copies WHERE bookId = ? AND branchId = ?", new Object[]{bookId, branchId}).size()>0)
			return readAll("SELECT * FROM tbl_book_copies WHERE bookId = ? AND branchId = ?", new Object[]{bookId, branchId}).get(0);
		return null;
	}

	@Override
	public List<BookCopies> extractData(ResultSet rs) throws SQLException {
		BookDAO adao = new BookDAO(conn);
		LibraryBranchDAO bdao = new LibraryBranchDAO(conn);
		List<BookCopies> bookCopies = new ArrayList<>();
		while (rs.next()) {
			BookCopies b = new BookCopies();
			b.setNoOfCopies(rs.getInt("noOfCopies"));
			if(adao.readAllFirstLevel("SELECT * FROM tbl_book WHERE bookId = ?", new Object[]{rs.getInt("bookId")}).size()>0)
				b.setBook((adao.readAllFirstLevel("SELECT * FROM tbl_book WHERE bookId = ?", new Object[]{rs.getInt("bookId")})).get(0));
			if(bdao.readAllFirstLevel("SELECT * FROM tbl_library_branch WHERE branchId = ?", new Object[]{rs.getInt("branchId")}).size()>0)
				b.setLibraryBranch((bdao.readAllFirstLevel("SELECT * FROM tbl_library_branch WHERE branchId = ?", new Object[]{rs.getInt("branchId")}).get(0)));
			bookCopies.add(b);
		}
		return bookCopies;
	}
	
	@Override
	public List<BookCopies> extractDataFirstLevel(ResultSet rs) throws SQLException {
		List<BookCopies> bookCopies = new ArrayList<>();
		while (rs.next()) {
			BookCopies b = new BookCopies();
			b.setNoOfCopies(rs.getInt("noOfCopies"));
			bookCopies.add(b);
		}
		return bookCopies;
	}

}
