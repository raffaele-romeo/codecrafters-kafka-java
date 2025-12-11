package api.apiversions;

import api.common.ApiResponseMessage;
import api.common.AbstractResponse;
import model.header.ResponseHeaderV0;

public class ApiVersionsResponse extends AbstractResponse {
    protected ApiVersionsResponse(ResponseHeaderV0 header, ApiResponseMessage message) {
        super(header, message);
    }
}
