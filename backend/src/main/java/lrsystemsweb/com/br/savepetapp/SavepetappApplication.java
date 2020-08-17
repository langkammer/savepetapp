package lrsystemsweb.com.br.savepetapp;

import lrsystemsweb.com.br.savepetapp.core.CargaSistema;
import lrsystemsweb.com.br.savepetapp.core.io.StorageProperties;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.web.config.EnableSpringDataWebSupport;


@SpringBootApplication
@EnableSpringDataWebSupport
@EnableConfigurationProperties(StorageProperties.class)
public class SavepetappApplication {
	@Autowired
	private CargaSistema cargaSistema;

	private static final Logger LOG
			= Logger.getLogger(SavepetappApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(SavepetappApplication.class, args);
	}

	@EventListener
	public void onApplicationEvent(ContextRefreshedEvent event) {
		LOG.info("Carregando Sistema....");
		cargaSistema.cargaSistema();
	}

}
