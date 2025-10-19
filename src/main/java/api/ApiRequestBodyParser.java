package api;

import api.apiversions.ApiVersionRequestBody;
import io.netty.buffer.ByteBuf;
import model.ApiKey;

public class ApiRequestBodyParser {
    public static RequestBody parse(short apiKey, ByteBuf input) {
        return switch (ApiKey.from(apiKey)) {
            case API_VERSIONS -> ApiVersionRequestBody.parse(input);
        };
    }
}
