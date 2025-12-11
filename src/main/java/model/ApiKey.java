package model;

import io.netty.buffer.ByteBuf;
import protocol.UnsignedVarInt;

import java.util.Arrays;

public enum ApiKey {
    API_VERSIONS(18, 0, 4),
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
        UnsignedVarInt.write(output, 0); //TODO Handle TAG_BUFFER
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
