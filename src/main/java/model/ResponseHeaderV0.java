package model;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class ResponseHeaderV0 extends ResponseHeader {
    public ResponseHeaderV0(int correlationId) {
        super(correlationId);
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