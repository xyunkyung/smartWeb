package model.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.DTO.NoticeDTO;

public class NoticeDAO extends DataBaseInfo {

	Integer result;
	
	final String COLUMNS = " NOTICE_NO, NOTICE_SUB, NOTICE_CON, NOTICE_DATE, NOTICE_KIND, NOTICE_FILE, NOTICE_COUNT, EMPLOYEE_ID ";
	
	public void noticeModify(NoticeDTO dto) {
		sql = " UPDATE NOTICE SET NOTICE_SUB = ?, NOTICE_CON = ?, NOTICE_FILE = ? "
				+ " WHERE NOTICE_NO = ? ";
		
		getConnect();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getNoticeSub());
			pstmt.setString(2, dto.getNoticeCon());
			pstmt.setString(3, dto.getNoticeFile());
			pstmt.setString(4, dto.getNoticeNo());
			
			int i = pstmt.executeUpdate();
			System.out.println(i + "개가 수정되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
	}
	
	public NoticeDTO noticeOne(String noticeNo) {
		NoticeDTO dto = null;
		
		sql = " SELECT " + COLUMNS + " , CASE NOTICE_KIND WHEN 'guidance' THEN '안내' "
				+ " WHEN 'check' THEN '점검' END noticeKind " + " FROM NOTICE "
				+ " WHERE NOTICE_NO = ? ";
		
		getConnect();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, noticeNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto = new NoticeDTO();
				dto.setEmployeeId(rs.getString("EMPLOYEE_ID"));
				dto.setNoticeCon(rs.getString("NOTICE_CON"));
				dto.setNoticeCount(rs.getString("NOTICE_COUNT"));
				dto.setNoticeDate(rs.getString("NOTICE_DATE"));
				dto.setNoticeFile(rs.getString("NOTICE_FILE"));
				dto.setNoticeKind(rs.getString("noticeKind"));
				dto.setNoticeNo(rs.getString("NOTICE_NO"));
				dto.setNoticeSub(rs.getString("NOTICE_SUB"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return dto;
	}
	
	public void noticeInsert(NoticeDTO dto) {
		sql = " INSERT INTO NOTICE (" + COLUMNS + ") " + " values(?, ?, ?, ?, ?, ?, ?, ? )";
		
		getConnect();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getNoticeNo());
			pstmt.setString(2, dto.getNoticeSub());
			pstmt.setString(3, dto.getNoticeCon());
			pstmt.setString(4, dto.getNoticeDate());
			pstmt.setString(5, dto.getNoticeKind());
			pstmt.setString(6, dto.getNoticeFile());
			pstmt.setString(7, dto.getNoticeCount());
			pstmt.setString(8, dto.getEmployeeId());
			
			rs = pstmt.executeQuery();
			System.out.println(rs + "개가 저장되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
	}
	
	public int getNoticeNum() {
		sql = " SELECT NVL(MAX(NOTICE_NO), 10000) + 1 FROM NOTICE ";
		
		getConnect();
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			result = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}
	
	public List<NoticeDTO> noticeList() {
		List<NoticeDTO> list = new ArrayList<NoticeDTO>();
		
		sql = " SELECT " + COLUMNS + " FROM NOTICE ";
		
		getConnect();
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				NoticeDTO dto = new NoticeDTO();
				dto.setEmployeeId(rs.getString("EMPLOYEE_ID"));
				dto.setNoticeCon(rs.getString("NOTICE_CON"));
				dto.setNoticeCount(rs.getString("NOTICE_COUNT"));
				dto.setNoticeDate(rs.getString("NOTICE_DATE"));
				dto.setNoticeFile(rs.getString("NOTICE_FILE"));
				dto.setNoticeKind(rs.getString("NOTICE_KIND"));
				dto.setNoticeNo(rs.getString("NOTICE_NO"));
				dto.setNoticeSub(rs.getString("NOTICE_SUB"));
				
				list.add(dto);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}
}
