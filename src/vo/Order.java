package vo;

import java.util.Date;

public class Order {

	private int no;
	private Date createDate;
	private String status;
	private int totalOrderPrice;
	private int usedPoint;
	private int totalCreditPrice;
	private int depositPoint;
	private int userNo;
	
	public Order () {}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getTotalOrderPrice() {
		return totalOrderPrice;
	}

	public void setTotalOrderPrice(int totalOrderPrice) {
		this.totalOrderPrice = totalOrderPrice;
	}

	public int getUsedPoint() {
		return usedPoint;
	}

	public void setUsedPoint(int usedPoint) {
		this.usedPoint = usedPoint;
	}

	public int getTotalCreditPrice() {
		return totalCreditPrice;
	}

	public void setTotalCreditPrice(int totalCreditPrice) {
		this.totalCreditPrice = totalCreditPrice;
	}

	public int getDepositPoint() {
		return depositPoint;
	}

	public void setDepositPoint(int depositPoint) {
		this.depositPoint = depositPoint;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	
	
}
