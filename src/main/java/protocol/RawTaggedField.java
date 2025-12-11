package protocol;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

class RawTaggedField {
    private final int tag;
    private final ByteBuf data;

    RawTaggedField (int tag, ByteBuf data) {
        this.tag = tag;
        this.data = data;
    }

    int getTag() {
        return tag;
    }

    static RawTaggedField empty() {
        return new RawTaggedField(0, Unpooled.EMPTY_BUFFER);
    }

    static RawTaggedField read (ByteBuf byteBuf) {
        int tag = UnsignedVarInt.read(byteBuf);
        int size = UnsignedVarInt.read(byteBuf);
        ByteBuf data = byteBuf.readBytes(size);

        return new RawTaggedField(tag, data);
    }


    @Override
    public String toString() {
        return "RawTaggedField{" +
                "tag=" + tag +
                ", data=" + data +
                '}';
    }
}
