package models;

import java.util.Date;

import javax.persistence.ManyToOne;

import net.sf.oval.constraint.DateRange;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import play.data.validation.Required;
import play.db.jpa.Blob;
import play.db.jpa.Model;

@Entity
public class Viagem extends Model {
	
	@Required
    public String destino;
	@Required
    public String descricao;
	@Required
    public Date dataPartida;
	@Required
    public Date dataRetorno;

	@Enumerated(EnumType.STRING)
	public Status status;
	
	public Blob midia;
	
	public Viagem() {
		this.status = Status.ATIVO;
	}
    
    @ManyToOne
    @Required
    public Usuario usuario;
}