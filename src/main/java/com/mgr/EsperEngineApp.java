package com.mgr;

import com.mgr.json.config.JsonMessageFormatConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

/**
 * Glowna klasa programu z silnikiem Esper Odpowiada za uruchomienie kontenera
 * Spring
 *
 * @author michal
 */
public class EsperEngineApp {

    private static final Logger LOG = LoggerFactory
            .getLogger(EsperEngineApp.class);

    /**
     * Metoda main uruchamiajaca Springa i wskazujaca mu pakiety do skanowania
     *
     * @param args
     */
    public static void main(String[] args) {
        try (AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext()) {
            applicationContext.register(JsonMessageFormatConfig.class);
            applicationContext.scan("com.mgr.config",
                    "com.mgr.json.controller", "com.mgr.common",
                    "com.mgr.esper");
            applicationContext.refresh();
            start(applicationContext);
            applicationContext.registerShutdownHook();
            LOG.info("Esper Engine Started");
        } catch (Exception e) {
            LOG.info("Error: ", e);
            System.exit(1);
        }
    }

    /**
     * Metoda startujaca serwer aplikacyjny Jetty
     *
     * @param context
     * @throws Exception
     */
    private static void start(AnnotationConfigWebApplicationContext context)
            throws Exception {
        new JettyServer().start(context);
    }

}
