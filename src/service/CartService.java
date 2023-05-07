package service;

import java.util.List;

import dao.CartItemDao;
import dao.OrderDao;
import dao.OrderItemDao;
import dao.PointHistoryDao;
import dao.ProductDao;
import dao.UserDao;
import dto.CartItemDto;
import dto.CartItemListDto;
import vo.CartItem;
import vo.Order;
import vo.OrderItem;
import vo.PointHistory;
import vo.Product;
import vo.User;

public class CartService {
	
	private CartItemDao cartItemDao = new CartItemDao();
	private OrderDao orderDao = new OrderDao();
	private OrderItemDao orderItemDao = new OrderItemDao();
	private ProductDao productDao = new ProductDao();
	private PointHistoryDao pointHistoryDao = new PointHistoryDao();
	private UserDao userDao = new UserDao();
	
	/*
	 * 장바구니에 장바구니 아이템 추가 서비스
	 * 
	 * 반환타입: void 
	 * 메소드명: addCartItem
	 * 매개변수: CartItem
	 * 업무로직
	 *  - 장바구니에 장바구니 아이템을 추가한다.
	 *  - 1. 사용자번호, 상품번호, 수량이 포함된 CartItem 객체를 전달받아서 저장시킨다.
	 *  	- CartItemDao객체의 insertCartItem() 메소드를 호출해서 저장시킨다.
	 */
	public void addCartItem(CartItem cartItem) {
		// 먼저 중복여부 체크
		CartItem savedCartItem = cartItemDao.getCartItem(cartItem.getUserNo(), cartItem.getProductNo());
		if (savedCartItem != null) {
			throw new RuntimeException("장바구니에 동일한 상품이 이미 저장되어 있습니다.");
		}
		cartItemDao.insertCartItem(cartItem);
	}
	
	/*
	 * 나의 장바구니 아이템 정보를 반환하는 서비스
	 * 
	 * 반환타입: CartItemListDto
	 * 메소드명: getMyCartItems
	 * 매개변수: int
	 * 업무로직
	 * 	- 사용자번호를 전달받아서 사용자번호에 해당번호로 등록된 장바구니 아이템정보를 반환한다.
	 * 	- 1. 장바구니 아이템 정보 조회 및 반환하기
	 * 		- CartItemDao의 getCartItemDtosByUserNo()를 호출해서 장바구니 아이템 정보를 조회하고, 조회된 결과를 반환한다.
	 * 		- sample_cart_items 테이블에 저장된 장바구니 아이템 정보가 없으면
	 * 		  빈 List<CartItemDto>객체가 반환된다.
	 */
	public CartItemListDto getMyCartItems(int userNo) {
		List<CartItemDto> dtos = cartItemDao.getCartItemDaosByUserNo(userNo);
		
		CartItemListDto listDto = new CartItemListDto();
		listDto.setDtos(dtos);
		
		return listDto;
	}
	
	/*
	 * 나의 장바구니 아이템을 전부 삭제하는 서비스
	 * 
	 * 반환타입: void
	 * 메소드명: clearMyCartItems
	 * 매개변수: int
	 * 업무로직
	 * 	- 사용자번호를 전달받아서 해당 사용자의 장바구니 아이템정보를 전부 삭제시킨다.
	 * 	- 1. 장바구니 아이템 정보 삭제하기
	 * 		- CartItemDao객체의 deleteCartItemsByUserNo()메소드를 호출해서 사용자의 모든 장바구니 아이템정보를 삭제한다.
	 */
	public void clearMyCartItems (int userNo) {
		cartItemDao.deleteCartItemByUserNo(userNo);
	}
	
	/*
	 * 장바구니 저장된 상품 모두 구매하는 서비스
	 * 
	 * 반환타입 void
	 * 메소드명 buy
	 * 매개변수 int
	 * 업무로직 
	 * 	- 1. 사용자번호로 해당 사용자의 장바구니아이템정보를 전부 조회한다.
	 *  - 2. 주문정보, 주문상품정보, 포인트변경이력정보에 활용되는 주문일련번호를 발행받는다.
	 *  - 3. 주문정보저장에 필요한 정보를 Order객체에 저장하고, DAO에 전달해서 저장시킨다.
	 *  - 4. 장바구니 아이템의 갯수만큼 아래 작업을 반복실행한다.
	 *  		주문상품정보 저장에 필요한 정보를 OrderItem객체에 저장하고, DAO에 전달해서 저장시킨다.
	 *  		상품의 재고를 구매수량만큼 감소시키고, DAO에 전달해서 테이블에 반영시킨다.
	 *  - 5. 총구매금엑에 대한 적립포인트를 계산하고, 포인트변경이력정보를 PointHistory객체에 저장하고, DAO에 전달해서 저장시킨다.
	 *  - 6. 사용자번호로 사용자정보를 조회한다.
	 *  	 사용자의 포인트를 적립포인트만큼 증가시키고, DAO에 전달해서 테이블에 반영시킨다.
	 *  - 7. 장바구니를 비운다.
	 */
	public void buy(int userNo) {
		CartItemListDto cartItemListDto = this.getMyCartItems(userNo);
		
		int orderNo = orderDao.getSequence();
		
		//주문정보 저장하기
		int totalOrderPrice = cartItemListDto.getTotalOrderPrice();
		int userpoint = 0;
		int totalCreditPrice = cartItemListDto.getTotalOrderPrice();
		int depositPoint = (int) (totalCreditPrice*0.01);
		
		Order order = new Order();
		order.setNo(orderNo);
		order.setTotalOrderPrice(totalOrderPrice);
		order.setUsedPoint(userpoint);
		order.setTotalCreditPrice(totalCreditPrice);
		order.setDepositPoint(depositPoint);
		order.setUserNo(userNo);
		
		orderDao.insertOrder(order);
		
		List<CartItemDto> dtos = cartItemListDto.getDtos();
		for (CartItemDto item : dtos) {
			int productNo = item.getProductNo();
			
			//주문상품정보 저장하기
			OrderItem orderItem = new OrderItem();
			orderItem.setOrderNo(orderNo);
			orderItem.setProductNo(productNo);
			orderItem.setAmount(item.getItemAmount());
			orderItem.setPrice(item.getProductPrice());
			
			orderItemDao.insertOrderItem(orderItem);
			
			// 상품정보의 재고수량 변경하기
			Product product = productDao.getProductByNo(productNo);
			product.setStock(product.getStock() - item.getItemAmount());
			
			productDao.updateProduct(product);
			
		}
		
		// 사용자의 포인트 변경하기
		User user = userDao.getUserByNo(userNo);
		user.setPoint(user.getPoint() + depositPoint);
		userDao.updateUser(user);

		// 포인트 변경이력정보 저장하기
		PointHistory history = new PointHistory();
		history.setUserNo(userNo);
		history.setOrderNo(orderNo);
		history.setDepositPoint(depositPoint);
		history.setCurrentPoint(user.getPoint());
		
		pointHistoryDao.insertHistory(history);
		
		// 장바구니 비우기
		this.clearMyCartItems(userNo);
		
	}
}









