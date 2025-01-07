package controllers;

import java.util.List;

import models.Cidade;
import models.UF;
import play.mvc.Controller;

public class Cidades extends Controller {
	
	public static void listar (UF uf) {
		
		List<Cidade> cidades = Cidade.find("uf = ?", uf ).fetch();
		
		renderJSON(cidades);
	}

}
