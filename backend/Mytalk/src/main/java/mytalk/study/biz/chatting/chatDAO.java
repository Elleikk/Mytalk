package mytalk.study.biz.chatting;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Repository;

import mytalk.study.biz.JDBCUtill;
import mytalk.study.biz.user.userVO;

@Repository("chatDAO")
public class chatDAO {
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	
	private String selectChat = "SELECT * FROM mytalk.chatting;";
	private String insertChat = "INSERT INTO mytalk.chatting VALUES (? ,?, NOW(), ?, ?);";
	
	
	public int InsertChatting(chatVO vo) {
		System.out.println("insetchat 실행");
		
		int result = 0;
		
		int roomNum = 1;
		String chattingdata = vo.getChat();
//		String date = vo.getDate();
		String type = vo.getType();
		String user = vo.getUser().getUser();
		
		conn = JDBCUtill.getConn();
		
		try {
			stmt = conn.prepareStatement(insertChat);
			stmt.setInt(1, roomNum);
			stmt.setString(2, chattingdata);
//			stmt.setString(3, date);
			stmt.setString(3, type);
			stmt.setString(4, user);
			
			result = stmt.executeUpdate();
			
		} 
		catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			// TODO: handle finally clause
			JDBCUtill.close(stmt, conn);
		}
		return result;
	}
	
	
}
