package api;

import api.apiversions.ApiVersionsResponseHandler;
import model.ApiKey;

public class ApiResponseHandler {
    public static ResponseBody handle(short apiKey, short apiVersion) {
        return switch (ApiKey.from(apiKey)) {
            case API_VERSIONS -> ApiVersionsResponseHandler.from(apiVersion);
        };
    }
}
