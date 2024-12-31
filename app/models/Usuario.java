package models;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;
import play.libs.Crypto;

@Entity
public class Usuario extends Model{
	
	public String nome;
	public String email;
	public String senha;
	public String cpf;
	public int nivel;
	
	@OneToMany(mappedBy = "usuario")
	public List<Viagem> viagens;
	
	public void setSenha(String s) {
		senha = Crypto.passwordHash(s);
	}
}
