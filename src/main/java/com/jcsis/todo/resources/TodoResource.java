package com.jcsis.todo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jcsis.todo.domain.Todo;
import com.jcsis.todo.services.TodoService;

@RestController
@RequestMapping(value = "/tarefas")
public class TodoResource {
	@Autowired
	private TodoService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Todo> findById(@PathVariable Integer id) {
		Todo obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(value = "/todas", method = RequestMethod.GET)
	public ResponseEntity<List<Todo>> listarTarefas() {
		List<Todo> lista = service.listarTarefas();
		return ResponseEntity.ok().body(lista);
	}

	@RequestMapping(value = "/abertas", method = RequestMethod.GET)
	public ResponseEntity<List<Todo>> listarTarefasAbertos() {
		List<Todo> lista = service.listarTarefasAbertas();
		return ResponseEntity.ok().body(lista);
	}

	@RequestMapping(value = "/concluidas", method = RequestMethod.GET)
	public ResponseEntity<List<Todo>> listarTarefasConcluidas() {
		List<Todo> lista = service.listarTarefasConcluidas();
		return ResponseEntity.ok().body(lista);
	}

}
