package vo;

public class CartItem {

	private int userNo;
	private int productNo;
	private int amount;
	
	public CartItem () {}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public int getProductNo() {
		return productNo;
	}

	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}

	public int getamount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
}
