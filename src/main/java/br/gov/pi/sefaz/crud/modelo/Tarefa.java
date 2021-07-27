package br.gov.pi.sefaz.crud.modelo;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "tarefas")
public class Tarefa {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;

	private LocalDateTime dataCriacao = LocalDateTime.now();
	private LocalDateTime dataConclusao;
	private StatusTarefa status = StatusTarefa.ABERTA;

	public Tarefa() {

	}

	public Tarefa(String title, String description) {
		this.title = title;
		this.description = description;
	}

	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public LocalDateTime getDataConclusao() {
		return dataConclusao;
	}

	public StatusTarefa getStatus() {
		return status;
	}
	
	public void setDataConclusao(LocalDateTime dataConclusao) {
		this.dataConclusao = dataConclusao;
	}

	public void setStatus(StatusTarefa status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Tarefa [id=" + id + ", title=" + title + ", desc=" + description +"]";
	}
}










