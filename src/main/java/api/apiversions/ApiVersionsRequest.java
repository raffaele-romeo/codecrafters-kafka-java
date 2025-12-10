package api.apiversions;

import api.common.AbstractRequest;
import io.netty.buffer.ByteBuf;
import model.ApiKey;

public class ApiVersionsRequest extends AbstractRequest {
    private final ApiResponseVersionsRequestData data;

    private ApiVersionsRequest(ApiResponseVersionsRequestData data) {
        super(ApiKey.API_VERSIONS);
        this.data = data;
    }

    public ApiResponseVersionsRequestData getData() {
        return data;
    }

    public static ApiVersionsRequest parse(ByteBuf input) {
        return new ApiVersionsRequest(new ApiResponseVersionsRequestData(input));
    }


}
