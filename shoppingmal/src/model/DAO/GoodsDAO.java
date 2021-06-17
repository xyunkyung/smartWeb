package model.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.DTO.GoodsDTO;

public class GoodsDAO extends DataBaseInfo {

	final String COLUMNS = "PROD_NUM, PROD_NAME, PROD_PRICE, PROD_IMAGE, PROD_DETAIL, RPOD_CAPACITY, PROD_SUPPLYER, PROD_DEL_FEE, RECOMMEND, EMPLOYEE_ID, CTGR ";
	
	public void goodsUpdate(GoodsDTO dto) {
		sql = " UPDATE PRODUCTS SET PROD_NAME = ?, PROD_PRICE = ?, PROD_DETAIL = ?, RPOD_CAPACITY = ?, PROD_SUPPLYER = ?, PROD_DEL_FEE = ?, RECOMMEND = ? "
			+ " WHERE PROD_NUM = ? ";
		
		getConnect();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(8, dto.getProdNum());
			pstmt.setString(1, dto.getProdName());
			pstmt.setInt(2, dto.getProdPrice());
			pstmt.setString(3, dto.getProdDetail());
			pstmt.setString(4, dto.getProdCapacity());
			pstmt.setString(5, dto.getProdSupplyer());
			pstmt.setString(6, dto.getProdDelFee());
			pstmt.setString(7, dto.getRecommend());
			
			int i = pstmt.executeUpdate();
			System.out.println(i + "개가 수정되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}
	
	public GoodsDTO GoodsOne(String prodNum) {
		GoodsDTO dto = null;
		
		sql = " SELECT " + COLUMNS + " , CASE CTGR WHEN 'wear' THEN '의류' " 
				+ " WHEN 'cosmetic' THEN '화장품' " 
				+ " WHEN 'food' THEN '음식' " 
				+ " WHEN 'car' THEN '자동차 용품' END CTGR1 " + " FROM PRODUCTS "
				+ " WHERE PROD_NUM = ?";
		
		getConnect();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, prodNum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto = new GoodsDTO();
				dto.setCtgr(rs.getString("CTGR1"));
				dto.setProdNum(rs.getString("PROD_NUM"));
				dto.setEmployeeId(rs.getString("EMPLOYEE_ID"));
				dto.setProdCapacity(rs.getString("RPOD_CAPACITY"));
				dto.setProdDelFee(rs.getString("PROD_DEL_FEE"));
				dto.setProdDetail(rs.getString("PROD_DETAIL"));
				dto.setProdImage(rs.getString("PROD_IMAGE"));
				dto.setProdName(rs.getString("PROD_NAME"));
				dto.setProdPrice(rs.getInt("PROD_PRICE"));
				dto.setProdSupplyer(rs.getString("PROD_SUPPLYER"));
				dto.setRecommend(rs.getString("RECOMMEND"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return dto;
	}
	
	public List<GoodsDTO> goodsList() {
		List<GoodsDTO> list = new ArrayList<GoodsDTO>();
		
		sql = " SELECT " + COLUMNS + " , CASE CTGR WHEN 'wear' THEN '의류' " 
				+ " WHEN 'cosmetic' THEN '화장품' " 
				+ " WHEN 'food' THEN '음식' " 
				+ " WHEN 'car' THEN '자동차 용품' END CTGR1 " + " FROM PRODUCTS ";
		
		getConnect();
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				GoodsDTO dto = new GoodsDTO();
				dto.setCtgr(rs.getString("CTGR1"));
				dto.setProdNum(rs.getString("PROD_NUM"));
				dto.setEmployeeId(rs.getString("EMPLOYEE_ID"));
				dto.setProdCapacity(rs.getString("RPOD_CAPACITY"));
				dto.setProdDelFee(rs.getString("PROD_DEL_FEE"));
				dto.setProdDetail(rs.getString("PROD_DETAIL"));
				dto.setProdImage(rs.getString("PROD_IMAGE"));
				dto.setProdName(rs.getString("PROD_NAME"));
				dto.setProdPrice(rs.getInt("PROD_PRICE"));
				dto.setProdSupplyer(rs.getString("PROD_SUPPLYER"));
				dto.setRecommend(rs.getString("RECOMMEND"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return list;
	}
	
	public void prodInsert(GoodsDTO dto) {
		sql = " INSERT INTO PRODUCTS ( " + COLUMNS + ") " + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
		
		getConnect();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getProdNum());
			pstmt.setString(2, dto.getProdName());
			pstmt.setInt(3, dto.getProdPrice());
			pstmt.setString(4, dto.getProdImage());
			pstmt.setString(5, dto.getProdDetail());
			pstmt.setString(6, dto.getProdCapacity());
			pstmt.setString(7, dto.getProdSupplyer());
			pstmt.setString(8, dto.getProdDelFee());
			pstmt.setString(9, dto.getRecommend());
			pstmt.setString(10, dto.getEmployeeId());
			pstmt.setString(11, dto.getCtgr());
			
			int i = pstmt.executeUpdate();
			System.out.println(i + "개가 입력되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}
	
	public String goodsNum() {
		String prodNum = null;
		sql = " SELECT PROD_SEQ.NEXTVAL FROM DUAL ";
		
		getConnect();
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			prodNum = rs.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return prodNum;
	}
}
