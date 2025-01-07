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
	        new Cidade("Natal", UF.RN).save();
	        new Cidade("Parnamirim", UF.RN).save();
	        new Cidade("Ipanguaçu", UF.RN).save();
	        new Cidade("Mossoró", UF.RN).save();
	        new Cidade("Assu", UF.RN).save();
	        new Cidade("Fortaleza", UF.CE).save();
	        new Cidade("Sobral", UF.CE).save();
	    }
	}
}
