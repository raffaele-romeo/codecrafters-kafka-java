package model;

import api.RequestBody;
import io.netty.buffer.ByteBuf;
import protocol.PrimitiveTypesReader;

public class RequestHeader {
    private short apiKey;
    private short apiVersion;
    private int correlationId;
    private String clientId;
    private RequestBody body;

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

        return new RequestHeader(apiKey, apiVersion, correlationId, clientId);
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public short getApiKey() {
        return apiKey;
    }

    public void setApiKey(short apiKey) {
        this.apiKey = apiKey;
    }

    public short getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(short apiVersion) {
        this.apiVersion = apiVersion;
    }

    public int getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(int correlationId) {
        this.correlationId = correlationId;
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