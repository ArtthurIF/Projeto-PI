package controllers;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import models.Cidade;
import models.Status;
import models.UF;
import models.Usuario;
import models.Viagem;
import play.cache.Cache;
import play.mvc.Controller;
import play.mvc.With;

@With(Segurança.class)

public class Viagens extends Controller {

	public static void userPhoto(Long id) {
		Viagem v = Viagem.findById(id);
		notFoundIfNull(v);
		response.setContentTypeIfNotSet(v.midia.type());
		renderBinary(v.midia.get());
	}

	public static void obterCidades(String uf) {
		List<Cidade> cidades = Cidade.find("uf = ?1", UF.valueOf(uf)).fetch();
		renderJSON(cidades);
	}

	public static void form() {

		List<UF> ufs = Arrays.asList(UF.values());
		List<Cidade> cidades = Cidade.findAll();

		List<Usuario> usuarios = Usuario.findAll();

		Viagem v = (Viagem) Cache.get("v");

		render(usuarios, "v", ufs, cidades);
	}

	public static void salvar(Viagem v, long usuarioId) {

		if (v.dataRetorno != null && v.dataRetorno.before(v.dataPartida)) {
			validation.addError("v.dataRetorno", "A data de RETORNO não deve ser antes da data de PARTIDA");
		}

		String mensagem = "Cadastro realizado com sucesso!";
		Usuario usuario = Usuario.findById(usuarioId);
		v.usuario = usuario;
		validation.valid(v);

		if (validation.hasErrors()) {
			validation.keep();

			flash.error("");
			Cache.set("v", v);
			form();
		}

		if (v.id != null) {
			mensagem = "Viagem editada com sucesso!";
		}
		v.save();
		flash.success(mensagem);
		listar(null);
	}
	
	public static void listar(String termo) {
	    List<Viagem> viagens = null;

	    // Se o termo for nulo, busca todas as viagens com status ATIVO
	    if (termo == null) {
	        viagens = Viagem.find("status = ?1", Status.ATIVO).fetch();
	    } else {
	        // Se o termo for fornecido, busca viagens ativas que correspondam ao termo no nome da cidade
	        viagens = Viagem.find("lower(cidade.nome) like ?1 and status = ?2", "%" + termo.toLowerCase() + "%", Status.ATIVO).fetch();
	    }

	    render(viagens, termo);
	}


	public static void remover(long id) {
		Viagem v = Viagem.findById(id);
		v.status = Status.INATIVO;
		v.save();
		listar(null);
	}

	public static void editar(Long id) {

		List<UF> ufs = Arrays.asList(UF.values());
		List<Cidade> cidades = Cidade.findAll();

		Viagem v = Viagem.findById(id);
		List<Usuario> usuarios = Usuario.findAll();
		renderTemplate("Viagens/form.html", v, usuarios, ufs, cidades);
	}

}