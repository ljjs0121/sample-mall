package dto;
// 

import java.util.List;

public class CartItemListDto {

	private List<CartItemDto> dtos;
	
	public List<CartItemDto> getDtos() {
		return dtos;
	}
	
	public void setDtos(List<CartItemDto> dtos) {
		this.dtos = dtos;
	}
	
	// 총 수량
	public int getTotalAmount() {
		int totalAmount = 0;
		
		for (CartItemDto dto : dtos) {
			totalAmount += dto.getItemAmount();
		}
		
		return totalAmount;
	}
	
	// 총 구매가격
	public int getTotalOrderPrice() {
		int totalOrderPrice = 0;
		
		for (CartItemDto dto : dtos) {
			int price = dto.getProductPrice();
			int amount = dto.getItemAmount();
			int orderPrice = price*amount;
			
			totalOrderPrice += orderPrice;
		}
		
		return totalOrderPrice;
	}
	
	// 총 할인금액
	
}
