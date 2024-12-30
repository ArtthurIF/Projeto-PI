package controllers;

import java.util.Date;

import models.Usuario;
import play.libs.Crypto;
import play.mvc.Controller;

public class Login extends Controller{
	
	//localhost:9000/login/teste para cadastrar no banco de dados
	public static void teste() {
		
		Usuario u = new Usuario();
		u.nome = "Arthur";
		u.email = "arthur@gmail.com";
		u.senha = "12345";
		u.cpf = "0439851";
		u.nivel = 1;
	
		u.save();
		
		form();
	}

	public static void form() {
		render();
	}
	
	public static void logar(String email, String senha) {
		
		Usuario usu = Usuario.find("email = ?1 and senha = ?2 ",
				email, Crypto.passwordHash(senha)).first();
		
		if(usu==null) {
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
