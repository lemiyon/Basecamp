package servlets.guestbook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import servlets.guestbook.model.GuestBook;

//�븘�슂�븳 SQL�뱾�쓽 �젙蹂대�� �떞怨� �엳�떎.
//�뙆�씪硫뷀꽣濡� �뵒鍮� 而ㅻ꽖�뀡, sql �떎�뻾�뿉 �븘�슂�븳 紐⑤뱺 寃껋쓣 �꽆寃⑤컺�뒗�떎.
//由ы꽩 媛믪쑝濡� sql�쓽 �떎�뻾 寃곌낵 
public class GuestBookDAO {

	Connection conn;
	//DAO가 생성될 때, DB 커넥션을 받아오도록 바꾼다.
	public GuestBookDAO(Connection conn) {
		this.conn = conn;
	}
	
	
//	C,U,D �뒗 蹂댄넻 諛섑솚 媛믪쑝濡� �젙�닔媛� 諛섑솚�맂�떎(�꽦怨�, �떎�뙣)
	public ArrayList<GuestBook> selectGuestBook(HashMap<String, Object> value) throws SQLException {
		
		ArrayList<GuestBook> guestBooks = new ArrayList<GuestBook>();	
		Statement stmt =  null;
		ResultSet rs = null;
		
		try {
			stmt = conn.createStatement();
			

			rs = stmt.executeQuery(
					"SELECT * "
					+ "FROM GUESTBOOK "
					+ "ORDER BY MOD_DATE DESC"
					);

			while(rs.next()) {
				
				guestBooks.add(new GuestBook()
						.setG_id(rs.getInt("GID"))
						.setG_mail(rs.getString("GEMAIL"))
						.setG_contents(rs.getString("GCONTENTS"))
						.setCre_date(rs.getDate("CRE_DATE"))
						.setMod_date(rs.getDate("MOD_DATE")));

			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
				throw e;
		}
		finally {
			try {if(rs != null) rs.close(); } catch(Exception e) {}
			try {if(stmt != null) stmt.close(); } catch(Exception e) {}
			try {if(conn != null) conn.close(); } catch(Exception e) {}
		}
		
		
		
			
		return guestBooks;
	}


	public int insertGuestBook(HashMap<String, Object> value) {
		PreparedStatement stmt =  null;


		try {

			stmt = conn.prepareStatement(
					"INSERT INTO GUESTBOOK(GEMAIL, GPWD, GCONTENTS, CRE_DATE, MOD_DATE)"
							+ "VALUES(?,?,?, NOW(), NOW())"
					);

			stmt.setString(1, (String)value.get("email"));
			stmt.setString(2, (String)value.get("password"));
			stmt.setString(3, (String)value.get("content"));
			stmt.executeUpdate();

		}
		catch (Exception e) {
			System.out.println(e);
		}
		finally {
			try {if(stmt != null) stmt.close(); } catch(Exception e) {}
			try {if(conn != null) conn.close(); } catch(Exception e) {}
		}

		return 0;
	}

	///�긽�꽭蹂닿린 �솕硫� get /update �뿉�꽌 �긽�꽭 蹂닿린瑜� �쐞�븳 �젙蹂대�� 媛��졇�삩�떎. 
	public GuestBook getAGuestBook(HashMap<String, Object> value) {
		Statement stmt =  null;
		ResultSet rs = null;
		GuestBook g = null;
		
		try {
		
		stmt = conn.createStatement();
		rs = stmt.executeQuery(
				"SELECT GID, GEMAIL,GCONTENTS, CRE_DATE, MOD_DATE, GPWD FROM GUESTBOOK "
				+ "WHERE GID=" + value.get("id")
				);
		
		if(rs.next()) {
			g = new GuestBook().setG_id(rs.getInt("GID"))
					.setG_mail(rs.getString("GEMAIL"))
					.setG_contents(rs.getString("GCONTENTS"))
					.setCre_date(rs.getDate("CRE_DATE"))
					.setMod_date(rs.getDate("MOD_DATE"))
					.setG_pwd(rs.getString("GPWD"));
		}
		
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		finally {
		try {if(rs != null) rs.close(); } catch(Exception e) {}
		try {if(stmt != null) stmt.close(); } catch(Exception e) {}
		try {if(conn != null) conn.close(); } catch(Exception e) {}
		}
		
		return g;
	}
	
	
	//�긽�꽭 蹂닿린 �솕硫댁뿉�꽌 post /update濡� �뾽�뜲�씠�듃 �븯�뒗�뜲 �벐�씤�떎.
	public int updateGuestBook(HashMap<String, Object> value) throws SQLException {

		PreparedStatement stmt =  null;
		
		stmt = conn.prepareStatement(
				"UPDATE GUESTBOOK "
				+ "SET GCONTENTS=?"
				+ " WHERE GID=?"
				);
		
		stmt.setString(1, (String)value.get("contents"));
		stmt.setString(2, (String)value.get("id"));
		stmt.executeUpdate();


		return 0;
	}
	
	

	public int deleteGuestBook(HashMap<String, Object> value) throws SQLException {
		PreparedStatement stmt =  null;
		
		stmt = conn.prepareStatement(
				"DELETE FROM GUESTBOOK"
				+ " WHERE GID=?"
				);
		
		stmt.setString(1, (String)value.get("id"));
		
		stmt.executeUpdate();
		


		return 0;
	}

}
