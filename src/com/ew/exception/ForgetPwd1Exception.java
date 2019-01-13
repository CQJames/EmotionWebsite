package com.ew.exception;

public class ForgetPwd1Exception extends RuntimeException {

	/**
	 * user找回密码异常类
	 */
	private static final long serialVersionUID = -5918841329522131395L;

	public ForgetPwd1Exception(String message) {
        super(message);
    }
}
