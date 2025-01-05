package controllers;

import java.util.List;

import models.Cidade;
import models.UF;
import play.mvc.Controller;

public class Cidades extends Controller {
	
	public static void listar (String uf) {
		
		List<Cidade> cidades = Cidade.find("uf = ?", UF.valueOf(uf) ).fetch();
		
		renderJSON(cidades);
	}

}
