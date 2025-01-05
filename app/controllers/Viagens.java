package controllers;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import models.Status;
import models.UF;
import models.Usuario;
import models.Viagem;
import play.cache.Cache;
import play.mvc.Controller;

public class Viagens extends Controller {

    
    public static void form(){
    	
    	List<UF> ufs = Arrays.asList(UF.values());
    	
    	List<Usuario> usuarios = Usuario.findAll();
    	
    	Viagem v = (Viagem) Cache.get("v");
    	
        render(usuarios, "v", ufs);
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
            viagens = Viagem.find("status = ?1", Status.ATIVO).fetch();
            
        } else {
            viagens = Viagem.find("lower(destino) like ?1",
                    "%" + termo.toLowerCase() + "%").fetch();
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
    	
        Viagem v = Viagem.findById(id);
        List<Usuario> usuarios = Usuario.findAll();
        renderTemplate("Viagens/form.html", v, usuarios, ufs);
    }

}