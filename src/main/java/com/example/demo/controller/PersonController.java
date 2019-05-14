package com.example.demo.controller;

import java.util.Collection;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Person;
import com.example.demo.repository.PersonRepository;
import com.example.demo.util.Response;

@CrossOrigin
@RestController
@RequestMapping("/people")
public class PersonController {

	private static final Log logger = LogFactory.getLog(PersonController.class);
	
	@Autowired
	private PersonRepository personRepo;

	@Autowired
	private Response response;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Collection<Person>> getPeople() throws Exception {
		logger.info("==================== METHOD => [ getPeople ] ============================");
		return new ResponseEntity<>(personRepo.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Response> getPerson(@PathVariable long id) {
		logger.info("==================== METHOD => [ getPerson ] ============================");
		logger.info("ID INGRESAOD => [" + id + "]");
		if (personRepo.existsById(id)) {
			response.setCode(HttpStatus.OK.toString());
			response.setData(personRepo.findById(id));
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			logger.info("El id ingresado no le pertenece a ningun registro");
			response.setCode(HttpStatus.NOT_FOUND.toString());
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> addPerson(@RequestBody Person person) {
		if (personRepo.save(person) != null) {
			response.setCode(HttpStatus.CREATED.toString());
			response.setData(personRepo.save(person));
		} else {
			response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
		}
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> updatePerson(@RequestBody Person person, @PathVariable("id") long id) {
		if (personRepo.existsById(id)) {
			person.setId(id);
			if (personRepo.save(person) != null) {
				response.setCode(HttpStatus.OK.toString());
				response.setData(personRepo.save(person));
			}
		} else {
			response.setCode(HttpStatus.NOT_FOUND.toString());
		}
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> deletePerson(@PathVariable long id) {
		if (personRepo.existsById(id)) {
			personRepo.deleteById(id);
			response.setCode(HttpStatus.OK.toString());
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.setCode(HttpStatus.NOT_FOUND.toString());
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
	}

	/*
	@RequestMapping(value = "/{id}/parties", method = RequestMethod.GET)
	public ResponseEntity<Collection<Party>> getPersonParties(@PathVariable long id) {
		Person person = personRepo.findById(id);

		if (person != null) {
			return new ResponseEntity<>(person.getParties(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	*/
	
}
