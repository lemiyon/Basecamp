package servlets.guestbook.dao;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;


//데이터커넥션을 생성하고 리턴해주는 역할
public class GuestBookDBConnection {
	
	Connection conn = null;
	
	
	public GuestBookDBConnection () {
		try {
			
			//어쩔 수 없이 절대 경로 등을 사용해야 하는 경우도 있기 때문에, 보통은 하드 코딩한다.
			//서블릿 컨텍스트 같은 걸 쓰고 싶지만, 이는 어쩔 수 없다. 얘는 서블릿이 아니라 POJO니까
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost/basecamp?useUnicode=true&characterEncoding=utf8" 
					,"lemiyon"
					, "test");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("데이터베이스 커넥션에 에러가 발생했습니다.");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}
	
	
	public Connection getConnection() {
		return conn;
	}
}
