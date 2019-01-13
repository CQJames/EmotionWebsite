package com.ew.exception;

public class UserEmailException extends RuntimeException {

	/**
	 * 验证码异常
	 */
	private static final long serialVersionUID = -8855375009600180364L;

	public UserEmailException(String message) {
        super(message);
    }
}

