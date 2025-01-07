package controllers;

import play.mvc.Before;
import play.mvc.Controller;

public class Segurança extends Controller{

	@Before
	static void verificar() {
		
		if (request.action.equals("Usuarios.form")) {
			return;
		}
		
		if(session.contains("usuario.email") == false) {
		Login.form();
		}
	}

	@Before(unless={"Usuarios.form", "Viagens.form", "Viagens.listar", "Usuarios.salvar", "Viagens.salvar", "Viagens.userPhoto"})
	static void permissoes() {
		if(session.get("usuario.nivel")== null || session.get("usuario.nivel").equals("1")==false) {
			renderText("Acesso negado");
		}
	}
}
