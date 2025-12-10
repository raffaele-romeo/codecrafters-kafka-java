package model;

import io.netty.buffer.ByteBuf;
import protocol.NullableString;

public class RequestHeader {
    private final ApiKey apiKey;
    private final short apiVersion;
    private final int correlationId;
    private final String clientId;

    private RequestHeader(ApiKey apiKey, short apiVersion, int correlationId, String clientId) {
        this.apiKey = apiKey;
        this.apiVersion = apiVersion;
        this.correlationId = correlationId;
        this.clientId = clientId;
    }

    public static RequestHeader parse(ByteBuf buf) {
        var apiKey = buf.readShort();
        var apiVersion = buf.readShort();
        var correlationId = buf.readInt();
        var clientId = NullableString.read(buf);
        buf.readByte(); // Tag buffer - discarded for now

        return new RequestHeader(ApiKey.from(apiKey), apiVersion, correlationId, clientId);
    }

    public ApiKey getApiKey() {
        return apiKey;
    }


    public short getApiVersion() {
        return apiVersion;
    }

    public int getCorrelationId() {
        return correlationId;
    }

    @Override
    public String toString() {
        return "RequestHeader{" +
                "correlationId=" + correlationId +
                ", apiVersion=" + apiVersion +
                ", apiKey=" + apiKey +
                ", clientId=" + clientId +
                '}';
    }
}