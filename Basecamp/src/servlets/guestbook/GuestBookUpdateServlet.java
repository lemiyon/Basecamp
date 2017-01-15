/**
 * 
 */
package servlets.guestbook;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			GuestBookBusienessLogic gblogic = new GuestBookBusienessLogic();
			request.setCharacterEncoding("UTF-8");
			gblogic.doUpdate(request, response);
			response.setContentType("text/html; charset=UTF-8"); 
		
			response.sendRedirect("list");
			
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			//리퀘스트를 해당 jsp에게 넘겨준다.
			request.setAttribute("error", e);
			
			RequestDispatcher rd = request.getRequestDispatcher("/guestbook/guestBookError.jsp");
			rd.forward(request, response);
		}

	}
}
