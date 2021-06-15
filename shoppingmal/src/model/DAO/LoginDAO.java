package model.DAO;

import java.sql.SQLException;

import model.DTO.AuthInfo;

public class LoginDAO extends DataBaseInfo {
	public AuthInfo login(String userId) {
		AuthInfo authInfo = null;
		sql = " SELECT MEM_ID USER_ID, MEM_PW USER_PW, 1 GRADE FROM MEMBER WHERE MEM_ID = ? "
				+ " UNION SELECT EMP_USERID, EMP_PW, EMPLOYEE_ID FROM EMPLOYEES WHERE EMP_USERID = ? ";

		getConnect();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, userId);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				authInfo = new AuthInfo();
				authInfo.setGrade(rs.getString("GRADE"));;
				authInfo.setUserId(rs.getString("USER_ID"));
				authInfo.setUserPw(rs.getString("USER_PW"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return authInfo;
	}
}
