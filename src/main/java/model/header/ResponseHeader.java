package model.header;

import io.netty.buffer.ByteBuf;

public abstract class ResponseHeader {
    protected int correlationId;

    public ResponseHeader(int correlationId) {
        this.correlationId = correlationId;
    }

    public abstract void write(ByteBuf output);
}
