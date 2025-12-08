package api.apiversions;

import model.Response;
import model.ResponseHeaderV0;

public class ApiVersionsResponse extends Response {
    protected ApiVersionsResponse(ResponseHeaderV0 header, ApiVersionsResponseBody body) {
        super(header, body);
    }
}
