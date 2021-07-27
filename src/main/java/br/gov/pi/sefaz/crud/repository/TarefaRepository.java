package br.gov.pi.sefaz.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.pi.sefaz.crud.modelo.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long>{
	List<Tarefa> findByTitleContaining(String title);
}
