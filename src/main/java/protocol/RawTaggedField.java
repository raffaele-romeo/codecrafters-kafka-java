package protocol;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class RawTaggedField {
    private final int tag;
    private final ByteBuf data;

    public RawTaggedField (int tag, ByteBuf data) {
        this.tag = tag;
        this.data = data;
    }

    public int getTag() {
        return tag;
    }

    public static RawTaggedField empty() {
        return new RawTaggedField(0, Unpooled.EMPTY_BUFFER);
    }

    public static RawTaggedField read (ByteBuf byteBuf) {
        int tag = UnsignedVarInt.read(byteBuf);
        int size = UnsignedVarInt.read(byteBuf);
        ByteBuf data = byteBuf.readBytes(size);

        return new RawTaggedField(tag, data);
    }

    public void write(ByteBuf byteBuf) {
        UnsignedVarInt.write(byteBuf, tag);
        UnsignedVarInt.write(byteBuf, data.readableBytes());
        byteBuf.writeBytes(data);
    }

    @Override
    public String toString() {
        return "RawTaggedField{" +
                "tag=" + tag +
                ", data=" + data +
                '}';
    }
}
