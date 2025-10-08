package model;

import java.util.Arrays;

public enum ApiKey {
    API_VERSIONS(18);

    private final short key;

    ApiKey(int key) {
        this.key = (short) key;
    }

    public static ApiKey from(short apiKey) {
        return Arrays.stream(values()).filter(apiKeys -> apiKeys.key == apiKey)
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Api Key %s is undefined".formatted(apiKey)));
    }

    public short getKey() {
        return key;
    }


}
