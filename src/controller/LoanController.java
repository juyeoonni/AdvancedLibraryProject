package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dto.BookDTO;
import dto.LoanDTO;
import dto.MemberDTO;
import service.LoanService;

public class LoanController {
	private Scanner scanner = new Scanner(System.in);

	private LoanService ls = new LoanService();

	// 도서 대출 목록 조회
	public void selectAllLoanList() {
		System.out.println("* 도서 대출 목록을 조회합니다. *");
		List<LoanDTO> list = ls.selectAllLoanList();
		for (LoanDTO lList : list) { // 뷰
			System.out.println(lList);
		}
	}
	
	// 도서 대출
	public void loanBook() {
		System.out.println("* 도서를 대출하겠습니다. 다음 정보를 입력하세요 *");
		
		System.out.println("회원 ID: ");
		String memberID = scanner.nextLine();

		System.out.println("도서 ID: ");
		String bookID = scanner.nextLine();
		
		System.out.println("대출이 완료되었습니다.");
		
		LoanDTO dto = new LoanDTO();
		dto.setMemberID(memberID); 
		dto.setBookID(bookID); 

		int cnt = ls.loanBook(dto);
		
	}
	
	// 도서 대출 연장 가능 도서 조회
	public String renewableList() {
		System.out.println("* 연장 가능한 도서를 조회하겠습니다. 대출 ID를 입력하세요 *");
		
		System.out.println("회원 ID: ");
		String id = scanner.nextLine();
		
		System.out.println("연장 가능한 도서가 조회되었습니다.");
		List<LoanDTO> list = ls.renewableList(id);
		for (LoanDTO lList : list) { // 뷰
			System.out.println(lList);
		}
		return id;
	}
		
	// 도서 대출 연장
	public void extendLoan(String memberID){
		System.out.println("* 연장하고 싶은 책의 제목을 입력하세요. *");
		
		System.out.println("도서 제목: ");
		String TITLE = scanner.nextLine();
		
		System.out.println("연장이 완료되었습니다.");
		
		int cnt = ls.extendLoan(memberID, TITLE);
		
	}
		
	// 도서 반납
	public void returnBook(){
		System.out.println("도서를 반납합니다.");

		System.out.println("회원 ID: ");
		String memberID = scanner.nextLine();
		
		System.out.println("도서 ID: ");
		String bookID = scanner.nextLine();
		
		System.out.println("반납이 완료되었습니다.");
		
		int cnt = ls.returnBook(memberID, bookID);
		
	}
	
}
