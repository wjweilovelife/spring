package com.pactera.exception;

/**
 * Created by Ptrk on 2015/11/20.
 */
public class NotFoundException extends RuntimeException {
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	
	private static final long serialVersionUID = -4943117481939153971L;

	public NotFoundException(int i, String s) {
		super(s);
	}
}
