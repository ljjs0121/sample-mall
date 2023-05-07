package controller;
// 로그인 정보 저장하는 클래스
public class LoginUser {

	private int no;
	private String id;
	private String name;
	
	public LoginUser (int no, String id, String name) {
		this.no = no;
		this.id = id;
		this.name = name;
	}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
