package model;

import io.netty.buffer.ByteBuf;
import protocol.PrimitiveTypesReader;

public class RequestHeader {
    private short requestApiKey;
    private short requestApiVersion;
    private int correlationId;
    private String clientId;

    public RequestHeader(ByteBuf buf) {
        this.requestApiKey = buf.readShort();
        this.requestApiVersion = buf.readShort();
        this.correlationId = buf.readInt();
        //this.clientId = PrimitiveTypesReader.readNullableString(buf);
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