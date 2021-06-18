package model.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.DTO.CartDTO;
import model.DTO.GoodsCartDTO;
import model.DTO.GoodsDTO;

public class GoodsDAO extends DataBaseInfo {

	final String COLUMNS = "PROD_NUM, PROD_NAME, PROD_PRICE, PROD_IMAGE, PROD_DETAIL, RPOD_CAPACITY, PROD_SUPPLYER, PROD_DEL_FEE, RECOMMEND, EMPLOYEE_ID, CTGR ";
	
	public List cartList(String memId) {
		List list = new ArrayList();
		
		sql = " SELECT p.PROD_NUM, PROD_SUPPLYER, PROD_DEL_FEE, PROD_IMAGE, PROD_NAME, PROD_PRICE, CART_PRICE, CART_QTY "
				+ " FROM PRODUCTS p, CART c WHERE p.PROD_NUM = c.PROD_NUM AND c.MEM_ID = ? ";
		
		getConnect();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				GoodsCartDTO dto = new GoodsCartDTO();
				dto.setCartDTO(new CartDTO());
				dto.setGoodsDTO(new GoodsDTO());
				dto.getCartDTO().setCartPrice(rs.getInt("CART_PRICE"));
				dto.getCartDTO().setCartQty(rs.getString("CART_QTY"));
				dto.getGoodsDTO().setProdDelFee(rs.getString("PROD_DEL_FEE"));
				dto.getGoodsDTO().setProdImage(rs.getString("PROD_IMAGE"));
				dto.getGoodsDTO().setProdName(rs.getString("PROD_NAME"));
				dto.getGoodsDTO().setProdPrice(rs.getInt("PROD_PRICE"));
				dto.getGoodsDTO().setProdSupplyer(rs.getString("PROD_SUPPLYER"));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}
	
	public void cartInsert(CartDTO dto) {
		sql = " MERGE INTO CART c USING ( SELECT PROD_NUM FROM PRODUCTS WHERE PROD_NUM = ? ) p "
				+ " ON ( c.PROD_NUM = p.PROD_NUM AND c.MEM_ID = ? ) "
				+ " WHEN MATCHED THEN UPDATE SET CART_QTY = CART_QTY + ? , "
				+ " CART_PRICE = CART_PRICE + ? "
				+ " WHEN NOT MATCHED THEN "
				+ " INSERT (c.MEM_ID, c.PROD_NUM, c.CART_QTY, c.CART_PRICE) " + " VALUES(?, ?, ?, ?) ";
		
		getConnect();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getProdNum());
			pstmt.setString(2, dto.getMemId());
			pstmt.setString(3, dto.getCartQty());
			pstmt.setInt(4, dto.getCartPrice());
			pstmt.setString(5, dto.getMemId());
			pstmt.setString(6, dto.getProdNum());
			pstmt.setString(7, dto.getCartQty());
			pstmt.setInt(8, dto.getCartPrice());
			
			int i = pstmt.executeUpdate();
			System.out.println(i + "개가 저장되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void goodsDelte(String prodNum) {
		sql = " DELETE FROM PRODUCTS WHERE PROD_NUM = ? ";
		
		getConnect();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, prodNum);
			int i = pstmt.executeUpdate();
			System.out.println(i + "개가 삭제되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
	}
	
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
		System.out.println(sql);
		System.out.println(prodNum);
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
