package mytalk.study.biz.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Repository;

import mytalk.study.biz.JDBCUtill;

@Repository("userDAO")
public class userDAO {
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	
	private String selectSQL = "SELECT * FROM mytalk.mytalk"
								+ " WHERE user_id=? AND user_pwd=?;";
	
	private String insertSQL = "INSERT INTO mytalk.mytalk"
								+ "	VALUES (? ,?);";
	
	private String selectIDSQL = "SELECT * FROM mytalk.mytalk"
								+ " WHERE user_id=?;";
	
	public userVO selectUser(userVO vo) {
		System.out.println("DB연동 확인");
		
		conn = JDBCUtill.getConn();
		
		userVO user = null;
		
		try {
			stmt = conn.prepareStatement(selectSQL);
			stmt.setString(1, vo.getUser());
			stmt.setString(2, vo.getPwd());
			
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				user = new userVO().setUser(rs.getString("user_id"))
									.setPwd(rs.getString("user_pwd"));
		
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			JDBCUtill.close(rs, stmt, conn);
		}
		
		return user;
	}
	
	
	public int insertUser(userVO vo) {
		System.out.println("insertUser 호출");
		
		int row = 0;
		
		conn = JDBCUtill.getConn();
		try {
			stmt = conn.prepareStatement(insertSQL);
			stmt.setString(1, vo.getUser());
			stmt.setString(2, vo.getPwd());
			row = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			JDBCUtill.close(stmt, conn);
		}
		
		return row;
	}
	
	
	public int selectID(userVO vo) {
		System.out.println("selectID 호출");
		
		int result = 0;
		conn = JDBCUtill.getConn();
		try {
			stmt = conn.prepareStatement(selectIDSQL);
			stmt.setString(1, vo.getUser());
			
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				result = 1;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			JDBCUtill.close(stmt, conn);
		}
		return result;
	}
}
