package com.example.demo.service;

import java.util.Collection;

import com.example.demo.entity.Book;

public interface BookService {
	
	public Collection<Book> obtenerLibros() throws Exception;
	
}
