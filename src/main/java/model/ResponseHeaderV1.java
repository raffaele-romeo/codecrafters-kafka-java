package model;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import protocol.RawTaggedFields;

public class ResponseHeaderV1 extends ResponseHeader {
    private final RawTaggedFields rawTaggedFields;

    public ResponseHeaderV1(int correlationId, RawTaggedFields rawTaggedFields) {
        super(correlationId);
        this.rawTaggedFields = rawTaggedFields;
    }

    public ByteBuf serialize() {
        var result = Unpooled.buffer();

        try {
            result.writeInt(correlationId);
            rawTaggedFields.write(result);

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