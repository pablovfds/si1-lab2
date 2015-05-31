

import java.util.List;

import models.constant.TiposDeInstrumentos;
import models.entity.Estilo;
import models.entity.Instrumento;
import models.repository.RepositorioDeInstrumentos;
import models.constant.TiposDeEstilos;
import models.repository.RepositorioDeEstilos;
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
			@Override
			public void invoke() throws Throwable {
				try {
					estilos = repositorioDeEstilos.findAll();
					if(estilos.size() == 0) {
						for(TiposDeEstilos style : TiposDeEstilos.values()) {
							repositorioDeEstilos.persist(new Estilo(style.getDescricao()));
						}
						repositorioDeEstilos.flush();
					}
					
					instrumentos = repositorioDeInstrumentos.findAll();
					if(instrumentos.size() == 0) {
						for(TiposDeInstrumentos instrument : TiposDeInstrumentos.values()) {
							repositorioDeInstrumentos.persist(new Instrumento(instrument.getDescription()));
						}
						repositorioDeInstrumentos.flush();
					}
				} catch (Exception e) {
					Logger.debug(e.getMessage());
				}
			}
		});
	}
	
	@Override
	public void onStop(Application app) {	
		super.onStop(app);
		
		JPA.withTransaction(new play.libs.F.Callback0() {
			@Override
			public void invoke() throws Throwable {
				Logger.info("Application shutdown");
				try {
					estilos = repositorioDeEstilos.findAll();
					for(Estilo estilo : estilos) {
						repositorioDeEstilos.removeById(estilo.getId());
					}
					
					instrumentos = repositorioDeInstrumentos.findAll();
					for(Instrumento instrumento : instrumentos) {
						repositorioDeInstrumentos.removeById(instrumento.getId());
					}
				} catch (Exception e) {
					Logger.debug("Problem in finalizing: " + e.getMessage());
				}
			}
		});
	}
}
