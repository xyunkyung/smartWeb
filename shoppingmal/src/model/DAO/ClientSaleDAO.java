package model.DAO;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.DTO.ClientSaleDTO;
import model.DTO.CustomerTotalDTO;
import model.DTO.DeliveryDTO;

public class ClientSaleDAO extends DataBaseInfo {

	public void deliveryCreate(DeliveryDTO dto) {
		String delFree = " SELECT SUM(PROD_DEL_FEE) "
							+ " FROM PURCHASE_LIST p1, PRODUCTS pr "
							+ " WHERE p1.PROD_NUM = pr.PROD_NUM "
							+ " AND p1.PURCHASE_NUM = ? ";
		
		sql = " MERGE INTO DELIVERY d "
				+ " USING (SELECT PURCHASE_NUM FROM PURCHASE WHERE PURCHASE_NUM = ? ) p "
				+ " ON (d.PURCHASE_NUM = p.PURCHASE_NUM) "
				+ " WHEN MATCHED THEN "
				+ " UPDATE SET DELIVERY_COM = ?, DELIVERY_NUM = ?, DELIVERY_EXP_DATE = ?, ARRIVAL_EXP_DATE = ?, DELIVERY_DEL_FREE = ( " + delFree + ") "
				+ " WHEN NOT MATCHED THEN "
				+ " INSERT (DELIVERY_COM, DELIVERY_NUM, DELIVERY_EXP_DATE, ARRIVAL_EXP_DATE, DELIVERY_DEL_FREE, PURCHASE_NUM ) "
				+ " VALUES(?, ?, ?, ?, (" + delFree + "), ? ) ";
		
		getConnect();
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, dto.getPurchaseNum());
			pstmt.setString(2, dto.getDeliveryCom());
			pstmt.setString(3, dto.getDeliveryNum());
			long deliveryExpDate = dto.getDeliveryExpDate().getTime();
			pstmt.setDate(4, new Date(deliveryExpDate));
			long arrivalExpDate = dto.getArrivalExpDate().getTime();
			pstmt.setDate(5, new Date(arrivalExpDate));
			pstmt.setString(6, dto.getPurchaseNum());
			pstmt.setString(7, dto.getDeliveryCom());
			pstmt.setString(8, dto.getDeliveryNum());
			pstmt.setDate(9, new Date(deliveryExpDate));
			pstmt.setDate(10, new Date(arrivalExpDate));
			pstmt.setString(11, dto.getPurchaseNum());
			pstmt.setString(12, dto.getPurchaseNum());
			
			int i = pstmt.executeUpdate();
			System.out.println(i + "개가 입력되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public DeliveryDTO deliverySelect(String purchaseNum) {
		DeliveryDTO dto = null;
		
		sql = " SELECT PURCHASE_NUM, DELIVERY_COM, DELIVERY_NUM, DELIVERY_EXP_DATE, ARRIVAL_EXP_DATE, DELIVERY_DEL_FREE "
				+ " FROM DELIVERY "
				+ " WHERE PURCHASE_NUM = ? ";
		
		getConnect();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, purchaseNum);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto = new DeliveryDTO();
				dto.setArrivalExpDate(rs.getDate("ARRIVAL_EXP_DATE"));
				dto.setDeliveryCom(rs.getString("DELIVERY_COM"));
				dto.setDeliveryDelFee(rs.getString("DELIVERY_DEL_FREE"));
				dto.setDeliveryExpDate(rs.getDate("DELIVERY_EXP_DATE"));
				dto.setDeliveryNum(rs.getString("DELIVERY_NUM"));
				dto.setPurchaseNum(rs.getString("PURCHASE_NUM"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return dto;
	}
	
	public List<CustomerTotalDTO> customerTotal() {
		List<CustomerTotalDTO> list = new ArrayList<CustomerTotalDTO>();
		
		sql = " SELECT M.MEM_ID, MEM_NAME, COUNT(*), SUM(PURCHASE_TOT_PRICE) PRICE, AVG(PURCHASE_TOT_PRICE) "
				+ " FROM MEMBER M, PURCHASE P "
				+ " WHERE M.MEM_ID = P.MEM_ID "
				+ " GROUP BY M.MEM_ID, MEM_NAME ";
		
		getConnect();
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CustomerTotalDTO dto = new CustomerTotalDTO();
				dto.setCount(rs.getString(4));
				dto.setMemId(rs.getString(1));
				dto.setMemName(rs.getString(2));
				dto.setSumPrice(rs.getString(3));
				dto.setAvg(rs.getString(5));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}
	
	public List<ClientSaleDTO> salesList(String memId) {
		List<ClientSaleDTO> list = new ArrayList<ClientSaleDTO>();
		
		sql = " SELECT m.MEM_ID, MEM_NAME, MEM_PHONE, pr.PROD_NUM, PROD_NAME, pu.PURCHASE_NUM, PURCHASE_DATE, PURCHASE_ADDR, RECEIVER_NAME, RECEIVER_PHONE, PURCHASE_QTY, PURCHASE_PRICE, DELIVERY_NUM "
				+ " FROM MEMBER m, PURCHASE pu, PRODUCTS pr, PURCHASE_LIST p1, DELIVERY d "
				+ " WHERE m.MEM_ID(+) = pu.MEM_ID "
				+ " AND pu.PURCHASE_NUM = p1.PURCHASE_NUM "
				+ " AND p1.PROD_NUM = pr.PROD_NUM "
				+ " AND pu.PURCHASE_NUM = d.PURCHASE_NUM(+) ";
		
		if(memId != null) {
			sql += " AND m.MEM_ID = ? ";
		}
		
		getConnect();
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			if(memId != null) {
				pstmt.setString(1, memId);
			}
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ClientSaleDTO dto = new ClientSaleDTO();
				dto.setMemId(rs.getString("MEM_ID"));
				dto.setMemName(rs.getString("MEM_NAME"));
				dto.setMemPhone(rs.getString("MEM_PHONE"));
				dto.setProdName(rs.getString("PROD_NAME"));
				dto.setPurchaseAddr(rs.getString("PURCHASE_ADDR"));
				dto.setPurchaseDate(rs.getString("PURCHASE_DATE"));
				dto.setPurchaseNum(rs.getString("PURCHASE_NUM"));
				dto.setPurchasePrice(rs.getString("PURCHASE_PRICE"));
				dto.setPurchaseQty(rs.getString("PURCHASE_QTY"));
				dto.setReceiverName(rs.getString("RECEIVER_NAME"));
				dto.setReceiverPhone(rs.getString("RECEIVER_PHONE"));
				dto.setDeliveryNum(rs.getString("DELIVERY_NUM"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}
}
