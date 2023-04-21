package dto;

import java.sql.Date;

public class LoanDTO {
	String id; // id (Loan테이블 PK)
	String memberID; // 회원 id (Loan테이블 FK)
	String bookID; // 책 id (Loan테이블 FK)
	String loanDate; // 대출일
	boolean loanStatus; // 반납여부
	int loanPeriod; // 대출 만료 기간
	boolean isExtension; // 대출연장여부

	public LoanDTO() {

	}
	

	public LoanDTO(String id, String memberID, String bookID, String loanDate, boolean loanStatus, int loanPeriod,
			boolean isExtension) {
		super();
		this.id = id;
		this.memberID = memberID;
		this.bookID = bookID;
		this.loanDate = loanDate;
		this.loanStatus = loanStatus;
		this.loanPeriod = loanPeriod;
		this.isExtension = isExtension;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMemberID() {
		return memberID;
	}

	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}

	public String getBookID() {
		return bookID;
	}

	public void setBookID(String bookID) {
		this.bookID = bookID;
	}

	public String getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(String loanDate) {
		this.loanDate = loanDate;
	}

	public boolean getLoanStatus() {
		return loanStatus;
	}

	public void setLoanStatus(boolean loanStatus) {
		this.loanStatus = loanStatus;
	}

	public int getLoanPeriod() {
		return loanPeriod;
	}

	public void setLoanPeriod(int loanPeriod) {
		this.loanPeriod = loanPeriod;
	}

	public boolean getIsExtension() {
		return isExtension;
	}

	public void setIsExtension(boolean isExtension) {
		this.isExtension = isExtension;
	}

	@Override
	public String toString() {
		return "LoanDTO [id=" + id + ", memberID=" + memberID + ", bookID=" + bookID + ", loanDate=" + loanDate
				+ ", loanStatus=" + loanStatus + ", loanPeriod=" + loanPeriod + ", isExtension=" + isExtension + "]";
	}

}