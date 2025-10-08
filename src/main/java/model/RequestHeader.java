package model;

import io.netty.buffer.ByteBuf;

public class RequestHeader {
    private short requestApiKey;
    private short requestApiVersion;
    private int correlationId;
    private String clientId;

    private RequestHeader(short requestApiKey, short requestApiVersion, int correlationId) {
        this.requestApiKey = requestApiKey;
        this.requestApiVersion = requestApiVersion;
        this.correlationId = correlationId;
    }

    public static RequestHeader from(ByteBuf buf) {
        var requestApiKey = buf.readShort();
        var requestApiVersion = buf.readShort();
        var correlationId = buf.readInt();

        return new RequestHeader(requestApiKey, requestApiVersion, correlationId);
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
}