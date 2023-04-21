package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dto.LoanDTO;
import dto.BookDTO;

public class LoanDAO {
	// 대출 목록 조회
	public ArrayList<LoanDTO> selectAllLoanList() {
		ArrayList<LoanDTO> list = new ArrayList<LoanDTO>();
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@192.168.119.119:1521/dink";
			String user = "scott";
			String passwd = "tiger";
			con = DriverManager.getConnection(url, user, passwd);
			con.setAutoCommit(false);
			String sql = "SELECT * "
						+ "FROM LOAN "
						+ "ORDER BY LOANPERIOD";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				LoanDTO dto = new LoanDTO();
				dto.setId(rs.getString("ID"));
				dto.setMemberID(rs.getString("memberID"));
				dto.setBookID(rs.getString("bookID"));
				dto.setLoanDate(rs.getString("LoanDate"));
				dto.setLoanStatus(rs.getString("LOANSTATUS").equals("Y") ? true : false);
				dto.setLoanPeriod(rs.getInt("LoanPeriod"));
				dto.setIsExtension(rs.getString("LOANEXTENSION").equals("Y") ? true : false);
				
				list.add(dto);
				}
			con.commit(); // 수동 commit
		} catch (Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	// 도서 대출
	public int loanBook(LoanDTO dto) {
		int result = 0;
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@192.168.119.119:1521/dink";
			String user = "scott";
			String passwd = "tiger";
			con = DriverManager.getConnection(url, user, passwd);
			con.setAutoCommit(false);
			String sql1 = "INSERT INTO LOAN(ID, MEMBERID, BOOKID, LOANDATE) "
					+ "VALUES(SCOTT.id_SEQ.NEXTVAL, ?, ?, TRUNC(SYSDATE))";
			String sql2 = "UPDATE BOOK SET isAvailable = 'N' WHERE BOOKID = ?";

			PreparedStatement ps1 = con.prepareStatement(sql1);
			PreparedStatement ps2 = con.prepareStatement(sql2);

			ps1.setString(1, dto.getMemberID());
			ps1.setString(2, dto.getBookID());
			ps2.setString(1, dto.getBookID());

			result = ps1.executeUpdate();
			ps2.executeUpdate();
			System.out.println("삽입 행의 갯수 = " + result);
			con.commit(); // 수동 commit
		} catch (Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	// 도서 대출 연장 가능 도서 조회
	public ArrayList<LoanDTO> renewableList(String id) {
		ArrayList<LoanDTO> list = new ArrayList<LoanDTO>();
		Connection con = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@192.168.119.119:1521/dink";
			String user = "scott";
			String passwd = "tiger";
			con = DriverManager.getConnection(url, user, passwd);
			con.setAutoCommit(false);
			String sql = "SELECT L.ID, L.MEMBERID, B.TITLE, L.LOANSTATUS, L.LOANDATE, L.LOANPERIOD, L.LOANEXTENSION "
					+ "FROM LOAN L INNER JOIN BOOK B ON L.BOOKID = B.BOOKID WHERE L.MEMBERID = ? "
					+ "AND LOANSTATUS = 'N' AND LOANEXTENSION = 'N'";

			PreparedStatement pt = con.prepareStatement(sql);
			pt.setString(1, id);
			ResultSet rs = pt.executeQuery();

			while (rs.next()) {
				LoanDTO dto = new LoanDTO();
				dto.setId(rs.getString("ID"));
				dto.setMemberID(rs.getString("memberID"));
				dto.setBookID(rs.getString("TITLE"));
				dto.setLoanStatus(rs.getString("LOANSTATUS").equals("Y") ? true : false);
				dto.setLoanDate(rs.getString("LoanDate"));
				dto.setLoanPeriod(rs.getInt("LoanPeriod"));
				dto.setIsExtension(rs.getString("LOANEXTENSION").equals("Y") ? true : false);

				list.add(dto);
			}
			con.commit(); // 수동 commit
		} catch (Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	// 도서 대출 연장 (update LOANEXTENSION이 N인 것을 Y로)
	public int extendLoan(String memberID, String bookID) {
		int result = 0;
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@192.168.119.119:1521/dink";
			String user = "scott";
			String passwd = "tiger";
			con = DriverManager.getConnection(url, user, passwd);
			con.setAutoCommit(false);
			String sql = "update loan set LOANEXTENSION = 'Y', LOANPERIOD = LOANPERIOD + 7 where BOOKID = (SELECT BOOKID FROM BOOK WHERE TITLE = ?) AND MEMBERID = ?";

			PreparedStatement pt = con.prepareStatement(sql);

			pt.setString(1, bookID);
			pt.setString(2, memberID);

			result = pt.executeUpdate();
			System.out.println("삽입 행의 갯수 = " + result);
			con.commit(); // 수동 commit
		} catch (Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	// 도서 반납 (UPDATE, 반납여부를 N에서 YFH)
	public int returnBook(String memberID, String bookID) {
		int result = 0;
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@192.168.119.119:1521/dink";
			String user = "scott";
			String passwd = "tiger";
			con = DriverManager.getConnection(url, user, passwd);
			con.setAutoCommit(false);
			String sql1 = "UPDATE LOAN SET LOANSTATUS = 'Y' WHERE BOOKID = ? AND MEMBERID = ?";
			String sql2 = "UPDATE BOOK SET isAvailable = 'Y' WHERE BOOKID = ?";
			
			PreparedStatement ps1 = con.prepareStatement(sql1);
			PreparedStatement ps2 = con.prepareStatement(sql2);


			ps1.setString(1, bookID);
			ps1.setString(2, memberID);
			ps2.setString(1, bookID);

			result = ps1.executeUpdate();
			ps2.executeUpdate();
			System.out.println("삽입 행의 갯수 = " + result);
			con.commit(); // 수동 commit
		} catch (Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
