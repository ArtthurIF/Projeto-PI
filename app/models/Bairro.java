package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class Bairro extends Model{
	public Bairro(String string, Cidade c1) {
		nome = string;
		cidade = c1;
	}

	public String nome;
	
	@ManyToOne
	public Cidade cidade;
}
