package models;

import java.util.Date;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Viagem extends Model {
	
    public String cliente;
    public String destino;
    public String descricao;
    public Date dataPartida;
    public Date dataRetorno;
    
}