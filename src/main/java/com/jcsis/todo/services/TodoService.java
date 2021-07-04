package com.jcsis.todo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcsis.todo.domain.Todo;
import com.jcsis.todo.repositories.TodoRepository;
import com.jcsis.todo.services.exceptions.ObjectNotFoundException;

@Service
public class TodoService {
	@Autowired
	private TodoRepository repo;
	public Todo findById(Integer id) {
		Optional<Todo> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Todo.class.getName()));
	}
	public List<Todo> listarTarefas() {
		return repo.findAll();
	}
	public List<Todo> listarTarefasAbertas(){
		return repo.findByConcluidaTrue();
	}
	public List<Todo> listarTarefasConcluidas() {
		return repo.findByConcluidaFalse();
	}
	public void salvar(Todo obj) {
		obj.setId(null);
		repo.save(obj);
	}
	public void deletar(Integer id) {
		if (findById(id) != null) {		
			repo.deleteById(id);
		}
	}
	public Todo atualizar(Integer id, Todo obj) {
		Todo newObj = findById(id);
		newObj.setDataTarefa(obj.getDataTarefa());
		newObj.setDescricao(obj.getDescricao());
		newObj.setNome(obj.getNome());
		newObj.setConcluida(obj.getConcluida());
		return repo.save(newObj);
	}
}
