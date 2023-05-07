package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.ConnUtils;
import vo.User;

public class UserDao {
	
	public void updateUser(User user) {
		String sql = "update sample_users "
				+ " set "
				+ " 	User_password = ?, "
				+ " 	User_point = ? "
				+ " where user_no = ? ";
		
		try {
			Connection con = ConnUtils.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getPassword());
			pstmt.setInt(2, user.getPoint());
			pstmt.setInt(3, user.getNo());
			
			pstmt.executeUpdate();
			
			pstmt.close();
			con.close();
			
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}
	
	public User getUserByNo(int userNo) {
		String sql = "select * "
				+ " from sample_users "
				+ " where user_no = ? ";
		
		try {
			User user = null;
			
			Connection con = ConnUtils.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, userNo);
			
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setNo(rs.getInt("user_no"));
				user.setId(rs.getString("user_id"));
				user.setPassword(rs.getString("user_password"));
				user.setName(rs.getString("user_name"));
				user.setPoint(rs.getInt("user_point"));;
				user.setCeateDate(rs.getDate("user_create_date"));
			}
			
			rs.close();
			pstmt.close();
			con.close();
			
			return user;
			
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}
	
	/*
	 * 사용자정보 조회하기 - SELECT 작업
	 * 
	 * 반환타입 : User
	 * 메소드명 : getUserById
	 * 매개변수 : String id
	 * 아이디를 전달받아서 SAMPLE_USERS 테이블에서 사용자정보를 조회한다.
	 */
	public User getUserById(String userId) {
		String sql = "select * "
				+ " from sample_users "
				+ " where user_id = ? ";
				
		try {
			User user = null;
			
			Connection con = ConnUtils.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userId);
			
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setNo(rs.getInt("user_no"));
				user.setId(rs.getString("user_id"));
				user.setPassword(rs.getString("user_password"));
				user.setName(rs.getString("user_name"));
				user.setPoint(rs.getInt("user_point"));;
				user.setCeateDate(rs.getDate("user_create_date"));
			}
			
			rs.close();
			pstmt.close();
			con.close();
			
			return user;
			
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}

	/*
	 * 반환타입 : void
	 * 메소드명 : insertUser
	 * 매개변수 : User user
	 * 아이디, 비밀번호, 이름이 포함된 User객체를 전달받아서 SAMPLE_USERS 테이블에 저장한다.
	 */
	public void insertUser(User user) {
		// 1. insert 구문 SQL을 작성한다
		String sql = "insert into sample_users"
				+ "(user_no, user_id, user_password, user_name) "
				+ "values "
				+ "(sample_users_seq.nextval, ?, ?, ?)";
		try {
			// 2. Connection을 획득한다
			Connection con = ConnUtils.getConnection();
			// 3. PreparedStatement를 획득한다
			PreparedStatement pstmt = con.prepareStatement(sql);
			// 4. ?에 값을 바인딩한다
			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getName());
			
			// 5. SQL을 DBMS로 전송해서 실행시킨다
			pstmt.executeUpdate();
			
			// 6. 자원을 반납한다
			pstmt.close();
			con.close();

		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
		
	}
}
