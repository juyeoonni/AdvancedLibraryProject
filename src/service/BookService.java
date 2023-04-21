package service;

import java.util.List;

import dao.BookDAO;
import dto.BookDTO;

public class BookService {

	private BookDAO bdao = new BookDAO();

	// selectBook()_전체 도서 조회
	public List<BookDTO> selectBook() {
		List<BookDTO> list = bdao.selectBook();
		return list;
	}

	// insertBook()_새로운 도서 등록
	public int insertBook(BookDTO book) {
		int result = bdao.insertBook(book);
		return result;
	}

	// updateBook()_도서 정보 수정
	public int updateBook(BookDTO book) {
		int result = bdao.updateBook(book);
		return result;
	}

	// deleteBook()_도서 삭제
	public int deleteBook(String bookID) {
		int result = bdao.deleteBook(bookID);
		return result;
	}

	// redoDeleteMember()_회원 삭제 취소
	public int redoDeleteBook() {
		int result = bdao.redoDeleteBook();
		return result;
	}

}
