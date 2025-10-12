package api.apiversions;

import api.ResponseBody;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import model.ApiKey;
import model.ErrorCode;

final public class ApiVersionsResponse implements ResponseBody {
    private static final int VERSION = 4;
    private final ApiResponseVersionData apiResponseVersionData;

    private ApiVersionsResponse(ApiResponseVersionData apiResponseVersionData) {
        this.apiResponseVersionData = apiResponseVersionData;
    }

    public static ApiVersionsResponse from(int version) {
        ApiResponseVersionData apiResponseVersionData;
        if (version != 4) {
            apiResponseVersionData = new ApiResponseVersionData(ErrorCode.UNSUPPORTED_VERSION.getCode(), new ApiKey[]{}, 0);
        } else {
            apiResponseVersionData = new ApiResponseVersionData(ErrorCode.NONE.getCode(), ApiKey.values(), 0);
        }

        return new ApiVersionsResponse(apiResponseVersionData);
    }

    @Override
    public ByteBuf serialize() {
        var result = Unpooled.buffer();
        try {
            result.writeShort(apiResponseVersionData.errorCode());
            int apiKeysLength = apiResponseVersionData.apiKeys().length;
            result.writeByte(apiKeysLength + 1);

            for (ApiKey apiKey : apiResponseVersionData.apiKeys()) {
                result.writeBytes(apiKey.serialize());
                result.writeByte(0); // tagged_fields for the response
            }

            result.writeInt(apiResponseVersionData.throttleTimeMs());
            result.writeByte(0); // tagged_fields for the response

            return result;
        } catch (Exception e) {
            result.release();
            throw e;
        }
    }

    @Override
    public int getVersion() {
        return VERSION;
    }

    @Override
    public String toString() {
        return "ApiVersionsResponse{" +
                "apiResponseVersionData=" + apiResponseVersionData.toString() +
                '}';
    }
}