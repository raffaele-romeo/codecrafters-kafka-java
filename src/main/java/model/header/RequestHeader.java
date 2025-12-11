package model.header;

import io.netty.buffer.ByteBuf;
import model.ApiKey;
import protocol.NullableString;
import protocol.RawTaggedFields;

public class RequestHeader {
    private final ApiKey apiKey;
    private final short apiVersion;
    private final int correlationId;
    private final String clientId;

    private RequestHeader(ApiKey apiKey, short apiVersion, int correlationId, String clientId, RawTaggedFields taggedFields) {
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
        var taggedFields = RawTaggedFields.read(buf);

        return new RequestHeader(ApiKey.from(apiKey), apiVersion, correlationId, clientId, taggedFields);
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