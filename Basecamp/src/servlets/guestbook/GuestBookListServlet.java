  package servlets.guestbook;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlets.guestbook.model.GuestBook;

/**
 * Servlet implementation class GeustBookListServlet
 */
@WebServlet("/guestbook/list")
public class GuestBookListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	GuestBookBusienessLogic bl;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GuestBookListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		bl = new GuestBookBusienessLogic();
		
		//한글을 출력할 수 있도록 한다.
		response.setContentType("text/html;charset=UTF-8");
		//방명록 게시글 리스트를 불러온다. 

		try{
	
			  //DB에서 가져온 방명록 게시글의 리스트
		    ArrayList<GuestBook> guestBooks = bl.doSelect(request, response);
	
			//request에 게시글 목록을 보관한다. 다음에 jsp가 이를 넘겨받아 출력할 것이다.
			request.setAttribute("guestBooks", guestBooks);
			
			//jsp에게 출력을 넘긴다.
			RequestDispatcher rd = request.getRequestDispatcher("/guestbook/guestBookList.jsp");
			rd.include(request, response);
			
			
		}
		catch (Exception e) {
			//System.out.println(e);
			
			//문제가 생기면 리퀘스트에 에러 내용을 넘겨 포워딩(다신 돌아오지 않음) 한다.
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("guestBookError.jsp");
			rd.forward(request, response);
			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
