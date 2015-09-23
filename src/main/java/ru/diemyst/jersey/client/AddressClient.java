package ru.diemyst.jersey.client;

import ru.diemyst.entities.google.GoogleResponse;

/**
 * Created by diemyst on 23.09.15.
 */
public interface AddressClient {

    GoogleResponse getGoogleResponse(String link, String path, String address);

}
