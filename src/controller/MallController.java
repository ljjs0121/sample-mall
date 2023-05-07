package controller;

import java.util.List;

import dto.CartItemDto;
import dto.CartItemListDto;
import dto.OrderDetailDto;
import dto.OrderItemDto;
import service.CartService;
import service.OrderService;
import service.ProductService;
import service.UserService;
import util.KeyboardReader;
import vo.CartItem;
import vo.Order;
import vo.PointHistory;
import vo.Product;
import vo.User;

public class MallController {

	private KeyboardReader keyboardReader = new KeyboardReader();
	
	private CartService cartService = new CartService();
	private OrderService orderService = new OrderService();
	private ProductService productService = new ProductService();
	private UserService userService = new UserService();
	
	// 인증된 사용자정보가 저장된다.(loginUser가 null 아니면 현재 로그인된 상태다.)
	private LoginUser loginUser = null;
	
	public void menu() {
		System.out.println("-----------------------------------------------------");
		if (loginUser == null) {
			// 초기화면
			System.out.println("1.상품조회  2.로그인  3.회원가입  0.종료");			
		} else {
			// 로그인 상태
			System.out.println("1.쇼핑  2.장바구니  3.주문  4.내정보  5.로그아웃  0.종료");
			System.out.println("-----------------------------------------------------");
			System.out.println("["+loginUser.getName()+"]님 환영합니다.");
		}
		System.out.println("-----------------------------------------------------");
		
		System.out.println();
		System.out.print("### 메뉴선택: ");
		int menu = keyboardReader.readInt();
		System.out.println();
		
		try {
			if (menu == 0) {
				System.out.println("<< 프로그램 종료 >>");
				System.out.println("### 프로그램을 종료합니다.");
				System.exit(0);
			}
			
			if (loginUser == null) {
				if (menu == 1) {
					상품조회();
				} else if (menu == 2) {
					로그인();
				} else if (menu == 3) {
					회원가입();
				}
			} else {
				if (menu == 1) {
					쇼핑();
				} else if (menu == 2) {
					장바구니();
				} else if (menu == 3) {
					주문();
				} else if (menu == 4) {
					내정보();
				} else if (menu == 5) {
					로그아웃();
				}
			}
		} catch (RuntimeException ex) {
			System.out.println("[오류발생] " + ex.getMessage());
			ex.printStackTrace(System.out);
		}
		
		System.out.println();
		System.out.println();
		System.out.println();
		menu();
	}
	
	private void 쇼핑() {
		System.out.println("<< 쇼핑 >>");
		System.out.println("---------------------------------------------");
		System.out.println("1.상품조회  2.바로구매하기  3.장바구니담기  0.종료");
		System.out.println("---------------------------------------------");
		
		System.out.println();
		System.out.print("### 쇼핑메뉴 선택: ");
		int menu = keyboardReader.readInt();
		System.out.println();
		
		if (menu == 1) {
			상품조회();
		} else if (menu == 2) {
			바로구매하기();
		} else if (menu == 3) {
			장바구니담기();
		} else if (menu == 0) {
			System.out.println("### 메인 메뉴로 돌아갑니다.");
			return;
		}
		
		System.out.println();
		System.out.println();
		쇼핑();
	}
	
	private void 장바구니() {
		System.out.println("<< 장바구니 >>");
		System.out.println("---------------------------------------------");
		System.out.println("1.장바구니보기  2.주문하기  3.비우기  0.종료");
		System.out.println("---------------------------------------------");
		
		System.out.println();
		System.out.print("### 장바구니메뉴 선택: ");
		int menu = keyboardReader.readInt();
		System.out.println();
		
		if (menu == 1) {
			장바구니보기();
		} else if (menu == 2) {
			장바구니에서구매하기();
		} else if (menu == 3) {
			장바구니비우기();
		} else if (menu == 0) {
			System.out.println("### 메인 메뉴로 돌아갑니다.");
			return;
		}
		
		System.out.println();
		System.out.println();
		장바구니();
	}
	
	private void 주문() {
		System.out.println("<< 주문 >>");
		System.out.println("---------------------------------------------");
		System.out.println("1.내역보기  2.상세정보보기 3.포인트내역보기  0.종료");
		System.out.println("---------------------------------------------");
		
		System.out.println();
		System.out.print("### 주문메뉴 선택: ");
		int menu = keyboardReader.readInt();
		System.out.println();
		
		if (menu == 1) {
			내주문내역조회();
		} else if (menu == 2) {
			주문상세정보조회();
		} else if (menu == 3) {
			포인트변경이력조회();
		} else if (menu == 0) {
			System.out.println("### 메인 메뉴로 돌아갑니다.");
			return;
		}
		
		System.out.println();
		System.out.println();
		주문();
	}
	
	private void 내정보() {
		System.out.println("<< 내 정보 >>");
		System.out.println("---------------------------------------------");
		System.out.println("1.내정보보기  0.종료");
		System.out.println("---------------------------------------------");
		
		System.out.println();
		System.out.print("### 내 정보메뉴 선택: ");
		int menu = keyboardReader.readInt();
		System.out.println();
		
		if (menu == 1) {
			내정보보기();
		} else if (menu == 0) {
			System.out.println("### 메인 메뉴로 돌아갑니다.");
			return;
		} 
		
		System.out.println();
		System.out.println();
		내정보();
	}
	
	private void 회원가입() {
		System.out.println("<< 회원가입 >>");
		System.out.println("### 아이디, 비밀번호, 이름을 입력하세요.");
		System.out.println();
		
		System.out.print("아이디: ");
		String id = keyboardReader.readString();
		System.out.print("비밀번호: ");
		String password = keyboardReader.readString();
		System.out.print("이름: ");
		String name = keyboardReader.readString();
		
		User user = new User();
		user.setId(id);
		user.setPassword(password);
		user.setName(name);

		userService.registerUser(user);
		
		System.out.println("### 신규 사용자 등록이 완료되었습니다.");
	}
	
	private void 로그인() {
		System.out.println("<< 로그인 >>");
		System.out.println("### 아이디/비밀번호를 입력해서 로그인하세요.");
		System.out.println();
		
		System.out.print("### 아이디: ");
		String userId = keyboardReader.readString();
		System.out.print("### 비밀번호: ");
		String password = keyboardReader.readString();
		
		// LoginUser 타입의 멤버변수에 저장시킨다.
		loginUser = userService.login(userId, password);
		
		System.out.println("### 로그인이 완료되었습니다.");
	}
	
	private void 로그아웃() {
		System.out.println("<< 로그아웃 >>");
		
		loginUser = null;
		System.out.println("### 로그아웃 되었습니다.");
	}
	
	private void 내정보보기() {
		System.out.println("<< 내 정보 보기 >>");
		System.out.println("### 사용자정보를 확인하세요.");
		System.out.println();
		
		User user = userService.getUserDetail(loginUser.getId());
		System.out.println("### 사용자 정보");
		System.out.println("---------------------------------------------------------------");
		System.out.println("사용자 번호: " + user.getNo());
		System.out.println("사용자 아이디: " + user.getId());
		System.out.println("사용자 이름: " + user.getName());
		System.out.println("사용자 포인트: " + user.getPoint());
		System.out.println("가입일자: " + user.getCeateDate());
		System.out.println("---------------------------------------------------------------");
	}
	
	private void 상품조회() {
		System.out.println("<< 상품조회 >>");
		System.out.println("### 상품을 확인하세요.");
		
		List<Product> products = productService.getAllProducts();
		
		System.out.println("------------------------------------------------------------------------------");
		System.out.println("번호\t가격\t재고수량\t상품명");
		System.out.println("------------------------------------------------------------------------------");
		for (Product product : products) {
			System.out.print(product.getNo() + "\t");
			System.out.print(product.getPrice() + "\t");
			System.out.print(product.getStock() + "\t");
			System.out.println(product.getName());
		}
		System.out.println("------------------------------------------------------------------------------");
	}
	
	private void 장바구니보기() {
		System.out.println("<< 장바구니 보기 >>");
		System.out.println("### 장바구니 아이템을 확인하세요.");
		System.out.println();
		
		CartItemListDto listDto = cartService.getMyCartItems(loginUser.getNo());
		
		List<CartItemDto> dtos = listDto.getDtos();
		if (dtos.isEmpty()) {
			System.out.println("### 장바구니가 비어있습니다.");
		} else {
			System.out.println("------------------------------------------------------------------------------");
			System.out.println("상품번호\t상품가격\t수량\t구매가격\t상품명");
			System.out.println("------------------------------------------------------------------------------");
			for (CartItemDto dto : dtos) {
				System.out.print(dto.getProductNo() + "\t");
				System.out.print(dto.getItemAmount() + "\t");
				System.out.print(dto.getProductPrice() + "\t");
				System.out.print(dto.getProductPrice()*dto.getItemAmount()  + "\t");
				System.out.println(dto.getProductName());
			}
			System.out.println("------------------------------------------------------------------------------");
			System.out.println();
			
			System.out.println("------------------------------------------------------------------------------");
			System.out.println("총 구매수량: " + listDto.getTotalAmount() );
			System.out.println("총 구매금액: " + listDto.getTotalOrderPrice());
			System.out.println("------------------------------------------------------------------------------");
		}
		
	}
	
	private void 장바구니담기() {
		System.out.println("<< 장바구니 담기 >>");
		System.out.println("### 상품번호와 상품수량을 입력해서 장바구니에 담으세요.");
		
		System.out.print("### 상품번호: ");
		int productNo = keyboardReader.readInt();
		System.out.print("### 상품수량: ");
		int productAmount = keyboardReader.readInt();
		System.out.println();
		
		CartItem cartItem = new CartItem();
		// 로그인 된 사용자 번호
		cartItem.setUserNo(loginUser.getNo());
		cartItem.setProductNo(productNo);
		cartItem.setAmount(productAmount);
		
		cartService.addCartItem(cartItem);
		
		System.out.println("### 장바구니에 상품이 추가되었습니다.");
	}
	
	private void 장바구니비우기() {
		System.out.println("<< 장바구니 비우기 >>");
		
		cartService.clearMyCartItems(loginUser.getNo());
		
		System.out.println("### 장바구니에 저장된 모든 상품정보가 삭제되었습니다.");
	}
	
	private void 장바구니에서구매하기() { // 장바구니 모든 품목 구매
		System.out.println("<< 장바구니 구매하기 >>");
		System.out.println("### 장바구니에 추가된 모든 상품을 구매합니다.");
		System.out.println();
		
		cartService.buy(loginUser.getNo());
		
		System.out.println("### 장바구니에 저장된 모든 상품을 구매하였습니다.");
	}
	
	private void 바로구매하기() { // 상품 하나 구매
		System.out.println("<< 바로 구매하기 >>");
		System.out.println("### 상품번호, 구매수량을 입력해서 상품을 구매하세요.");
		System.out.println();
		
		System.out.print("### 상품번호: ");
		int productNo = keyboardReader.readInt(); 
		System.out.print("### 구매수량: ");
		int amount = keyboardReader.readInt();
		System.out.println();
		
		orderService.order(productNo, amount, loginUser.getNo());
		
		System.out.println("### 주문이 완료되었습니다.");
	}
	
	private void 내주문내역조회() {
		System.out.println("<< 내 주문내역 조회 >>");
		System.out.println("### 주문 내역을 확인하세요.");
		System.out.println();
		
		List<Order> orders = orderService.getMyOrders(loginUser.getNo());
		if (orders.isEmpty()) {
			System.out.println("### 주문내역이 존재하지 않습니다.");
		} else {
			System.out.println("-----------------------------------------------------------");
			System.out.println("주문번호\t주문날짜\t결제금액\t적립포인트");
			for (Order order : orders) {
				System.out.print(order.getNo() + "\t");
				System.out.print(order.getCreateDate() + "\t");
				System.out.print(order.getTotalOrderPrice() + "\t");
				System.out.println(order.getDepositPoint());
			}
			System.out.print("-----------------------------------------------------------");
		}
		
	}
	
	private void 주문상세정보조회() {
		System.out.println("<< 주문 상세정보 조회 >>");
		System.out.println("### 주문번호를 입력해서 상세정보를 확인하세요.");
		
		System.out.print("### 주문번호 입력: ");
		int orderNo = keyboardReader.readInt();
		System.out.println();
		
		OrderDetailDto dto = orderService.getOrderDetail(orderNo, loginUser.getNo());
		
		Order  order = dto.getOrder();
		List<OrderItemDto> items = dto.getItems();
		
		System.out.println("### 주문정보");
		System.out.println("--------------------------------------------------------------");
		System.out.println("주문번호: " + order.getNo());
		System.out.println("주문날짜: " + order.getCreateDate());
		System.out.println("주문상태: " + order.getStatus());
		System.out.println("주문금액: " + order.getTotalOrderPrice());
		System.out.println("사용한 포인트: " + order.getUsedPoint());
		System.out.println("결제 금액: " + order.getTotalCreditPrice());
		System.out.println("적립된 포인트: " + order.getDepositPoint());
		System.out.println("--------------------------------------------------------------");
		System.out.println();
		
		System.out.println("### 주문상품 정보");
		System.out.println("--------------------------------------------------------------");
		System.out.println("상품번호\t상품가격\t구매수량\t구매금액\t상품이름");
		for (OrderItemDto item : items) {
			System.out.print(item.getNo() + "\t");
			System.out.print(item.getPrice() + "\t");
			System.out.print(item.getAmount() + "\t");
			System.out.print(item.getOrderPrice() + "\t");
			System.out.println(item.getName());
		}
		System.out.println("--------------------------------------------------------------");
		
	}
	
	private void 포인트변경이력조회() {
		System.out.println("<< 포인트 변경이력 조회 >>");
		System.out.println("### 포인트 변경 이력을 확인하세요.");
		System.out.println();
		
		List<PointHistory> histories = orderService.getMyPointHistories(loginUser.getNo());
		
		if (histories.isEmpty()) {
			System.out.println("### 포인트 변경 이력정보가 존재하지 않습니다.");
		} else {
			System.out.println("### 포인트 변경 이력 정보");
			System.out.println("---------------------------------------------------------");
			System.out.println("순번\t적립포인트\t누적포인트\t날짜");
			int count = 1;
			for (PointHistory history : histories) {
				System.out.print(count + "\t");
				System.out.print(history.getDepositPoint() + "\t");
				System.out.print(history.getCurrentPoint() + "\t");
				System.out.println(history.getCreateDate());
				count ++;
			}
			System.out.println("---------------------------------------------------------");
		}
	}
	
	public static void main(String[] args) {
		new MallController().menu();
	}
}

