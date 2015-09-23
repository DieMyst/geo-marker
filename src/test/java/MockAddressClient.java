import ru.diemyst.entities.google.Geometry;
import ru.diemyst.entities.google.GoogleResponse;
import ru.diemyst.entities.google.Location;
import ru.diemyst.entities.google.Result;
import ru.diemyst.jersey.client.AddressClient;

/**
 * Created by diemyst on 23.09.15.
 */
public class MockAddressClient implements AddressClient {
    @Override
    public GoogleResponse getGoogleResponse(String link, String path, String address) {
        if (address.equals("fault")) {
            return newResponse("NOT_OK");
        } else {
            return newResponse("OK");
        }
    }

    private GoogleResponse newResponse(String status) {
        GoogleResponse response = new GoogleResponse();
        response.setStatus(status);

        Result[] results = new Result[1];
        Result result = new Result();
        Geometry geometry = new Geometry();
        Location location = new Location();
        location.setLat("10.0");
        location.setLng("22.2");
        geometry.setLocation(location);
        result.setGeometry(geometry);
        results[0] = result;
        response.setResults(results);

        return response;
    }
}
