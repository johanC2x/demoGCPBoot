package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post")
public class PostController {

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<String>> index() throws Exception {
		List<String> lista = new ArrayList<String>();
		lista.add("valor1");
		lista.add("valor2");
		lista.add("valor3");
		return new ResponseEntity<>(lista, HttpStatus.OK);
	}
	
}
