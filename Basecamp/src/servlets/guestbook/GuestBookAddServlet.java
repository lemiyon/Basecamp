package servlets.guestbook;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * @author kim-bola
 * 게시글 추가 서블릿
 * get 요청 시 형식 가져옴
 * post 요청 시 멤버 추가
 */
@WebServlet("/guestbook/add")
public class GuestBookAddServlet extends HttpServlet {
	
	GuestBookBusienessLogic bl;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{
		
		//회원 등록 페이지 html을 직접 출력한다. 나중에는 jsp로 분리할 것이다.
		//보낼 콘텐츠타입을 설정한다. 자세한 설정은 html스펙 참고 
		response.setContentType("text/html;charset=UTF-8");
		
		//리퀘스트를 해당 jsp에게 넘겨준다.
		RequestDispatcher rd = request.getRequestDispatcher("/guestbook/guestBookAdd.jsp");
		rd.include(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException{
		
		bl = new GuestBookBusienessLogic();
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		//이것은 리퀘스트, 리스폰스를 넘겨주는 방법이다.
		//그러나, 이 말고도, 필요한 정보만 해쉬맵 등으로 저장한 뒤 넘겨주는 방법도 있다.
		try {
			bl.doAdd(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			//리퀘스트를 해당 jsp에게 넘겨준다.
			request.setAttribute("error", e);
			
			RequestDispatcher rd = request.getRequestDispatcher("/guestbook/guestBookError.jsp");
			rd.forward(request, response);
		}
		
			//게시글 리스트로 리다이렉트 시키기
		response.sendRedirect("list");
		
	}
	
}
