package api.apiversions;

import api.ResponseBody;
import model.ErrorCode;

public class ApiVersionsResponseHandler {
    public static ResponseBody from(int version) {
        if (version != 4) {
            return new ApiVersionsResponseV4(ErrorCode.UNSUPPORTED_VERSION);
        }

        return new ApiVersionsResponseV4(ErrorCode.NONE);
    }
}