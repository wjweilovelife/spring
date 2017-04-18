package com.pactera.exception;

/**
 * Created by Ptrk on 2015/11/20.
 */
public class BusinessException extends RuntimeException {
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	
	private static final long serialVersionUID = -732349847888799641L;

	public BusinessException(String s) {
		super(s);
	}
}
