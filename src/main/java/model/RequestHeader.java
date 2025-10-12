package model;

import api.RequestBody;
import io.netty.buffer.ByteBuf;

public class RequestHeader {
    private short requestApiKey;
    private short requestApiVersion;
    private int correlationId;
    private String clientId;
    private RequestBody body;

    private RequestHeader(short apiKey, short apiVersion, int correlationId) {
        this.requestApiKey = apiKey;
        this.requestApiVersion = apiVersion;
        this.correlationId = correlationId;
    }

    public static RequestHeader parse(ByteBuf buf) {
        var apiKey = buf.readShort();
        var apiVersion = buf.readShort();
        var correlationId = buf.readInt();

        return new RequestHeader(apiKey, apiVersion, correlationId);
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public short getRequestApiKey() {
        return requestApiKey;
    }

    public void setRequestApiKey(short requestApiKey) {
        this.requestApiKey = requestApiKey;
    }

    public short getRequestApiVersion() {
        return requestApiVersion;
    }

    public void setRequestApiVersion(short requestApiVersion) {
        this.requestApiVersion = requestApiVersion;
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
                ", requestApiVersion=" + requestApiVersion +
                ", requestApiKey=" + requestApiKey +
                '}';
    }
}