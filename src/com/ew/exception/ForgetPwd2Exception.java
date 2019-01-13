package com.ew.exception;

public class ForgetPwd2Exception extends RuntimeException {

	/**
	 * 用户没有邮箱异常类
	 */
	private static final long serialVersionUID = 4012872762494449279L;

	public ForgetPwd2Exception(String message) {
        super(message);
    }
}

