package models;

import java.sql.Date;

import javax.persistence.Entity;

import play.db.jpa.Model;
import play.libs.Crypto;

@Entity
public class Usuario extends Model{
	
	public String nome;
	public String email;
	public String senha;
	public String cpf;
	public int nivel;
	
	public void setSenha(String s) {
		senha = Crypto.passwordHash(s);
	}
}
