package ru.diemyst.client;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.glassfish.jersey.jackson.JacksonFeature;
import ru.diemyst.ObjectMapperProvider;

import javax.ws.rs.client.Client;

/**
 * Created by diemyst on 18.09.15.
 */
public class AddressClient {
    private static Client instance;

    public static Client getInstance() {
        Client localInstance = instance;
        if (localInstance == null) {
            synchronized (AddressClient.class) {
                localInstance = instance;
                if (localInstance == null) {

                    instance = localInstance = JerseyClientBuilder.newBuilder()
                            .register(JacksonFeature.class)
                            .register(ObjectMapperProvider.class)
                            .build();
                }
            }
        }
        return localInstance;
    }

    private AddressClient() {
    }
}
