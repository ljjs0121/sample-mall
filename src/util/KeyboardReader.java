package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 키보드 입력값을 읽어서 문자열, 정수, 실수, 불린값으로 반환하는 기능을 제공한다.
 * @author jhta
 *
 */
public class KeyboardReader {

	private BufferedReader in;
	public KeyboardReader() {
		in = new BufferedReader(new InputStreamReader(System.in));
	}
	
	/**
	 * 키보드 입력값을 읽어서 문자열로 반환한다.
	 * @return 문자열
	 */
	public String readString() {
		try {
			return in.readLine();
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}
	
	/**
	 * 키보드 입력값을 읽어서 int형의 정수로 반환한다.
	 * @return 정수
	 */
	public int readInt() {
		try {
			return Integer.parseInt(in.readLine().trim());
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}
	
	/**
	 * 키보드 입력값을 읽어서 long형의 정수로 반환한다.
	 * @return 정수
	 */
	public long readLong() {
		try {
			return Long.parseLong(in.readLine().trim());
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}
	
	/**
	 * 키보드 입력값을 읽어서 double형의 실수로 반환한다.
	 * @return 실수
	 */
	public double readDouble() {
		try {
			return Double.parseDouble(in.readLine().trim());
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	/**
	 * 키보드 입력값을 읽어서 불린값으로 반환한다.
	 * @return 불린값
	 */
	public boolean readBoolean() {
		try {
			return Boolean.parseBoolean(in.readLine().trim());
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}
}
