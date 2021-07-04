package com.jcsis.todo.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Todo> salvar(@RequestBody Todo obj){
		service.salvar(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Todo> deletar(@PathVariable Integer id){
		service.deletar(id);
		return ResponseEntity.noContent().build();
	}
	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Todo> atualizar(@PathVariable Integer id, @RequestBody Todo obj){
		Todo newObj = service.atualizar(id, obj);
		return ResponseEntity.ok().body(newObj);
	}
}
