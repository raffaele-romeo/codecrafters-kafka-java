package model;

import io.netty.buffer.ByteBuf;
import protocol.RawTaggedFields;

import java.util.Arrays;

public enum ApiKey {
    API_VERSIONS(18, 0, 4),
    FETCH(1, 0, 16),
    DESCRIBE_TOPIC_PARTITIONS(75, 0, 0);

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

    public void write(ByteBuf output) {
        output.writeShort(key);
        output.writeShort(minSupportedApiVersion);
        output.writeShort(maxSupportedApiVersion);
        RawTaggedFields.empty().write(output);
    }

    public short getKey() {
        return key;
    }

    @Override
    public String toString() {
        return "ApiKey{" +
                "key=" + key +
                ", minSupportedApiVersion=" + minSupportedApiVersion +
                ", maxSupportedApiVersion=" + maxSupportedApiVersion +
                '}';
    }
}
