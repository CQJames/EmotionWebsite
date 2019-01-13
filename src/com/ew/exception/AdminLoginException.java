package com.ew.exception;

public class AdminLoginException extends RuntimeException {

	/**
	 * admin登录异常类
	 */
	private static final long serialVersionUID = -8654874706358988845L;

	public AdminLoginException(String message) {
        super(message);
    }

}