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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlets.guestbook.model.GuestBook;

/**
 * @author gim-bola
 * 게시글 업데이트를 처리하는 서블릿이다. 
 * get : 게시글의 상세 내용을 볼 수 있다.
 * post : 게시글을 수정하고,  그 수정된 내용을 디비에 저장한다.
 *
 */
@SuppressWarnings("serial")
public class GuestBookUpdateServlet extends HttpServlet {
	
	
	
	//get은 상세정보를 확인케 한다.
	@Override 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		GuestBookBusienessLogic gblogic = new GuestBookBusienessLogic();
		
			GuestBook g = gblogic.doGetAGuestBook(request, response);
		
			request.setAttribute("guestbook", g);
			response.setContentType("text/html; charset=UTF-8"); 
			
			RequestDispatcher rd = request.getRequestDispatcher("/guestbook/guestBookUpdate.jsp");
			rd.include(request, response);
	
		
	}
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		
		PreparedStatement stmt = null;
		Connection conn = null;
		
		try {
			request.setCharacterEncoding("UTF-8");
			Class.forName(this.getInitParameter("driver"));
			conn = DriverManager.getConnection(this.getInitParameter("url") + "?useUnicode=true&characterEncoding=utf8",
					this.getInitParameter("username"),
					this.getInitParameter("password"));
			
			stmt = conn.prepareStatement("UPDATE GUESTBOOK SET GCONTENTS=?, MOD_DATE=now()"
					+ " WHERE GID=?");
			stmt.setString(1, request.getParameter("contents"));
			stmt.setInt(2, Integer.parseInt(request.getParameter("id")));
			
			//executeUpdate는 주로 Update
			stmt.executeUpdate();
			
			//다시 리스트로 리다이렉트한다.
			response.sendRedirect("list");
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		finally {
			try{ if(stmt != null) {stmt.close();} } catch(Exception e) {}
			try{ if(conn != null) {conn.close();} } catch(Exception e) {}
			
		}
	}
}
