package model;

import io.netty.buffer.ByteBuf;

public class ResponseHeader {
    private int correlationId;

    public ResponseHeader(int correlationId) {
        this.correlationId = correlationId;
    }

    public int getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(int correlationId) {
        this.correlationId = correlationId;
    }

    public void writeTo(ByteBuf byteBuf) {
        byteBuf.writeInt(correlationId);
    }
}