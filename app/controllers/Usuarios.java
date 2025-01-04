package controllers;

import java.util.List;

import javax.validation.Valid;

import models.Usuario;
import models.Viagem;
import play.cache.Cache;
import play.mvc.Controller;
import play.mvc.With;

@With(Segurança.class)
public class Usuarios extends Controller {
    
    public static void form(){
    	Usuario u = (Usuario) Cache.get("u");
    	Cache.clear();
        render(u);
    }
    
    public static void salvar(Usuario u, String senha) {
    
    	if(senha.equals("")==false || u.id == null) {
        	u.senha = senha;
        	
        	if(senha.length() < 6) {
        		validation.addError("u.senha", "A senha deve conter no minimo 6 digitos");
        	}
        }
    	
        if(u.cpf.length() < 11) {
    		validation.addError("u.cpf", "O CPF deve conter 11 digitos");
    	}
        
        validation.valid(u);
        
        if(senha.equals("") && u.id != null) {
        	validation.removeErrors("u.senha");
        	if(validation.errors().size() == 1) {
        		validation.clear();
        	}
        }
        
    	
    
        if (validation.hasErrors()) {
   		 validation.keep();
   		 
   		 Cache.set("u", u);
   		 
   		form();
   	}
        String mensagem = "Cadastro realizado com sucesso!";
        if(u.id != null){
            mensagem = "Usuario editado com sucesso!";
        }
        
        
       
        u.save();
        flash.success(mensagem);
        listar(null);
    }
    
    public static void listar(String termo) {
        List<Usuario> u = null;
        if(termo == null) {
            u = Usuario.findAll();
        } else {
            u = Usuario.find("lower(nome) like ?1",
                    "%" + termo.toLowerCase() + "%").fetch();
        }
        render(u, termo);
    }
    
    public static void remover(long id) {
        Usuario u = Usuario.findById(id);
        u.delete();
        flash.success("Usuario removido com sucesso!");
        listar(null);
    } 
    
    public static void editar(Long id) {
        Usuario u = Usuario.findById(id);
        renderTemplate("Usuarios/form.html", u);
    }


}