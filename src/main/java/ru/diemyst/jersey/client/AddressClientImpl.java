package ru.diemyst.jersey.client;

import org.glassfish.jersey.client.JerseyClientBuilder;
import org.glassfish.jersey.jackson.JacksonFeature;
import ru.diemyst.ObjectMapperProvider;
import ru.diemyst.entities.google.GoogleResponse;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 * Created by diemyst on 18.09.15.
 */
public class AddressClientImpl implements AddressClient {
    private static AddressClientImpl instance;
    private static Client client;

    public static AddressClientImpl getInstance() {
        AddressClientImpl localInstance = instance;
        if (localInstance == null) {
            synchronized (AddressClientImpl.class) {
                localInstance = instance;
                if (localInstance == null) {

                    client = JerseyClientBuilder.newBuilder()
                            .register(JacksonFeature.class)
                            .register(ObjectMapperProvider.class)
                            .build();

                    instance = localInstance = new AddressClientImpl();
                }
            }
        }
        return localInstance;
    }

    private AddressClientImpl() {
    }

    public GoogleResponse getGoogleResponse(String link, String path, String address) {
        WebTarget target = client.target(link).path(path).queryParam("address", address);
        return target.request(MediaType.APPLICATION_JSON_TYPE).get(GoogleResponse.class);

    }
}
