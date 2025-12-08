package api.apiversions;

import api.ResponseHandler;
import model.ApiKey;
import model.ErrorCode;
import model.RequestContext;
import model.ResponseHeaderV0;

final public class ApiVersionsHandler extends ResponseHandler<ApiVersionsRequestBody, ApiVersionsResponse> {
    @Override
    protected ApiVersionsResponse handle(RequestContext requestContext, ApiVersionsRequestBody requestBody) {
        ApiVersionsResponseBody apiResponseVersion;
        if (requestContext.apiVersion() != 4) {
            apiResponseVersion = new ApiVersionsResponseBody(ErrorCode.UNSUPPORTED_VERSION, new ApiKey[]{}, 0);
        } else {
            apiResponseVersion = new ApiVersionsResponseBody(ErrorCode.NONE, ApiKey.values(), 0);
        }

        return new ApiVersionsResponse(new ResponseHeaderV0(requestContext.correlationId()), apiResponseVersion);
    }
}