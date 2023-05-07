package dto;

public class OrderItemDto {
	
	private int no;
	private int price;
	private int amount;
	private int orderPrice;
	private String name;
	
	public OrderItemDto() {}
	
	public int getNo() {
		return no;
	}
	
	public void setNo(int no) {
		this.no = no;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public int getOrderPrice() {
		return orderPrice;
	}
	
	public void setOrderPrice(int orderPrice) {
		this.orderPrice = orderPrice;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
