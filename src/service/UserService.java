package service;

import controller.LoginUser;
import dao.UserDao;
import vo.User;

public class UserService {
	
	private UserDao userDao = new UserDao();

	/*
	 * 회원가입 서비스
	 * 반환타입 : 없음
	 * 메소드명 : registerUser
	 * 매개변수 : User user
	 * 업무로직 
	 * 	- 아이디/비밀번호/이름이 포함된 User객체를 전달받아서 회원가입시킨다.
	 * 	- 1. 아이디 중복 체크를 수행한다.
	 * 		- UserDao객체의 getUserById() 메소드를 호출해서 아이디로 사용자 정보를 조회한다.
	 * 		- 사용자가 정보가 존재하면 예외를 던진다.
	 * 	- 2. 신규사용자 정보를 저장한다.
	 * 		- UserDao객체의 insertUser()메소드를 호출해서 사용자정보를 저장시킨다.
	 */
	public void registerUser(User user) {
		User savedUser = userDao.getUserById(user.getId());
		if (savedUser != null) {
			throw new RuntimeException("["+user.getId()+"] 이미 사용중인 아이디입니다.");
		}
		
		userDao.insertUser(user);
		
		
	}
	
	/*
	 * 로그인 서비스
	 * 
	 * 반환타입 : LoginUser
	 * 메소드명 : login
	 * 매개변수 : String, String
	 * 업무로직
	 * 	- 아이디/비밀번호를 전달받아서 사용자 인증작업을 수행하고,
	 * 	  인증이 완료되면 번호/아이디/이름이 포함된 LoginUser객체를 반환한다.
	 *  - 1. 사용자 정보를 조회한다.
	 *  	- UserDao객체의 getuserById()를 호출해서 아이디로 사용자 정보를 조회한다.
	 *  	- 조회된 사용자정보가 null이면 아이디가 존재하지 않으므로 예외를 던진다.
	 *  - 2. 비밀번호를 체크한다.
	 *  	- 조회된 사용자정보의 비밀번호와 입력한 비밀번호를 비교한다.
	 *  	- 비밀번호가 일치하지 않으면 예외를 던진다.
	 *  - 3. LoginUser를 반환한다.
	 *  	- 인증이 완료되었으므로, LoginUser객체를 생성해서 값을 담고 반환한다.
	 */
	public LoginUser login(String userId, String password) {
		User savedUser = userDao.getUserById(userId);
		if (savedUser == null) {
			throw new RuntimeException("["+userId+"] 아이디가 일치하지 않습니다.");
		}

		if (!savedUser.getPassword().equals(password)) {
			throw new RuntimeException("비밀번호가 일치하지 않습니다.");
		}
	
		return new LoginUser(savedUser.getNo(), savedUser.getId(), savedUser.getName());

		// 또는 이것도 가능하다
//		LoginUser loginUser = new LoginUser(savedUser.getNo(), savedUser.getId(), savedUser.getName());
//		
//		return loginUser;
		
	}
	
	/*
	 * 사용자상세정보를 제공하는 서비스
	 * 
	 * 반환타입 : User
	 * 메소드명 : getUserDetail
	 * 매개변수 : String
	 * 업무로직
	 * 	- 사용자아이디를 전달받아서 사용자 아이디에 해당하는 사용자정보를 반환한다.
	 * 	- 1. 사용자 정보 조회하기
	 *  	- UserDao객체의 getuserById()를 호출해서 아이디로 사용자 정보를 조회한다.
	 *  	- 사용자 정보가 존재하지 않으면 예외를 던진다.
	 *  - 2. 사용자 정보 반환하기
	 *  	- 1번에서 조회된 사용자 정보를 반환한다.
	 */
	public User getUserDetail(String userId) {
		User savedUser = new UserDao().getUserById(userId);
		if (savedUser == null) {
			throw new RuntimeException("사용자정보가 존재하지 않습니다.");
		}
		
		return savedUser;
	}
}



















