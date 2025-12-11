package protocol;

import io.netty.buffer.ByteBuf;

class RawTaggedField {
    private final int tag;
    private final ByteBuf data;

    RawTaggedField(int tag, ByteBuf data) {
        this.tag = tag;
        this.data = data;
    }

    int getTag() {
        return tag;
    }

    static RawTaggedField read(ByteBuf byteBuf) {
        int tag = UnsignedVarInt.read(byteBuf);
        int size = UnsignedVarInt.read(byteBuf);
        ByteBuf data = byteBuf.readBytes(size);

        return new RawTaggedField(tag, data);
    }

    void write(ByteBuf output) {
        UnsignedVarInt.write(output, tag);
        output.writeBytes(data);
    }


    @Override
    public String toString() {
        return "RawTaggedField{" +
                "tag=" + tag +
                ", data=" + data +
                '}';
    }
}
