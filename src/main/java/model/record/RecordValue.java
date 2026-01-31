package model.record;

import io.netty.buffer.ByteBuf;

import java.util.List;
import java.util.Objects;

public sealed interface RecordValue permits TopicRecordValue, PartitionRecordValue, FeatureLevelRecord {
    byte frameVersion();

    byte type();

    int taggedFieldsCount();

    static RecordValue read(byte frameVersion, byte version, byte type, ByteBuf buf) {
        return switch (type) {
            case 12 -> FeatureLevelRecord.read(frameVersion, version, buf);
            case 2 -> TopicRecordValue.read(frameVersion, version, buf);
            case 3 -> PartitionRecordValue.read(frameVersion, version, buf);
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
    }
}
