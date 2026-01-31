package model.record;

import io.netty.buffer.ByteBuf;
import protocol.CompactString;
import protocol.VarInt;

public record FeatureLevelRecord(byte frameVersion, byte version, String name,
                                 short featureLevel, int taggedFieldsCount) implements RecordValue {

    @Override
    public byte type() {
        return 12;
    }

    public static FeatureLevelRecord read(byte frameVersion, byte version, ByteBuf buf) {
        String name = CompactString.read(buf);
        short featureLevel = buf.readShort();
        int taggedFieldsCount = VarInt.readUnsigned(buf);

        return new FeatureLevelRecord(frameVersion, version, name, featureLevel, taggedFieldsCount);
    }

}
