package com.samadai.shop.api.exceptions;

@SuppressWarnings("serial") // para que la advertencia de serializar no moleste si se serializa quitar
public class NotFoundException extends RuntimeException{
	// private static final long serialVersionUID = 1L; // activar si se va a serializar o mandar por la red
	
	public NotFoundException(String msg) {
		super(msg);
	}
}
