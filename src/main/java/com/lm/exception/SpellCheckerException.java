package com.lm.exception;

public class SpellCheckerException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	public SpellCheckerException(String msg){
		 super(msg);
	}
	public SpellCheckerException(String msg, Throwable th){
		 super(msg, th);
	}
}
