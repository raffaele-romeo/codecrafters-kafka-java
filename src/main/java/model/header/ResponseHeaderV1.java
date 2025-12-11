package model.header;

import io.netty.buffer.ByteBuf;
import protocol.RawTaggedFields;

public class ResponseHeaderV1 extends ResponseHeader {
    RawTaggedFields taggedFields;
    public ResponseHeaderV1(int correlationId, RawTaggedFields taggedFields) {
        super(correlationId);
        this.taggedFields = taggedFields;
    }

    @Override
    public void write(ByteBuf output) {
        output.writeInt(correlationId);
        taggedFields.write(output);
    }

    @Override
    public String toString() {
        return "ResponseHeaderV1{" +
                "correlationId=" + correlationId +
                '}';
    }
}