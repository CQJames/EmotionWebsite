package com.ew.exception;

public class UserLoginException extends RuntimeException {

	/**
	 * user登录异常类
	 */
	private static final long serialVersionUID = -8844204743938320066L;

	public UserLoginException(String message) {
        super(message);
    }
}
