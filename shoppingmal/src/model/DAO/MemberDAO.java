package model.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.DTO.MemberDTO;

public class MemberDAO {

	final String COLUMNS = " MEM_ID, MEM_PW, POST_NUMBER, MAM_ADDRESS, DETAIL_ADD, MEM_NAME, MEM_PHONE, MEM_BIRTH, MEM_GENDER, MEM_ACCOUNT, MEM_EMAIL, MEM_EMAIL_CK ";
	static String jdbcDriver;
	static String jdbcUrl;
	static Connection conn;
	String sql;
	PreparedStatement pstmt;
	Integer result;
	ResultSet rs;
	
	static {
		jdbcDriver = "oracle.jdbc.driver.OracleDriver";
		jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
	}
	
	public void idFind(MemberDTO dto) {
		sql = " SELECT MEM_ID, MEM_NAME FROM MEMBER "
				+ " WHERE MEM_NAME = ? AND MEM_PHONE = ? AND MEM_EMAIL = ? ";
		
		getConnect();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getMemName());
			pstmt.setString(2, dto.getMemPhone());
			pstmt.setString(3, dto.getMemEmail());
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto.setMemId(rs.getString("MEM_ID"));
				dto.setMemName(rs.getString("MEM_NAME"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
	}
	
	public static void getConnect() {
		try {
			Class.forName(jdbcDriver);
			conn = DriverManager.getConnection(jdbcUrl, "smart", "oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void close() {
		if(rs != null)	try {rs.close();} 
						catch (SQLException e) {}
		if(pstmt != null)	try {pstmt.close();} 
						catch (SQLException e) {}
		if(conn != null)	try {conn.close();} 
						catch (SQLException e) {}
	}
	
	public void pwChange(String memId, String memPw) {
		sql = " UPDATE MEMBER SET MEM_PW = ? WHERE MEM_ID = ? ";
		
		getConnect();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memPw);
			pstmt.setString(2, memId);
			
			int i = pstmt.executeUpdate();
			System.out.println(i + "개가 수정되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}
	
	public void memDel(String memId) {
		sql = "DELETE FROM MEMBER WHERE MEM_ID = ?";
		
		getConnect();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			int i = pstmt.executeUpdate();
			System.out.println(i + "개가 삭제되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
	}
	
	public void memUpdate(MemberDTO dto) {
		sql = "UPDATE MEMBER" + " SET POST_NUMBER = ?, MAM_ADDRESS = ?, DETAIL_ADD = ?, MEM_EMAIL = ?, MEM_EMAIL_CK = ?, MEM_ACCOUNT = ?, MEM_PHONE = ?, MEM_BIRTH = ? "
				+ " WHERE MEM_ID = ?";
		
		getConnect();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getPostNumber());
			pstmt.setString(2, dto.getMemAddress());
			pstmt.setString(3, dto.getDetailAdd());
			pstmt.setString(4, dto.getMemEmail());
			pstmt.setString(5, dto.getMemEmailCk());
			pstmt.setString(6, dto.getMemAccount());
			pstmt.setString(7, dto.getMemPhone());
			long birth = dto.getMemBirth().getTime();
			pstmt.setDate(8, new Date(birth));
			pstmt.setString(9, dto.getMemId());
			int i = pstmt.executeUpdate();
			System.out.println(i + "개가 변경되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}
	
	public MemberDTO memDetail(String memId) {
		MemberDTO dto = new MemberDTO();
		
		sql = "SELECT " + COLUMNS + " FROM MEMBER WHERE MEM_ID = ?";
		
		getConnect();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setDetailAdd(rs.getString("DETAIL_ADD"));
				dto.setMemAccount(rs.getString("MEM_ACCOUNT"));
				dto.setMemAddress(rs.getString("MAM_ADDRESS"));
				dto.setMemBirth(rs.getDate("MEM_BIRTH"));
				dto.setMemEmail(rs.getString("MEM_EMAIL"));
				dto.setMemEmailCk(rs.getString("MEM_EMAIL_CK"));
				dto.setMemGender(rs.getString("MEM_GENDER"));
				dto.setMemId(rs.getString("MEM_ID"));
				dto.setMemName(rs.getString("MEM_NAME"));
				dto.setMemPhone(rs.getString("MEM_PHONE"));
				dto.setPostNumber(rs.getString("POST_NUMBER"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return dto;
	}
	
	public List<MemberDTO> memList() {
		List<MemberDTO> list = new ArrayList<MemberDTO>();
		sql = "SELECT " + COLUMNS + " FROM MEMBER";
		
		getConnect();
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberDTO dto = new MemberDTO();
				dto.setDetailAdd(rs.getString("DETAIL_ADD"));
				dto.setMemAccount(rs.getString("MEM_ACCOUNT"));
				dto.setMemAddress(rs.getString("MAM_ADDRESS"));
				dto.setMemBirth(rs.getDate("MEM_BIRTH"));
				dto.setMemEmail(rs.getString("MEM_EMAIL"));
				dto.setMemEmailCk(rs.getString("MEM_EMAIL_CK"));
				dto.setMemGender(rs.getString("MEM_GENDER"));
				dto.setMemId(rs.getString("MEM_ID"));
				dto.setMemName(rs.getString("MEM_NAME"));
				dto.setMemPhone(rs.getString("MEM_PHONE"));
				dto.setPostNumber(rs.getString("POST_NUMBER"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return list;
	}
	
	public void memInsert(MemberDTO dto) {
		sql = "INSERT INTO MEMBER ( " + COLUMNS + ") " + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		getConnect();
		
		try {
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, dto.getMemId());
		pstmt.setString(2, dto.getMemPw());
		pstmt.setString(3, dto.getPostNumber());
		pstmt.setString(4, dto.getMemAddress());
		pstmt.setString(5, dto.getDetailAdd());
		pstmt.setString(6, dto.getMemName());
		pstmt.setString(7, dto.getMemPhone());
		long birth = dto.getMemBirth().getTime();
		pstmt.setDate(8, new Date(birth));
		pstmt.setString(9, dto.getMemGender());
		pstmt.setString(10, dto.getMemAccount());
		pstmt.setString(11, dto.getMemEmail());
		pstmt.setString(12, dto.getMemEmailCk());
		
		int i = pstmt.executeUpdate();
		System.out.println(i + "개가 저장 되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}
}
