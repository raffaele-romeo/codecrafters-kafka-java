package model;

import io.netty.buffer.ByteBuf;
import protocol.PrimitiveTypesReader;

public class RequestHeader {
    private final short apiKey;
    private final short apiVersion;
    private final int correlationId;
    private final String clientId;

    private RequestHeader(short apiKey, short apiVersion, int correlationId, String clientId) {
        this.apiKey = apiKey;
        this.apiVersion = apiVersion;
        this.correlationId = correlationId;
        this.clientId = clientId;
    }

    public static RequestHeader parse(ByteBuf buf) {
        var apiKey = buf.readShort();
        var apiVersion = buf.readShort();
        var correlationId = buf.readInt();
        var clientId = PrimitiveTypesReader.readNullableString(buf);
        buf.readByte(); // Tag buffer - discarded for now

        return new RequestHeader(apiKey, apiVersion, correlationId, clientId);
    }

    public short getApiKey() {
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