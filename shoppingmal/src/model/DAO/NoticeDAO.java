package model.DAO;

import java.util.ArrayList;
import java.util.List;

import model.DTO.NoticeDTO;

public class NoticeDAO extends DataBaseInfo {

	final String COLUMNS = " NOTICE_NO, NOTICE_SUB, NOTICE_CON, NOTICE_DATE, NOTICE_KIND, NOTICE_FILE, NOTICE_COUNT, EMPLOYEE_ID ";
	
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
