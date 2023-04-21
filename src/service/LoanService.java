package service;

import java.util.List;

import dao.LoanDAO;
import dto.LoanDTO;

public class LoanService {
	
	private LoanDAO ldao = new LoanDAO();
	
	// 도서 대출 목록 조회
	public List<LoanDTO> selectAllLoanList() {
		List<LoanDTO> result = ldao.selectAllLoanList();
		return result;
	}

	// 도서 대출
	public int loanBook(LoanDTO dto) {
		int result = ldao.loanBook(dto);
		return result;
	}

	// 도서 대출 연장 가능 도서 조회
	public  List<LoanDTO> renewableList(String id) {
		 List<LoanDTO> result = ldao.renewableList(id);
		return result;
	}

	// 도서 대출 연장
	public int extendLoan(String memberID, String bookID) {
		int result = ldao.extendLoan(memberID, bookID);
		return result;
	}
	
	// 도서 반납
	public int returnBook(String memberID, String bookID) {
		int result = ldao.returnBook(memberID, bookID);
		return result;
	}

}
