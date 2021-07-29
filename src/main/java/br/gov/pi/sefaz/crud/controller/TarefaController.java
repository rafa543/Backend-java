package br.gov.pi.sefaz.crud.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gov.pi.sefaz.crud.modelo.Tarefa;
import br.gov.pi.sefaz.crud.repository.TarefaRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class TarefaController {
	
	@Autowired
	TarefaRepository tarefaRepository;
	
	@GetMapping("/tarefas")
	public ResponseEntity<List<Tarefa>> getAllTutorials(@RequestParam(required = false) String title) {
	    try {
	      List<Tarefa> tarefas = new ArrayList<Tarefa>();

	      if (title == null)
	    	  tarefaRepository.findAll().forEach(tarefas::add);
	      else
	    	  tarefaRepository.findByTitleContaining(title).forEach(tarefas::add);

	      if (tarefas.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }

	      return new ResponseEntity<>(tarefas, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	
	@GetMapping("/tarefas/{id}")
	  public ResponseEntity<Tarefa> getTutorialById(@PathVariable("id") long id) {
	    Optional<Tarefa> tarefaData = tarefaRepository.findById(id);

	    if (tarefaData.isPresent()) {
	      return new ResponseEntity<>(tarefaData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	
	@PostMapping("/tarefas")
	  public ResponseEntity<Tarefa> createTutorial(@RequestBody Tarefa tarefa) {
	    try {
	      Tarefa _tarefa = tarefaRepository
	          .save(new Tarefa(tarefa.getTitle(), tarefa.getDescription()));
	      
	      return new ResponseEntity<>(_tarefa, HttpStatus.CREATED);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	
	 @PutMapping("/tarefas/{id}")
	  public ResponseEntity<Tarefa> updateTutorial(@PathVariable("id") long id, @RequestBody Tarefa tarefa) {
	    Optional<Tarefa> tarefaData = tarefaRepository.findById(id);

	    if (tarefaData.isPresent()) {
	      Tarefa _tarefa = tarefaData.get();
	      _tarefa.setTitle(tarefa.getTitle());
	      _tarefa.setDescription(tarefa.getDescription());
	      _tarefa.setStatus(tarefa.getStatus());
	      _tarefa.setDataConclusao(tarefa.getDataConclusao());
	      return new ResponseEntity<>(tarefaRepository.save(_tarefa), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	 
	 @DeleteMapping("/tarefas/{id}")
	  public ResponseEntity<HttpStatus> deleteTarefa(@PathVariable("id") long id) {
	    try {
	      tarefaRepository.deleteById(id);
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
}
















