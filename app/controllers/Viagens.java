package controllers;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import models.Usuario;
import models.Viagem;
import play.cache.Cache;
import play.mvc.Controller;

public class Viagens extends Controller {

    
    public static void form(){
    	List<Usuario> usuarios = Usuario.findAll();
    	
    	Viagem v = (Viagem) Cache.get("v");
    	Cache.get("v");
        render(usuarios, "v");
    }
 
    
    public static void salvar(Viagem v, long usuarioId ) {

    	if(v.dataRetorno != null && v.dataRetorno.before(v.dataPartida)) {
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
        if(termo == null) {
            viagens = Viagem.findAll();
        } else {
            viagens = Viagem.find("lower(cliente) like ?1",
                    "%" + termo.toLowerCase() + "%").fetch();
        }
        render(viagens, termo);
    }
    
    public static void remover(long id) {
        Viagem v = Viagem.findById(id);
        v.delete();
        flash.success("Viagem removida com sucesso!");
        listar(null);
    } 
    
    public static void editar(Long id) {
        Viagem v = Viagem.findById(id);
        List<Usuario> usuarios = Usuario.findAll();
        renderTemplate("Viagens/form.html", v, usuarios);
    }

}