package model.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.DTO.CartDTO;
import model.DTO.GoodsCartDTO;
import model.DTO.GoodsDTO;
import model.DTO.OrderList;
import model.DTO.PaymentDTO;
import model.DTO.PurchaseDTO;

public class GoodsDAO extends DataBaseInfo {

	final String COLUMNS = "PROD_NUM, PROD_NAME, PROD_PRICE, PROD_IMAGE, PROD_DETAIL, RPOD_CAPACITY, PROD_SUPPLYER, PROD_DEL_FEE, RECOMMEND, EMPLOYEE_ID, CTGR ";
	
	public void payment(PaymentDTO dto) {
		String num = " select to_char(sysdate,'yyyymmdd') || nvl2(max(PAYMENT_APPR_NUM),substr(max(PAYMENT_APPR_NUM),-6),100000)+1 from payment " + 
				" where substr(PAYMENT_APPR_NUM,1,8)=to_char(sysdate,'yyyymmdd') ";
		
		sql = " INSERT INTO PAYMENT(PURCHASE_NUM, PAYMENT_METHOD, PAYMENT_APPR_PRICE, PAYMENT_APPR_NUM, PAYMENT_APPR_DATE, PAYMENT_NUMBER) "
				+ " VALUES (?, ?, ?, ( " + num 
				+ " ), sysdate, ? ) ";
		
		getConnect();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getPurchaseNum());
			pstmt.setString(2, dto.getPaymentMethod());
			pstmt.setString(3, dto.getPaymentApprPrice());
			pstmt.setString(4, dto.getPaymentNumber());
			
			int i = pstmt.executeUpdate();
			System.out.println(i + "개가 저장되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		
		
	}
	
	public List<OrderList> orderList(String memId) {
		List<OrderList> list = new ArrayList<OrderList>();
		
		sql = " SELECT p2.PURCHASE_DATE, p4.PAYMENT_APPR_NUM, p1.PROD_NUM, p2.PURCHASE_NUM, p1.PROD_NAME, p1.PROD_SUPPLYER, p2.PURCHASE_TOT_PRICE, p1.PROD_IMAGE "
				+ " FROM PRODUCTS p1, PURCHASE p2, PURCHASE_LIST p3, PAYMENT p4 "
				+ " WHERE p2.PURCHASE_NUM = p3.PURCHASE_NUM "
				+ " AND p1.PROD_NUM = p3.PROD_NUM "
				+ " AND p2.PURCHASE_NUM = p4.PURCHASE_NUM(+) "
				+ " AND p2.MEM_ID = ? "
				+ " ORDER BY PURCHASE_NUM DESC ";
		
		getConnect();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				OrderList dto = new OrderList();
				dto.setPaymentApprNum(rs.getString("PAYMENT_APPR_NUM"));
				dto.setProdImage(rs.getString("PROD_NUM"));
				dto.setProdName(rs.getString("PROD_NAME"));
				dto.setProdNum(rs.getString("PROD_NUM"));
				dto.setProdSupplyer(rs.getString("PROD_SUPPLYER"));
				dto.setPurchaseDate(rs.getString("PURCHASE_DATE"));
				dto.setPurchaseTotPrice(rs.getString("PURCHASE_TOT_PRICE"));
				dto.setPurchaseNum(rs.getString("PURCHASE_NUM"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}
	
	public void cartDel(String prodNum, String memId) {
		sql = " DELETE FROM CART "
				+ " WHERE MEM_ID = ? AND PROD_NUM = ? ";
		
		getConnect();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			pstmt.setString(2, prodNum);
			
			int i = pstmt.executeUpdate();
			System.out.println(i + "개가 삭제되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}
	
	public void purchaseListInsert(String purchaseNum, String prodNum, String memId) {
		sql = " INSERT INTO PURCHASE_LIST (PURCHASE_NUM, PROD_NUM, PURCHASE_QTY, PURCHASE_PRICE) "
				+ " SELECT ?, PROD_NUM, CART_QTY, CART_PRICE "
				+ " FROM CART "
				+ " WHERE PROD_NUM = ? AND MEM_ID = ? ";
		
		getConnect();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, purchaseNum);
			pstmt.setString(2, prodNum);
			pstmt.setString(3, memId);
			
			int i = pstmt.executeUpdate();
			System.out.println(i + "개가 입력되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
	}
	
	public void purchaseInsert(PurchaseDTO dto) {
		sql = " INSERT INTO PURCHASE (PURCHASE_NUM, MEM_ID, PURCHASE_TOT_PRICE, "
				+ "PURCHASE_ADDR, PURCHASE_METHOD, PURCHASE_REQUEST, RECEIVER_NAME, "
				+ "RECEIVER_PHONE , PURCHASE_DATE) "
				+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?, SYSDATE) ";
		
		getConnect();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getPurchaseNum());
			pstmt.setString(2, dto.getMemId());
			pstmt.setString(3, dto.getPurchaseTotPrice());
			pstmt.setString(4, dto.getPurchaseAddr());
			pstmt.setString(5, dto.getPurchaseMethod());
			pstmt.setString(6, dto.getPurchaseRequest());
			pstmt.setString(7, dto.getReceiverName());
			pstmt.setString(8, dto.getReceiverPhone());
			
			int i = pstmt.executeUpdate();
			System.out.println(i + "개가 입력되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}
	
	public GoodsCartDTO prodCart(String prodNum, String memId) {
		GoodsCartDTO dto = null;
		
		sql = " SELECT p.PROD_NUM, PROD_NAME, PROD_PRICE, PROD_SUPPLYER, PROD_DEL_FEE, PROD_IMAGE, MEM_ID, CART_QTY, CART_PRICE " 
				+ " FROM PRODUCTS p, CART c "
				+ " WHERE p.PROD_NUM = c.PROD_NUM "
				+ " AND MEM_ID = ? AND c.PROD_NUM = ? ";
		
		getConnect();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			pstmt.setString(2, prodNum);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto = new GoodsCartDTO();
				dto.setCartDTO(new CartDTO());
				dto.setGoodsDTO(new GoodsDTO());
				dto.getGoodsDTO().setProdNum(rs.getString("PROD_NUM"));
				dto.getCartDTO().setCartPrice(rs.getInt("CART_PRICE"));
				dto.getCartDTO().setCartQty(rs.getString("CART_QTY"));
				dto.getGoodsDTO().setProdDelFee(rs.getString("PROD_DEL_FEE"));
				dto.getGoodsDTO().setProdImage(rs.getString("PROD_IMAGE"));
				dto.getGoodsDTO().setProdName(rs.getString("PROD_NAME"));
				dto.getGoodsDTO().setProdPrice(rs.getInt("PROD_PRICE"));
				dto.getGoodsDTO().setProdSupplyer(rs.getString("PROD_SUPPLYER"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return dto;
	}
	
	public void cartProdDel(CartDTO dto) {
		sql = " DELETE FROM CART WHERE MEM_ID = ? AND PROD_NUM = ? ";
		
		getConnect();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getMemId());
			pstmt.setString(2, dto.getProdNum());
			
			int i = pstmt.executeUpdate();
			System.out.println(i + "개가 삭제되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}
	
	public void cartQtyDowm(CartDTO dto) {
		sql = " UPDATE CART SET CART_QTY = CART_QTY - ?, CART_PRICE = CART_PRICE - ? "
				+ " WHERE MEM_ID = ? AND PROD_NUM = ? ";
		
		getConnect();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 1);
			pstmt.setInt(2, dto.getCartPrice());
			pstmt.setString(3, dto.getMemId());
			pstmt.setString(4, dto.getProdNum());
			
			int i = pstmt.executeUpdate();
			System.out.println(i + "개가 수정되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
	}
	
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
				dto.getGoodsDTO().setProdNum(rs.getString("PROD_NUM"));
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
		}finally {
			close();
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
