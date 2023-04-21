package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dto.MemberDTO;

public class MemberDAO {

	String saveDelete = null;

	// 회원 조회
	public ArrayList<MemberDTO> selectMember() {
		ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();
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
			ResultSet rs = stmt.executeQuery("SELECT * FROM MEMBER");

			while (rs.next()) {
				MemberDTO dto = new MemberDTO();
				dto.setMemberID(rs.getString("memberID"));
				dto.setName(rs.getString("name"));
				dto.setAge(rs.getInt("age"));
				dto.setAddress(rs.getString("address"));
				dto.setPhoneNumber(rs.getString("phoneNumber"));
				dto.setDateOfBirth(rs.getString("dateOfBirth"));
				dto.setDateOfMembership(rs.getString("dateOfMembership"));
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

	// 회원 등록
	// MemberDTO 객체의 데이터를 사용하여 MEMBER 테이블에 데이터를 삽입
	public int insertMember(MemberDTO dto) {
		int result = 0;
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@192.168.119.119:1521/dink";
			String user = "scott";
			String passwd = "tiger";
			con = DriverManager.getConnection(url, user, passwd);
			con.setAutoCommit(false);
			String sql = "INSERT INTO MEMBER(memberID, name, age, address, phoneNumber, dateOfBirth, dateOfMembership) "
					+ "VALUES(SCOTT.memberID_SEQ.nextval,?,?,?,?,TO_DATE(?, 'YYYY/MM/DD'), TRUNC(SYSDATE))";
			PreparedStatement pt = con.prepareStatement(sql);

			// dto 객체의 각 필드 값을 사용하여 각각의 파라미터 값을 설정
			// insert 문
			pt.setString(1, dto.getName());
			pt.setInt(2, dto.getAge());
			pt.setString(3, dto.getAddress());
			pt.setString(4, dto.getPhoneNumber());
			pt.setString(5, dto.getDateOfBirth());

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

	// 회원 수정
	public int updateMember(MemberDTO dto) {
		int result = 0;
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@192.168.119.119:1521/dink";
			String user = "scott";
			String passwd = "tiger";
			con = DriverManager.getConnection(url, user, passwd);
			con.setAutoCommit(false);
			String sql = "UPDATE MEMBER SET name = ?, age = ?, address = ?, phoneNumber = ?, dateOfBirth = TO_DATE(?, 'YYYY/MM/DD') WHERE memberID = ?";
			PreparedStatement pt = con.prepareStatement(sql);

			// dto 객체의 각 필드 값을 사용하여 각각의 파라미터 값을 설정
			pt.setString(1, dto.getName());
			pt.setInt(2, dto.getAge());
			pt.setString(3, dto.getAddress());
			pt.setString(4, dto.getPhoneNumber());
			pt.setString(5, dto.getDateOfBirth());
			pt.setString(6, dto.getMemberID());
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

	// 회원 삭제
	public int deleteMember(String memberID) {
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
			String saveSql = "SELECT * FROM MEMBER WHERE memberID = ?";
			PreparedStatement ps = con.prepareStatement(saveSql);
			ps.setString(1, memberID);
			ResultSet rs = ps.executeQuery();
			con.commit();

			while (rs.next()) {
				saveDelete = rs.getString("MEMBERID") + "," + rs.getString("NAME") + "," + rs.getString("AGE") + ","
						+ rs.getString("ADDRESS") + "," + rs.getString("PHONENUMBER") + "," + rs.getDate("DATEOFBIRTH")
						+ "," + rs.getString("DATEOFMEMBERSHIP");
			}

			// 해당 ID를 가진 멤버 삭제
			String sql = "DELETE FROM MEMBER WHERE memberID = ?";
			PreparedStatement pt = con.prepareStatement(sql);

			pt.setString(1, memberID);

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

	// 회원 삭제 취소
	public int redoDeleteMember() {

		if (saveDelete == null) {
			System.out.println("삭제 취소할 멤버가 없습니다.");
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
			String sql = "INSERT INTO MEMBER VALUES(?, ?, ?, ?, ?, TO_DATE(?, 'YYYY-MM-DD'), TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS'))";
			
			PreparedStatement pt = con.prepareStatement(sql);
			String[] restoredata = saveDelete.split(",");

			for (int i = 0; i < restoredata.length; i++) {
				if (i == 2) {
					pt.setInt(i + 1, Integer.parseInt(restoredata[i]));
				} else {
					pt.setString(i + 1, restoredata[i]);

				}
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
