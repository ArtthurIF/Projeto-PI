package controllers;

import java.util.ArrayList;
 
import models.Cidade;
import models.UF;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import play.mvc.Controller;

@OnApplicationStart
public class Starter extends Job {

	public void doJob() {
	    if (Cidade.count() == 0) {
	        new Cidade("AC", UF.Nacional).save();
	        new Cidade("AL", UF.Nacional).save();
	        new Cidade("AP", UF.Nacional).save();
	        new Cidade("AM", UF.Nacional).save();
	        new Cidade("BA", UF.Nacional).save();
	        new Cidade("CE", UF.Nacional).save();
	        new Cidade("DF", UF.Nacional).save();
	        new Cidade("ES", UF.Nacional).save();
	        new Cidade("GO", UF.Nacional).save();
	        new Cidade("MA", UF.Nacional).save();
	        new Cidade("MT", UF.Nacional).save();
	        new Cidade("MS", UF.Nacional).save();
	        new Cidade("MG", UF.Nacional).save();
	        new Cidade("PA", UF.Nacional).save();
	        new Cidade("PB", UF.Nacional).save();
	        new Cidade("PR", UF.Nacional).save();
	        new Cidade("PE", UF.Nacional).save();
	        new Cidade("PI", UF.Nacional).save();
	        new Cidade("RJ", UF.Nacional).save();
	        new Cidade("RN", UF.Nacional).save();
	        new Cidade("RS", UF.Nacional).save();
	        new Cidade("RO", UF.Nacional).save();
	        new Cidade("RR", UF.Nacional).save();
	        new Cidade("SC", UF.Nacional).save();
	        new Cidade("SP", UF.Nacional).save();
	        new Cidade("SE", UF.Nacional).save();
	        new Cidade("TO", UF.Nacional).save();
	        new Cidade("Estados Unidos", UF.Internacional).save();
	        new Cidade("França", UF.Internacional).save();
	        new Cidade("Espanha", UF.Internacional).save();
	        new Cidade("Italia", UF.Internacional).save();
	        new Cidade("Alemanha", UF.Internacional).save();
	        new Cidade("México", UF.Internacional).save();
	        new Cidade("Japão", UF.Internacional).save();
	        new Cidade("Argentina", UF.Internacional).save();
	        new Cidade("Uruguai", UF.Internacional).save();
		    }
	}
}
