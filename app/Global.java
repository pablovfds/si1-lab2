import models.constant.InstrumentType;
import models.constant.StyleType;
import models.entity.Anuncio;
import models.entity.Estilo;
import models.entity.Instrumento;
import models.repository.RepositorioDeAnuncios;
import models.repository.RepositorioDeEstilos;
import models.repository.RepositorioDeInstrumentos;
import play.Application;
import play.GlobalSettings;
import play.Logger;
import play.db.jpa.JPA;

import java.util.List;

public class Global extends GlobalSettings {

	@Override
	public void onStart(Application app) {
		super.onStart(app);

		Logger.info("Application has started");

		JPA.withTransaction(new play.libs.F.Callback0() {
			@Override
			public void invoke() throws Throwable {
				try {
					RepositorioDeEstilos repositorioDeEstilos = RepositorioDeEstilos.getInstance();
					List<Estilo> estilos = repositorioDeEstilos.findAll();
					if(estilos.size() == 0) {
						for(StyleType style : StyleType.values()) {
							repositorioDeEstilos.persist(new Estilo(style.getDescription()));

						}
						repositorioDeEstilos.flush();
					}

					RepositorioDeInstrumentos repositorioDeInstrumentos = RepositorioDeInstrumentos.getInstance();
					List<Instrumento> instrumentos = repositorioDeInstrumentos.findAll();
					if(instrumentos.size() == 0) {
						for(InstrumentType instrumento : InstrumentType.values()) {
							repositorioDeInstrumentos.persist(new Instrumento(instrumento.getDescription()));
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

		final RepositorioDeEstilos styleRepository = RepositorioDeEstilos.getInstance();
		final RepositorioDeAnuncios posterRepository = RepositorioDeAnuncios.getInstance();
		final RepositorioDeInstrumentos instrumentRepository = RepositorioDeInstrumentos.getInstance();

		JPA.withTransaction(new play.libs.F.Callback0() {
			@Override
			public void invoke() throws Throwable {
				Logger.info("Application shutdown");
				try {
					List<Anuncio> adverts = posterRepository.findAll();
					for (Anuncio poster : adverts) {
						posterRepository.removeById(poster.getId());
					}

					List<Estilo> styles = styleRepository.findAll();
					for (Estilo style : styles) {
						styleRepository.removeById(style.getId());
					}

					List<Instrumento> instruments = instrumentRepository.findAll();
					for (Instrumento instrument : instruments) {
						instrumentRepository.removeById(instrument.getId());
					}
				} catch (Exception e) {
					Logger.debug("Problem in finalizing: " + e.getMessage());
				}
			}
		});
	}
}
