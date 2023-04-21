package controller;

import java.util.List;
import java.util.Scanner;

import dto.BookDTO;
import service.BookService;

public class BookController {
	private Scanner scanner = new Scanner(System.in);

	private BookService bs = new BookService();

	// selectBook()_도서 조회
	public void selectBook() { // 컨트롤러
		System.out.println("* 도서조회 화면입니다. *");
		List<BookDTO> list = bs.selectBook(); // 서비스
		for (BookDTO m : list) { // 뷰
			System.out.println(m);
		}
	}

	// insertBook()_도서등록
	public void insertBook() {
		System.out.println("* 도서 등록을 시작합니다. 아래 정보를 기입하세요. *");
		System.out.println("제목: ");
		String title = scanner.nextLine();

		System.out.println("출판일: ");
		String publicationDate = scanner.nextLine();

		// System.out.println("대여 가능 여부: ");
		// String isAvailable = scanner.nextLine();

		System.out.println("등록이 완료되었습니다.");
		BookDTO dto = new BookDTO();
		dto.setTitle(title); // 뷰
		dto.setPublicationDate(publicationDate); // 뷰
		// dto.setIsAvailable(isAvailable); // 뷰

		int cnt = bs.insertBook(dto);
	}

	// updateBook()_도서 정보 수정
	public void updateBook() {
		System.out.println("* 도서 정보를 수정합니다. 아래 정보를 기입하세요. *");

		System.out.println("수정할 책 ID: ");
		String bookID = scanner.nextLine();

		System.out.println("수정할 책 제목: ");
		String title = scanner.nextLine();

		System.out.println("수정할 도서 출판일(년/월/일 형식): ");
		String publicationDate = scanner.nextLine();

		System.out.println("수정할 도서 대여 가능 여부: ");
		String isAvailable = scanner.nextLine();

		System.out.println("등록이 완료되었습니다.");
		BookDTO dto = new BookDTO();
		dto.setBookID(bookID); // 뷰
		dto.setTitle(title); // 뷰
		dto.setPublicationDate(publicationDate); // 뷰
		dto.setIsAvailable(isAvailable); // 뷰

		int result = bs.updateBook(dto);
		if (result > 0) {
			System.out.println("도서 정보가 성공적으로 업데이트되었습니다.");
		} else {
			System.out.println("도서 정보 업데이트에 실패하였습니다.");
		}

	}

	// deleteBook()_도서 삭제
	public void deleteBook() {
		System.out.println("* 도서 정보를 삭제합니다. 아래 정보를 기입하세요. *");
		System.out.println("삭제하고 싶은 도서ID: ");
		String bookID = scanner.next();
		int cnt = bs.deleteBook(bookID);
		System.out.println("삭제가 완료되었습니다.");
	}

	// redoDeleteBook()_회원 삭제 취소
	public void redoDeleteBook() {
		System.out.println("삭제를 취소하겠습니다.");
		int result = bs.redoDeleteBook();
	}
}
