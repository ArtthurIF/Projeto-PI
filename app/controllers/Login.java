package controllers;

import java.util.Date;

import models.Status;
import models.Usuario;
import play.libs.Crypto;
import play.mvc.Controller;

public class Login extends Controller{
	
	public static void form() {
		
		if (Usuario.count() == 0) {
			Usuario u = new Usuario();
			u.cpf = "12345678910";
			u.email = "arthur@gmail.com";
			u.nivel = 1;
			u.nome = "Arthur";
			u.senha = "123456";
			u.status = Status.ATIVO;
			u.save();
			
		}
		
		render();
	}
	
	public static void logar(String email, String senha) {
		
		Usuario usu = Usuario.find("email = ?1 and senha = ?2 ",
				email, Crypto.passwordHash(senha)).first();
		
		if(usu==null) {
			flash.error("Login ou senha invalidos");
			Login.form();
		} else {
			session.put("usuario.email", usu.email);
			session.put("usuario.nivel", usu.nivel);
			
			Viagens.form();
		}
	}
	
	public static void sair() {
		session.clear();
		Login.form();
	}
}
