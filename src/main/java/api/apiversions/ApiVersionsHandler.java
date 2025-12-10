package api.apiversions;

import api.common.RequestHandler;
import model.ApiKey;
import model.ErrorCode;
import model.RequestContext;
import model.ResponseHeaderV0;

final public class ApiVersionsHandler extends RequestHandler<ApiVersionsRequest, ApiVersionsAbstractResponse> {
    @Override
    public ApiVersionsAbstractResponse handle(RequestContext requestContext, ApiVersionsRequest request) {
        ApiResponseVersionsResponseData apiResponseVersion;
        if (requestContext.apiVersion() != 4) {
            apiResponseVersion = new ApiResponseVersionsResponseData(ErrorCode.UNSUPPORTED_VERSION, new ApiKey[]{}, 0);
        } else {
            apiResponseVersion = new ApiResponseVersionsResponseData(ErrorCode.NONE, ApiKey.values(), 0);
        }

        return new ApiVersionsAbstractResponse(new ResponseHeaderV0(requestContext.correlationId()), apiResponseVersion);
    }
}