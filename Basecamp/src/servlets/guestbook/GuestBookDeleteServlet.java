/**
 * 
 */
package servlets.guestbook;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author gim-bola
 * 게시글을 지우는 역할을 한다. 
 *
 */
@WebServlet(
		urlPatterns= {"/guestbook/delete"}
//initParams = {
//		@WebInitParam(name="driver", value="com.mysql.jdbc.Driver",description="드라이버"),
//		@WebInitParam(name="url", value="jdbc:mysql://localhost/basecamp?useUnicode=true&characterEncoding=utf8",description="url"),
//		@WebInitParam(name="username", value="lemiyon"),
//		@WebInitParam(name="password", value="test")
//		}
)
public class GuestBookDeleteServlet extends HttpServlet {
	
	GuestBookBusienessLogic bl;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Connection conn = null;
		PreparedStatement stmt = null;
		bl = new GuestBookBusienessLogic();
		
		//드라이버를 로딩 
		try {

			
			bl.doDelete(request, response);
			response.sendRedirect("list");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//문제가 생기면 리퀘스트에 에러 내용을 넘겨 포워딩(다신 돌아오지 않음) 한다.
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("guestBookError.jsp");
			rd.forward(request, response);
			}
		
		finally {
			try { stmt.close(); } catch(Exception e) { }
			try { conn.close(); } catch(Exception e) { }
			
		}
	}
}
