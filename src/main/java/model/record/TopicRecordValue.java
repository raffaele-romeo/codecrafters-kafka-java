package model.record;

import io.netty.buffer.ByteBuf;
import protocol.CompactString;
import protocol.UUIDOps;
import protocol.VarInt;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public record TopicRecordValue(byte frameVersion,
                               byte version,
                               String topicName,
                               UUID topicId,
                               int taggedFieldsCount) implements RecordValue {
    @Override
    public byte type() {
        return 2;
    }

    public static TopicRecordValue read(byte frameVersion, byte version, ByteBuf buf) {
        String topicName = CompactString.read(buf);
        UUID topicId = UUIDOps.readUuid(buf);
        int taggedFieldsCount = VarInt.readUnsigned(buf);

        return new TopicRecordValue(frameVersion, version, topicName, topicId, taggedFieldsCount);
    }

}
