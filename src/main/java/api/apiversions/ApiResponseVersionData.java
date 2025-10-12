package api.apiversions;

import model.ApiKey;

import java.util.Arrays;

public record ApiResponseVersionData(short errorCode, ApiKey[] apiKeys, int throttleTimeMs) {

    @Override
    public String toString() {
        return "ApiResponseVersionData{" +
                "errorCode=" + errorCode +
                ", apiKeys=" + Arrays.toString(apiKeys) +
                ", throttleTimeMs=" + throttleTimeMs +
                '}';
    }
}