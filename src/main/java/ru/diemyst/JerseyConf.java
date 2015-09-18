package ru.diemyst;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Created by diemyst on 18.09.15.
 */
public class JerseyConf extends ResourceConfig {

    public JerseyConf() {
        register(JacksonFeature.class);
        register(ObjectMapperProvider.class);
    }

}
