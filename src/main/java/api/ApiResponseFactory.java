package api;

import api.apiversions.ApiVersionsResponse;
import model.ApiKey;

public class ApiResponseFactory {
    public static ResponseBody make(short apiKey, short apiVersion) {
        return switch (ApiKey.from(apiKey)) {
            case API_VERSIONS -> ApiVersionsResponse.make(apiVersion);
        };
    }
}
