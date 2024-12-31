package models;

import java.util.Date;

import javax.persistence.ManyToOne;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class Viagem extends Model {
	
    public String cliente;
    public String destino;
    public String descricao;
    public Date dataPartida;
    public Date dataRetorno;
    
    @ManyToOne
    @JoinColumn(name = "usuario_Id", referencedColumnName = "id")
    public Usuario usuario;
}