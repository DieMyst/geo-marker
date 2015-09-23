package ru.diemyst;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletProperties;
import ru.diemyst.jersey.binders.AddressClientBinder;
import ru.diemyst.jersey.binders.PointDaoBinder;
import ru.diemyst.jersey.resources.AddressResource;
import ru.diemyst.jersey.resources.PointsResource;

/**
 * Created by diemyst on 18.09.15.
 */
public class JerseyConf extends ResourceConfig {

    public JerseyConf() {
        register(JacksonFeature.class);
        property(ServletProperties.FILTER_STATIC_CONTENT_REGEX, "*");
        property(ServletProperties.FILTER_FORWARD_ON_404, true);
        register(new AddressClientBinder());
        register(new PointDaoBinder());
        register(PointsResource.class);
        register(AddressResource.class);
        register(CustomExceptionMapper.class);
        register(new LoggingFilter());

        register(ObjectMapperProvider.class);

    }

}
