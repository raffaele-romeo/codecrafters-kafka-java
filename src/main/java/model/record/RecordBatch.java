package model.record;

import io.netty.buffer.ByteBuf;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

public record RecordBatch(
        long baseOffset,
        int batchLength,
        int partitionLeaderEpoch,
        byte magic,
        int crc,
        short attributes,
        int lastOffsetDelta,
        long baseTimestamp,
        long maxTimestamp,
        long producerId,
        short producerEpoch,
        int baseSequence,
        int recordsLength,
        List<model.record.Record> records
) {

    public static final int RECORD_BATCH_HEADER_SIZE = 61;

    public static Optional<RecordBatch> read(ByteBuf buf) {
        if (buf.readableBytes() < RECORD_BATCH_HEADER_SIZE) {
            return Optional.empty();
        }

        long baseOffset = buf.readLong();
        int batchLength = buf.readInt();
        int partitionLeaderEpoch = buf.readInt();
        byte magic = buf.readByte();
        int crc = buf.readInt();
        short attributes = buf.readShort();
        int lastOffsetDelta = buf.readInt();
        long firstTimestamp = buf.readLong();
        long maxTimestamp = buf.readLong();
        long producerId = buf.readLong();
        short producerEpoch = buf.readShort();
        int baseSequence = buf.readInt();
        int recordCount = buf.readInt();

        List<model.record.Record> records = new ArrayList<>(recordCount);
        for (int i = 0; i < recordCount; i++) {
            var record = Record.read(buf);
            records.add(record);
        }

        return Optional.of(new RecordBatch(
                baseOffset, batchLength, partitionLeaderEpoch, magic, crc,
                attributes, lastOffsetDelta, firstTimestamp, maxTimestamp,
                producerId, producerEpoch, baseSequence, recordCount, records
        ));
    }
}