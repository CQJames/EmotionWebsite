package com.ew.exception;

public class UserRegisterException extends RuntimeException {

	/**
	 * user注册异常类
	 */
	private static final long serialVersionUID = -8844204743938320066L;

	public UserRegisterException(String message) {
        super(message);
    }
}
