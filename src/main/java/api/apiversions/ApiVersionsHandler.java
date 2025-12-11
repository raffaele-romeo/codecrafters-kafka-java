package api.apiversions;

import api.common.RequestHandler;
import model.ApiKey;
import model.ErrorCode;
import model.header.RequestContext;
import model.header.ResponseHeaderV0;

final public class ApiVersionsHandler extends RequestHandler<ApiVersionsRequest, ApiVersionsResponse> {
    @Override
    public ApiVersionsResponse handle(RequestContext requestContext, ApiVersionsRequest request) {
        ApiResponseVersionsResponseData apiResponseVersion;
        if (requestContext.apiVersion() != 4) {
            apiResponseVersion = new ApiResponseVersionsResponseData(ErrorCode.UNSUPPORTED_VERSION, new ApiKey[]{}, 0);
        } else {
            apiResponseVersion = new ApiResponseVersionsResponseData(ErrorCode.NONE, ApiKey.values(), 0);
        }

        return new ApiVersionsResponse(new ResponseHeaderV0(requestContext.correlationId()), apiResponseVersion);
    }
}