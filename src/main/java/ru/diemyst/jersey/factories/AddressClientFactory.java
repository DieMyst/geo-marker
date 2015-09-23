package ru.diemyst.jersey.factories;

import org.glassfish.hk2.api.Factory;
import ru.diemyst.jersey.client.AddressClientImpl;

/**
 * Created by diemyst on 19.09.15.
 */
public class AddressClientFactory implements Factory<AddressClientImpl> {
    @Override
    public AddressClientImpl provide() {
        return AddressClientImpl.getInstance();
    }

    @Override
    public void dispose(AddressClientImpl instance) {

    }
}
