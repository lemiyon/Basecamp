package servlets.guestbook;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlets.guestbook.dao.GuestBookDAO;
import servlets.guestbook.dao.GuestBookDAOFactory;
import servlets.guestbook.dao.GuestBookDBConnection;
import servlets.guestbook.model.GuestBook;


//DAO들을 이용해 필요한 작업을 하는 
//비즈니스 로직 단계
public class GuestBookBusienessLogic {
	
	//데이터 베이스 커넥션을 생성
	//보통은 처음 연결이 생겼을 때, 세션으로 집어넣고 계속 쓰거나, 파라미터를 계속 넘겨주기도 한다.
	GuestBookDAO dao;
	
	/**
	 * 생성자.
	 * DAOFactory에서 필요한 dao 객체를 생성한다.
	 */
	public GuestBookBusienessLogic() 
	{
		//dao 팩토리에서 dao를 가져온다.
		GuestBookDAOFactory daoFactory = new GuestBookDAOFactory();
		dao = daoFactory.guestBookDAO();
	}
	
	public ArrayList<GuestBook> doSelect(HttpServletRequest request, HttpServletResponse response) throws SQLException 
	{
		ArrayList<GuestBook> guestBooks = new ArrayList<GuestBook>();
		
	
		//리퀘스트를 직접 넘겨주는 방식 대신, 해쉬맵으로 필요한 정보만 넘겨주는 방법을 써보았다.
		HashMap<String, Object> value = new HashMap<String, Object>();
		guestBooks = dao.selectGuestBook(value);

		return guestBooks;
	}
	
	//여러 건을 하는 경우는, 새로운 메소드를 만들어서 for문으로 dao의 메소드를 호출해주면 된다. 
	public void doAdd(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		//서버에서 이메일 체크하는 부분
		//email이 적당한 형태인지 확인한다.
		String email = request.getParameter("email");
		if (email.matches("[A-Za-z0-9_\\.\\-]+@[A-Za-z0-9\\-]+\\.[A-Za-z0-9\\-]+")) {
		
		//리퀘스트를 직접 넘겨주는 방식 대신, 해쉬맵으로 필요한 정보만 넘겨주는 방법을 써보았다.
		HashMap<String, Object> value = new HashMap<String, Object>();
		value.put("email", request.getParameter("email"));
		value.put("password", request.getParameter("password"));
		value.put("content", request.getParameter("content"));
		
		dao.insertGuestBook(value);
		}
		else 
		{
			throw new NotACorrectEmailException("이메일이 적절한 형식이 아닙니다.");	
		}
	}
	
	public GuestBook doGetAGuestBook(HttpServletRequest request, HttpServletResponse response) 
	{
		//리퀘스트를 직접 넘겨주는 방식 대신, 해쉬맵으로 필요한 정보만 넘겨주는 방법을 써보았다.
				HashMap<String, Object> value = new HashMap<String, Object>();
				value.put("id", request.getParameter("gid"));
			
				
		GuestBook g = dao.getAGuestBook(value);
		
		return g;
	}
	
	//삭제 메소드
	//사실은 나중에는 성공 여부를 반환하여 제대로 된 처리를 ㅎ ㅐ줘야 하지만 당장 시간이 없으니까.
	public void doDelete(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException 
	{
		
		//리퀘스트를 직접 넘겨주는 방식 대신, 해쉬맵으로 필요한 정보만 넘겨주는 방법을 써보았다.
		HashMap<String, Object> value = new HashMap<String, Object>();
		value.put("id", request.getParameter("gid"));
		
		dao.deleteGuestBook(value);
	}
	
	//업데이트 메소드
	public int doUpdate(HttpServletRequest request, HttpServletResponse response) throws SQLException 
	{
		//리퀘스트를 직접 넘겨주는 방식 대신, 해쉬맵으로 필요한 정보만 넘겨주는 방법을 써보았다.
				HashMap<String, Object> value = new HashMap<String, Object>();
				value.put("id", request.getParameter("id"));
				value.put("contents", request.getParameter("contents"));
				
				return dao.updateGuestBook(value);
	}
	
	
	
	
}

//이메일 형식이 맞지 않으면 던지는 Exception
class NotACorrectEmailException extends Exception {
	private final int ERR_CODE;
	public NotACorrectEmailException(String msg, int errcode) {
		// TODO Auto-generated constructor stub
		super(msg);
		ERR_CODE = errcode;
	}
	NotACorrectEmailException(String msg){// 생성자
		this(msg, 100);// ERR_CODE를 100(기본값)으로 초기화한다.
	}
	
}
