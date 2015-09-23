package ru.diemyst.jersey.binders;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import ru.diemyst.jersey.client.AddressClient;
import ru.diemyst.jersey.factories.AddressClientFactory;

/**
 * Created by diemyst on 19.09.15.
 */
public class AddressClientBinder extends AbstractBinder {

    @Override
    protected void configure() {
        bindFactory(AddressClientFactory.class).to(AddressClient.class);
    }
}
