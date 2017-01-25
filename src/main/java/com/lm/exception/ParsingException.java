package com.lm.exception;

public class ParsingException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	public ParsingException(String msg){
		 super(msg);
	}
	public ParsingException(String msg, Throwable th){
		 super(msg, th);
	}
}
