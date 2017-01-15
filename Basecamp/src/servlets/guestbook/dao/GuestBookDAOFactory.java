package servlets.guestbook.dao;

import java.sql.Connection;

/*
 * DAO 객체를 만들어주는 DAO Factory
 * DAO와 DAO가 필요로 하는 DB Connection 간의 관계를 맺어주고, 완전 세팅된 dao를 돌려준다.
 * 말 그대로 dao 공장 
 * Ioc(제어의 역전)
 */

public class GuestBookDAOFactory 
{

	public GuestBookDAO guestBookDAO() 
	{
		GuestBookDBConnection gbConn;
		gbConn = new GuestBookDBConnection();
		Connection conn = gbConn.getConnection();
		
		GuestBookDAO dao = new GuestBookDAO(conn);
		
		return dao;
	}
	
}
