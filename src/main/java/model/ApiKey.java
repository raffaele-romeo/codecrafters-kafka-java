package model;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.util.Arrays;

public enum ApiKey {
    API_VERSIONS(18, 0, 4);

    private final short key;
    private final short minSupportedApiVersion;
    private final short maxSupportedApiVersion;

    ApiKey(int key, int minSupportedApiVersion, int maxSupportedApiVersion) {
        this.key = (short) key;
        this.minSupportedApiVersion = (short) minSupportedApiVersion;
        this.maxSupportedApiVersion = (short) maxSupportedApiVersion;
    }

    public static ApiKey from(short apiKey) {
        return Arrays.stream(values()).filter(apiKeys -> apiKeys.key == apiKey)
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Api Key %s is undefined".formatted(apiKey)));
    }

    public ByteBuf serialize() {
        var result = Unpooled.buffer();
        try {
            result.writeShort(key);
            result.writeShort(minSupportedApiVersion);
            result.writeShort(maxSupportedApiVersion);

            return result;
        } catch (Exception e) {
            result.release();
            throw e;
        }
    }

    public short getKey() {
        return key;
    }
}
