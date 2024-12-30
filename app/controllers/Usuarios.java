package controllers;

import java.util.List;

import models.Usuario;
import models.Viagem;

import play.mvc.Controller;
import play.mvc.With;

@With(Segurança.class)
public class Usuarios extends Controller {
    
    public static void form(){
        render();
    }
    
    public static void salvar(Usuario u, String senha) {
        String mensagem = "Cadastro realizado com sucesso!";
        if(u.id != null){
            mensagem = "Usuario editado com sucesso!";
        }
        if(senha.equals("")==false) {
        	u.senha = senha;
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