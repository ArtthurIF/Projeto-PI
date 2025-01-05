package models;

import javax.persistence.Entity;

import play.db.jpa.Model;
@Entity
public class Cidade extends Model{
	public Cidade(String string, UF ufn) {
		nome = string;
		uf = ufn;
	}

	public String nome;
	
	public UF uf;
}
