package model;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

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

    public ByteBuf serialize() {
        var result = Unpooled.buffer();

        try {
            result.writeInt(correlationId);

            return result;
        } catch (Exception e) {
            result.release();
            throw e;
        }
    }

    @Override
    public String toString() {
        return "ResponseHeader{" +
                "correlationId=" + correlationId +
                '}';
    }
}