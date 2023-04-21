package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionTest {

	public static void main(String[] args) {
		// Oracle JDBC 드라이버를 로드합니다.
		Connection conn = null;

		// 데이터베이스에 연결합니다.
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@192.168.119.119:1521/dink";
			String user = "scott";
			String passwd = "tiger";
			conn = DriverManager.getConnection(url, user, passwd);
			System.out.println(conn);

			// 3. SQL 쿼리를 실행합니다.
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM MEMBER");

			// 4. 쿼리 결과를 처리합니다.
			while (rs.next()) {
				System.out.println(rs.getString("MEMBERID") + " " + rs.getString("NAME") + rs.getString("AGE") + " " + rs.getString("ADDRESS") + " " + rs.getString("PHONENUMBER") + " " + rs.getString("DATEOFBIRTH") + " " + rs.getString("DATEOFMEMBERSHIP"));
			}

		} catch (ClassNotFoundException e) {
			// 드라이버 로드 중 예외가 발생한 경우 처리합니다.
			e.printStackTrace();
		} catch (SQLException e) {
			// 데이터베이스 연결 및 쿼리 실행 중 예외가 발생한 경우 처리합니다.
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// 연결 닫기 중 예외가 발생한 경우 처리합니다.
				e.printStackTrace();
			}
		}

	}
	
	// 예외 상황에 대비하여 리소스를 닫는 로직이 포함되어 있어, 메모리 누수와 같은 문제를 방지
	public static void close(Connection con) {
		try {
			if(con != null && !con.isClosed())
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Statement stmt) {
		try {
			if(stmt != null && !stmt.isClosed())
				stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rset) {
		try {
			if(rset != null && !rset.isClosed())
				rset.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void commit(Connection con) {
		try {
			if(con != null & !con.isClosed())
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void rollback(Connection con) {
		try {
			if(con != null & !con.isClosed())
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
