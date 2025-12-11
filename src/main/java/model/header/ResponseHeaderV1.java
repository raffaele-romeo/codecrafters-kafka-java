package model.header;

import io.netty.buffer.ByteBuf;
import protocol.UnsignedVarInt;

public class ResponseHeaderV1 extends ResponseHeader {

    public ResponseHeaderV1(int correlationId) {
        super(correlationId);
    }

    @Override
    public void write(ByteBuf output) {
        output.writeInt(correlationId);
        UnsignedVarInt.write(output, 0);  // TAG_BUFFER
    }

    @Override
    public String toString() {
        return "ResponseHeaderV1{" +
                "correlationId=" + correlationId +
                '}';
    }
}