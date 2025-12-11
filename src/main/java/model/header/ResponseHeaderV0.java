package model.header;

import io.netty.buffer.ByteBuf;

public class ResponseHeaderV0 extends ResponseHeader {
    public ResponseHeaderV0(int correlationId) {
        super(correlationId);
    }

    public void write(ByteBuf output) {
        output.writeInt(correlationId);
    }

    @Override
    public String toString() {
        return "ResponseHeader{" +
                "correlationId=" + correlationId +
                '}';
    }
}