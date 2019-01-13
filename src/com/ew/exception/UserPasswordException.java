package com.ew.exception;

public class UserPasswordException extends RuntimeException {

	/**
	 * 用户安全功能异常
	 */
	private static final long serialVersionUID = -5376037797827971991L;

	public UserPasswordException(String message) {
        super(message);
    }
}
