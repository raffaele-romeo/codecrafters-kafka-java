package model;

import io.netty.buffer.ByteBuf;
import protocol.RawTaggedField;
import protocol.RawTaggedFields;

public class ResponseHeaderV1 extends ResponseHeader {
    private final RawTaggedField rawTaggedField;

    public ResponseHeaderV1(int correlationId, RawTaggedField rawTaggedField) {
        super(correlationId);
        this.rawTaggedField = rawTaggedField;
    }

    @Override
    public void write(ByteBuf output) {
        output.writeInt(correlationId);
        rawTaggedField.write(output);
    }

    @Override
    public String toString() {
        return "ResponseHeader{" +
                "correlationId=" + correlationId +
                '}';
    }
}