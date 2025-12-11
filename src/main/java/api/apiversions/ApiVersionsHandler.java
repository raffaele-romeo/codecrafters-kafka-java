package api.apiversions;

import api.common.RequestHandler;
import model.ApiKey;
import model.ErrorCode;
import model.header.RequestContext;
import model.header.ResponseHeaderV0;
import protocol.RawTaggedFields;

import java.util.ArrayList;
import java.util.Arrays;

final public class ApiVersionsHandler extends RequestHandler<ApiVersionsRequest, ApiVersionsResponse> {
    @Override
    public ApiVersionsResponse handle(RequestContext requestContext, ApiVersionsRequest request) {
        ApiResponseVersionsResponseData apiResponseVersion;
        if (requestContext.apiVersion() != 4) {
            apiResponseVersion = new ApiResponseVersionsResponseData(ErrorCode.UNSUPPORTED_VERSION, new ArrayList<>(), 0, RawTaggedFields.empty());
        } else {
            apiResponseVersion = new ApiResponseVersionsResponseData(ErrorCode.NONE, Arrays.stream(ApiKey.values()).toList(), 0, RawTaggedFields.empty());
        }

        return new ApiVersionsResponse(new ResponseHeaderV0(requestContext.correlationId()), apiResponseVersion);
    }
}