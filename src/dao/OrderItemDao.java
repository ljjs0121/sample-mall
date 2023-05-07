package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.OrderItemDto;
import util.ConnUtils;
import vo.OrderItem;

public class OrderItemDao {

	public List<OrderItemDto> getOrderItemDtosByOrderNo(int orderNo) {
		String sql = "select A.product_no, "
				+ "			 A.product_price, "
				+ "			 A.product_amount, "
				+ "			 A.product_price*A.product_amount as order_price, "
				+ "			 B.product_name "
				+ " from sample_order_items A, sample_products B "
				+ " where a.order_no = ? "
				+ " and A.product_no = B.product_no ";
		
		try {
			List<OrderItemDto> items = new ArrayList<>();
			
			Connection con = ConnUtils.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, orderNo);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				OrderItemDto dto = new OrderItemDto();
				dto.setNo(rs.getInt("product_no"));
				dto.setPrice(rs.getInt("product_price"));
				dto.setAmount(rs.getInt("product_amount"));
				dto.setOrderPrice(rs.getInt("order_price"));
				dto.setName(rs.getString("product_name"));
				
				items.add(dto);
			}
			rs.close();
			pstmt.close();
			con.close();
			
			return items;
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}

	/*
	 * 주문 상품정보를 INSERT하는 기능
	 * 
	 * 반환타입 : void
	 * 메소드명 : insertOrderItem
	 * 매개변수 : OrderItem
	 */
	public void insertOrderItem(OrderItem orderItem) {
		String sql = "insert into sample_order_items "
				+ " (order_no, product_no, product_amount, product_price) "
				+ " values "
				+ " (?, ?, ?, ?) ";
		
		try {
			Connection con = ConnUtils.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, orderItem.getOrderNo());
			pstmt.setInt(2, orderItem.getProductNo());
			pstmt.setInt(3, orderItem.getAmount());
			pstmt.setInt(4, orderItem.getPrice());
			
			pstmt.executeUpdate();
			
			pstmt.close();
			con.close();
			
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}
	
}
