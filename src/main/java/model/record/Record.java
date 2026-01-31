package model.record;

import io.netty.buffer.ByteBuf;
import protocol.CompactString;
import protocol.VarInt;

public record Record(
        int length,
        byte attributes,
        int timestampDelta,
        int offsetDelta,
        String key,
        int valueLength,
        RecordValue value,
        int headersArrayCount
) {
    public static Record read(ByteBuf buf) {
        int length = VarInt.readUnsigned(buf);
        byte attributes = buf.readByte();
        int timestampDelta = VarInt.readUnsigned(buf);
        int offsetDelta = VarInt.readUnsigned(buf);
        var key = CompactString.read(buf);
        int valueLen = VarInt.readUnsigned(buf);

        byte frameVersion = buf.readByte();
        byte type = buf.readByte();
        byte version = buf.readByte();
        RecordValue value = RecordValue.read(frameVersion, version, type, buf);
        int headersArrayCount = VarInt.readUnsigned(buf);

        return new Record(length, attributes, timestampDelta, offsetDelta, key, valueLen, value, headersArrayCount);
    }
}
