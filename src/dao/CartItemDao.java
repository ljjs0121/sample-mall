package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.CartItemDto;
import util.ConnUtils;
import vo.CartItem;

public class CartItemDao {
	
	
	/*
	 * 사용자의 장바구니 아이템정보를 조회하는 select 기능
	 * 반환타입: List<CartItemDto>
	 * 메소드명: getCartItemDtosByUserNo
	 * 매개변수: int 
	 */
	public List<CartItemDto> getCartItemDaosByUserNo (int userNo) {
		String sql = "select c.product_no, p.product_price, "
				+ "			 c.item_amount, p.product_name "
				+ "	  from sample_cart_items c, sample_products p " // 조인
				+ "   where c.product_no = p.product_no " // 조인
				+ "	  and c.user_no = ? ";
		
		try {
			List<CartItemDto> dtos = new ArrayList<>();
			
			Connection con = ConnUtils.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, userNo);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				CartItemDto dto = new CartItemDto();
				dto.setProductNo(rs.getInt("product_no"));
				dto.setProductPrice(rs.getInt("product_price"));
				dto.setItemAmount(rs.getInt("item_amount"));
				dto.setProductName(rs.getString("product_name"));
				
				dtos.add(dto);
			}
			rs.close();
			pstmt.close();
			con.close();
			
			return dtos;
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}

	/*
	 * 장바구니 아이템을 추가하는 INSERT 기능이다.
	 * 반환타입 : 없음
	 * 메소드명 : insertCartItem
	 * 매개변수 : CartItem
	 */
	public void insertCartItem(CartItem item) {
		String sql = "insert into sample_cart_items "
				+ " (user_no, product_no, item_amount) "
				+ " values "
				+ " (?, ?, ?)";
		try {
			Connection con = ConnUtils.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, item.getUserNo());
			pstmt.setInt(2, item.getProductNo());
			pstmt.setInt(3, item.getamount());
			
			pstmt.executeUpdate();
			
			pstmt.close();
			con.close();
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
		
	}
	
	/*
	 * 장바구니 아이템 정보가 존재하는지 조회하는 SELECT 기능이다.
	 * 반환타입: List<cartItem>
	 * 메소드명: getCartItems
	 * 매개변수: int, int
	 */
	public CartItem getCartItem(int userNo, int productNo) {
		String sql = "select * "
				+ " from sample_cart_items "
				+ " where user_no = ? "
				+ " and product_no = ? ";
		
		try {
			// 객체 하나만 불러올 떄
			CartItem cartItem = null;
			
			Connection con = ConnUtils.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, userNo);
			pstmt.setInt(2, productNo);
			
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				cartItem = new CartItem();
				cartItem.setUserNo(rs.getInt("user_no"));
				cartItem.setProductNo(rs.getInt("product_no"));
				cartItem.setAmount(rs.getInt("item_amount"));
				
			}
			rs.close();
			pstmt.close();
			con.close();
			
			return cartItem;
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
		
	}
	
	/*
	 * 장바구니 아이템정보를 삭제하는 delete 기능
	 * 반환타입 : void
	 * 메소드명 : deleteCartItemsByUserNo
	 * 매개변수 : int 
	 */
	public void deleteCartItemByUserNo (int userNo) {
		String sql = "delete from sample_cart_items "
				+ "where user_no = ? ";
		
		try {
			Connection con = ConnUtils.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, userNo);
			
			pstmt.executeUpdate();
			
			pstmt.close();
			con.close();
			
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
		
	}
	
}
