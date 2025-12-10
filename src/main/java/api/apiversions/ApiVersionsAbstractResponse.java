package api.apiversions;

import api.common.ApiResponseMessage;
import api.common.AbstractResponse;
import model.ResponseHeaderV0;

public class ApiVersionsAbstractResponse extends AbstractResponse {
    protected ApiVersionsAbstractResponse(ResponseHeaderV0 header, ApiResponseMessage message) {
        super(header, message);
    }
}
