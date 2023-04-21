package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dto.BookDTO;

public class BookDAO {

	String saveDelete = null;

	// 도서 조회 (select)_최근출간된 순서로
	public ArrayList<BookDTO> selectBook() {
		ArrayList<BookDTO> list = new ArrayList<BookDTO>();
		Connection con = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@192.168.119.119:1521/dink";
			String user = "scott";
			String passwd = "tiger";
			con = DriverManager.getConnection(url, user, passwd);
			con.setAutoCommit(false);
			// 3. SQL 쿼리를 실행합니다.
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM BOOK ORDER BY PUBLICATIONDATE DESC");

			while (rs.next()) {
				BookDTO dto = new BookDTO();
				dto.setBookID(rs.getString("bookID"));
				dto.setTitle(rs.getString("title"));
				dto.setPublicationDate(rs.getString("publicationDate"));
				dto.setIsAvailable(rs.getString("isAvailable"));
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

	// 도서 등록 (insert)
	// MemberDTO 객체의 데이터를 사용하여 MEMBER 테이블에 데이터를 삽입
	public int insertBook(BookDTO dto) {
		int result = 0;
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@192.168.119.119:1521/dink";
			String user = "scott";
			String passwd = "tiger";
			con = DriverManager.getConnection(url, user, passwd);
			con.setAutoCommit(false);
			String sql = "INSERT INTO BOOK(BOOKID, TITLE, PUBLICATIONDATE, ISAVAILABLE) "
					+ "VALUES (SCOTT.bookID_SEQ.NEXTVAL,? ,TO_DATE(?, 'YYYY/MM/DD'), 'Y')";
			PreparedStatement pt = con.prepareStatement(sql);

			// dto 객체의 각 필드 값을 사용하여 각각의 파라미터 값을 설정
			pt.setString(1, dto.getTitle());
			pt.setString(2, dto.getPublicationDate());
			// pt.setString(4, dto.getIsAvailable());

			// pt.executeUpdate(): 쿼리를 실행하여 데이터를 삽입하는 메소드
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

	// 도서 수정
	public int updateBook(BookDTO dto) {
		int result = 0;
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@192.168.119.119:1521/dink";
			String user = "scott";
			String passwd = "tiger";
			con = DriverManager.getConnection(url, user, passwd);
			con.setAutoCommit(false);
			String sql = "UPDATE BOOK SET TITLE = ?, PUBLICATIONDATE = TO_DATE(?, 'YYYY/MM/DD'), ISAVAILABLE = ? WHERE BOOKID = ?";
			PreparedStatement pt = con.prepareStatement(sql);

			// dto 객체의 각 필드 값을 사용하여 각각의 파라미터 값을 설정
			pt.setString(1, dto.getTitle());
			pt.setString(2, dto.getPublicationDate());
			pt.setString(3, dto.getIsAvailable());
			pt.setString(4, dto.getBookID());

			// pt.executeUpdate(): 쿼리를 실행하여 데이터를 수정하는 메소드
			result = pt.executeUpdate();
			System.out.println("수정된 행의 갯수 = " + result);
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

	// 도서 삭제
	public int deleteBook(String BookID) {
		int result = 0;
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@192.168.119.119:1521/dink";
			String user = "scott";
			String passwd = "tiger";
			con = DriverManager.getConnection(url, user, passwd);
			con.setAutoCommit(false);

			// 삭제 취소 저장
			String saveSql = "SELECT * FROM BOOK WHERE BOOKID = ?";
			PreparedStatement ps = con.prepareStatement(saveSql);
			ps.setString(1, BookID);
			ResultSet rs = ps.executeQuery();
			con.commit();

			while (rs.next()) {
				saveDelete = rs.getString("BOOKID") + "," + rs.getString("TITLE") + ","
						+ rs.getString("PUBLICATIONDATE") + "," + rs.getString("ISAVAILABLE");
			}

			// 해당 ID를 가진 도서 삭제
			String sql = "DELETE FROM BOOK WHERE BOOKID = ?";
			PreparedStatement pt = con.prepareStatement(sql);

			// BOOKID를 파라미터로 설정
			pt.setString(1, BookID);

			// pt.executeUpdate(): 쿼리를 실행하여 데이터를 삭제하는 메소드
			result = pt.executeUpdate();
			System.out.println("삭제된 행의 갯수 = " + result);
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

	// 도서 삭제 취소
	public int redoDeleteBook() {

		if (saveDelete == null) {
			System.out.println("삭제 취소할 도서가 없습니다.");
			return 0;
		}

		int result = 0;
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@192.168.119.119:1521/dink";
			String user = "scott";
			String passwd = "tiger";
			con = DriverManager.getConnection(url, user, passwd);
			con.setAutoCommit(false);
			String sql = "INSERT INTO BOOK VALUES(?, ?, TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS'), ?)";

			PreparedStatement pt = con.prepareStatement(sql);
			String[] restoredata = saveDelete.split(",");

			for (int i = 0; i < restoredata.length; i++) {
				pt.setString(i+1, restoredata[i]);
			}

			result = pt.executeUpdate();
			System.out.println("삭제된 행의 갯수 = " + result);
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
		saveDelete = null;
		return result;
	}

}
