import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.Scanner;

import models.entity.Estilo;
import models.entity.Instrumento;
import models.repository.RepositorioDeEstilos;
import models.repository.RepositorioDeInstrumentos;
import play.Application;
import play.GlobalSettings;
import play.Logger;
import play.db.jpa.JPA;

public class Global extends GlobalSettings {
	
	private List<Estilo> estilos;
	private List<Instrumento> instrumentos;
	private static RepositorioDeEstilos repositorioDeEstilos = RepositorioDeEstilos.getInstance();
	private static RepositorioDeInstrumentos repositorioDeInstrumentos = RepositorioDeInstrumentos.getInstance();
	
	@Override
	public void onStart(Application app) {
		super.onStart(app);
		Logger.info("Application has started");

		JPA.withTransaction(new play.libs.F.Callback0() {
			@SuppressWarnings("resource")
			@Override
			public void invoke() throws Throwable {
				Scanner in;
				in = new Scanner(new FileReader(new File("app/TiposDeEstilos.dat").getCanonicalPath()));
				while (in.hasNextLine()) {
					String nomeEstilo = in.nextLine();
					repositorioDeEstilos.persist(new Estilo(nomeEstilo));
				}

				repositorioDeEstilos.flush();

				/*
				 * Cadastrar instrumentos no Bando de Dados
				 */
				in = new Scanner(new FileReader(new File("app/TiposDeInstrumentos.dat").getCanonicalPath()));
				while (in.hasNextLine()) {
					String nomeInstrumentos = in.nextLine();
					repositorioDeInstrumentos.persist(new Instrumento(nomeInstrumentos));
				}

				repositorioDeInstrumentos.flush();
			}
		});
	}
	
	@Override
	public void onStop(Application app) {
		super.onStop(app);
		Logger.debug("Application on stop");
	}
}
