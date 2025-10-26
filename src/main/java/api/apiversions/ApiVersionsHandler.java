package api.apiversions;

import model.ApiKey;
import model.ErrorCode;

final public class ApiVersionsHandler  {
    public static ApiVersionsResponse handle(int version) {
        ApiVersionsResponse apiResponseVersion;
        if (version != 4) {
            apiResponseVersion = new ApiVersionsResponse(ErrorCode.UNSUPPORTED_VERSION.getCode(), new ApiKey[]{}, 0);
        } else {
            apiResponseVersion = new ApiVersionsResponse(ErrorCode.NONE.getCode(), ApiKey.values(), 0);
        }

        return apiResponseVersion;
    }
}