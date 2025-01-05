package controllers;

import java.util.ArrayList;

import models.Bairro;
import models.Cidade;
import models.UF;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import play.mvc.Controller;

@OnApplicationStart
public class Starter extends Job {

	public void doJob(){
		if (Cidade.count() == 0){
			
			Cidade c1 = new Cidade("Natal", UF.RN);
			Cidade c2 = new Cidade("Parnamirim", UF.RN);
			Cidade c3 = new Cidade("Ipanguaçu", UF.RN);
			Cidade c4 = new Cidade("Mossoró", UF.RN);
			Cidade c5 = new Cidade("Assu", UF.RN);
			Cidade c6 = new Cidade("Fortaleza", UF.CE);
			Cidade c7 = new Cidade("Sobral", UF.CE);
			
			ArrayList<Bairro> bairros = new ArrayList<Bairro>();
			
			c1.save();
			bairros.add(new Bairro("Neópolis", c1));
			bairros.add(new Bairro("Ponta negra", c1));
					
			c2.save();
			bairros.add(new Bairro("Nova parnamirim", c2));
			bairros.add(new Bairro("Cohabinal", c2));
			
			
			c3.save();
			bairros.add(new Bairro("Centro", c3));
			bairros.add(new Bairro("Ilha grande", c3));
			
			
			c4.save();
			bairros.add(new Bairro("Centro", c4));
			bairros.add(new Bairro("Itajá", c4));
			
			
			c5.save();
			bairros.add(new Bairro("Caribé", c5));
			bairros.add(new Bairro("Ipaí", c5));
			
			c6.save();
			bairros.add(new Bairro("Centro", c6));
			bairros.add(new Bairro("Norte", c6));
			
			c7.save();
			bairros.add(new Bairro("Zona velha", c7));
			bairros.add(new Bairro("Zona nova", c7));
			
			for (Bairro b : bairros){
				b.save();
			}
			
		}
	}
}
