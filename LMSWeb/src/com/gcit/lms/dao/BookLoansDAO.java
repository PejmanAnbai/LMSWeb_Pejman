package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookLoans;

public class BookLoansDAO extends BaseDAO <BookLoans>{
	public BookLoansDAO(Connection conn) {
		super(conn);
	}

	public void saveBookLoans(BookLoans bookLoans) throws SQLException {
		save("INSERT INTO tbl_book_loans VALUES (?, ?, ?, curdate(), curdate() + INTERVAL 7 DAY, ?)", new Object[] { bookLoans.getBook().getBookId(), bookLoans.getLibraryBranch().getBranchId(), bookLoans.getBorrower().getCardNo(), bookLoans.getDateIn()});
	}
	public void saveBookLoansWithOutDateIn(BookLoans bookLoans) throws SQLException {
		save("INSERT INTO tbl_book_loans VALUES (?, ?, ?, curdate(), curdate() + INTERVAL 7 DAY, null)", new Object[] { bookLoans.getBook().getBookId(), bookLoans.getLibraryBranch().getBranchId(), bookLoans.getBorrower().getCardNo()});
	}
	
//	public void saveBookAuthor(BookLoans bookLoans) throws SQLException {
//		for(Author a: book.getAuthors()){
//			save("INSERT INTO tbl_book_authors VALUES (?, ?)", new Object[] { book.getBookId(), a.getAuthorId()});
//		}
//	}
//	
//	public Integer saveBookID(BookLoans bookLoans) throws SQLException {
//		return saveWithID("INSERT INTO tbl_book (bookName) VALUES (?)", new Object[] { book.getTitle() });
//	}

	public void updateDueDate(BookLoans bookLoans) throws SQLException {
		save("UPDATE tbl_book_loans SET dueDate = ? WHERE bookId = ? AND branchId = ? AND cardNo = ?", new Object[] {bookLoans.getDueDate() , bookLoans.getBook().getBookId(), bookLoans.getLibraryBranch().getBranchId(), bookLoans.getBorrower().getCardNo() });
	}
	public void updateDateIn(BookLoans bookLoans) throws SQLException {
		save("UPDATE tbl_book_loans SET dateIn = curdate() WHERE bookId = ? AND branchId = ? AND cardNo = ?", new Object[] { bookLoans.getBook().getBookId(), bookLoans.getLibraryBranch().getBranchId(), bookLoans.getBorrower().getCardNo() });
	}
	public void updateDateIn(BookLoans bookLoans, Book book) throws SQLException {
		save("UPDATE tbl_book_loans SET dateIn = ? WHERE bookId = ? AND branchId = ? AND cardNo = ?", new Object[] { book.getBookId(), bookLoans.getLibraryBranch().getBranchId(), bookLoans.getBorrower().getCardNo() });
	}

	public void deleteBookLoans(BookLoans bookLoans) throws SQLException {
		save("DELETE FROM tbl_book_loans WHERE bookId = ? AND branchId = ? AND cardNo = ?", new Object[] { bookLoans.getBook().getBookId(), bookLoans.getLibraryBranch().getBranchId(), bookLoans.getBorrower().getCardNo() });
	}

	public List<BookLoans> readAllBookLoans() throws SQLException {
		return readAll("SELECT * FROM tbl_book_loans", null);
	}
	
	public List<BookLoans> readBooksByTitle(String bookTitle, String branchName, String borrowerName) throws SQLException {
		return readAll("SELECT * FROM tbl_book_loans WHERE bookId IN (SELECT bookId FROM tbl_book WHERE title = ? ) AND branchId IN (SELECT branchId FROM tbl_library_branch WHERE branchName = ?) AND cardNo IN (SELECT cardNo FROM tbl_borrower WHERE name = ?)", new Object[]{bookTitle, branchName, borrowerName});
	}
	public BookLoans readBookLoanByPK(Integer bookId, Integer branchId, Integer cardNo) throws SQLException {
		List<BookLoans> bookLoans = readAll("SELECT * FROM tbl_book_loans WHERE bookId = ? AND branchId = ? AND cardNo = ?", new Object[]{bookId, branchId, cardNo});
		if(bookLoans!=null && bookLoans.size()>0){
			return bookLoans.get(0);
		}
		return null;
	}
	public List<BookLoans> readBookLoans(Integer branchId, Integer cardNo, Integer pageNo) throws SQLException {
		setPageNo(pageNo);
		List<BookLoans> bookLoans = readAll("SELECT * FROM tbl_book_loans WHERE branchId = ? AND cardNo = ? AND dateIn IS NULL", new Object[]{ branchId, cardNo});
		if(bookLoans!=null && bookLoans.size()>0){
			return bookLoans;
		}
		return null;
	}
	public Integer getBookLoansCount(Integer branchId, Integer cardNo) throws SQLException {
		return getCount("SELECT count(*) as COUNT FROM tbl_book_loans WHERE branchId = ? AND cardNo = ? AND dateIn IS NULL", new Object[]{ branchId, cardNo});
	}
	public Integer getBookLoansCount() throws SQLException {
		return getCount("SELECT count(*) as COUNT FROM tbl_book_loans", null);
	}
	
	public List<BookLoans> readBookLoans(String bookTitle, Integer pageNo) throws SQLException {
		setPageNo(pageNo);
			return readAll("SELECT * FROM tbl_book_Loans", null);		
	}
	@Override
	public List<BookLoans> extractData(ResultSet rs) throws SQLException {
		BookDAO adao = new BookDAO(conn);
		BorrowerDAO bdao = new BorrowerDAO(conn);
		LibraryBranchDAO cdao = new LibraryBranchDAO(conn);
		List<BookLoans> bookLoans = new ArrayList<>();
		while (rs.next()) {
			BookLoans b = new BookLoans();
			b.setDateIn(rs.getString("dateIn"));
			b.setDueDate(rs.getString("dueDate"));
			b.setDateOut(rs.getString("dateOut"));
			b.setBook((adao.readAllFirstLevel("SELECT * FROM tbl_book WHERE bookId = ?", new Object[]{rs.getInt("bookId")})).get(0));
			b.setBorrower((bdao.readAllFirstLevel("SELECT * FROM tbl_borrower WHERE cardNo = ?", new Object[]{rs.getInt("cardNo")})).get(0));
			b.setLibraryBranch((cdao.readAllFirstLevel("SELECT * FROM tbl_library_branch WHERE branchId = ?", new Object[]{rs.getInt("branchId")})).get(0));
			//do the same for genres
			//do the same for One Publisher
			bookLoans.add(b);
		}
		return bookLoans;
	}
	
	@Override
	public List<BookLoans> extractDataFirstLevel(ResultSet rs) throws SQLException {
		List<BookLoans> bookLoans = new ArrayList<>();
		while (rs.next()) {
			BookLoans b = new BookLoans();
			b.setDateIn(rs.getString("dateIn"));
			b.setDueDate(rs.getString("dueDate"));
			b.setDateOut(rs.getString("dateOut"));
			bookLoans.add(b);
		}
		return bookLoans;
	}

}
