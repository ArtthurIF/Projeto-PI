package models;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import play.data.validation.Email;
import play.data.validation.Equals;
import play.data.validation.MaxSize;
import play.data.validation.MinSize;
import play.data.validation.Required;
import play.db.jpa.Model;
import play.libs.Crypto;

@Entity
public class Usuario extends Model{
	
	@Required
	public String nome;
	
	@Required
	@Email
	public String email;
	
	@Required
	@Equals(value="confimacaoSenha", message="Preencha 'Senha' e 'Confirmação' com a mesma senha.")
	public String senha;
	
	@Transient
	public String confimacaoSenha;
	
	@Required
	@MinSize(11)
	@MaxSize(11)
	public String cpf;

	public int nivel;
	
	@Enumerated(EnumType.STRING)
	public Status status;
	
	public Usuario() {
		this.status = Status.ATIVO;
	}
	
	@OneToMany(mappedBy = "usuario")
	public List<Viagem> viagens;
	
	public void setSenha(String s) {
		senha = Crypto.passwordHash(s);
	}
	
	public void setconfimacaoSenha(String s) {
		confimacaoSenha = Crypto.passwordHash(s);
	}
	
	
}
