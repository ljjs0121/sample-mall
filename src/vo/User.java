package vo;

import java.util.Date;

public class User {

	private int no;
	private String id;
	private String password;
	private String name;
	private int point;
	private Date ceateDate;
	
	public User() {}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public Date getCeateDate() {
		return ceateDate;
	}

	public void setCeateDate(Date ceateDate) {
		this.ceateDate = ceateDate;
	}
	
	
}
