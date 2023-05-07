package dto;

import java.util.List;

import vo.Order;

public class OrderDetailDto {

	private Order order;
	private List<OrderItemDto> items;
	
	public OrderDetailDto() {}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public List<OrderItemDto> getItems() {
		return items;
	}

	public void setItems(List<OrderItemDto> items) {
		this.items = items;
	}
	
	
}
