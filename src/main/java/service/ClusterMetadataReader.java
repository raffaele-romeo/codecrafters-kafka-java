package service;

import io.netty.buffer.ByteBuf;
import model.ErrorCode;
import model.PartitionData;
import model.Topic;
import model.acl.AclOperation;
import model.record.*;
import model.record.Record;
import protocol.RawTaggedFields;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class ClusterMetadataReader {
    private final File metadataLogFile = new File("/tmp/kraft-combined-logs/__cluster_metadata-0/00000000000000000000.log");

    public Map<String, Topic> loadMetadata() {
        var buf = LogFileReader.read(metadataLogFile);

        try {
            List<RecordValue> allRecords = parseAllRecords(buf);

            Map<String, UUID> topicNameToId = allRecords.stream()
                    .filter(r -> r instanceof TopicRecordValue)
                    .map(r -> (TopicRecordValue) r)
                    .collect(Collectors.toMap(
                            TopicRecordValue::topicName,
                            TopicRecordValue::topicId,
                            (existing, replacement) -> replacement
                    ));

            Map<UUID, List<PartitionData>> partitionsByTopicId = allRecords.stream()
                    .filter(r -> r instanceof PartitionRecordValue)
                    .map(r -> (PartitionRecordValue) r)
                    .collect(Collectors.groupingBy(
                            PartitionRecordValue::topicId,
                            Collectors.mapping(this::mapToPartitionData, Collectors.toList())
                    ));


            return topicNameToId.entrySet().stream()
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            e -> new Topic(
                                    ErrorCode.NONE,
                                    e.getKey(),
                                    e.getValue(),
                                    false,
                                    partitionsByTopicId.getOrDefault(e.getValue(), List.of()),
                                    AclOperation.UNKNOWN,
                                    RawTaggedFields.empty()
                            )
                    ));
        } finally {
            buf.release();
        }
    }

    private List<RecordValue> parseAllRecords(ByteBuf buf) {
        List<RecordValue> recordValues = new ArrayList<>();
        var batch = RecordBatch.read(buf);
        while (batch.isPresent()) {
            batch.get().records().stream()
                    .map(Record::value)
                    .filter(Objects::nonNull)
                    .forEach(recordValues::add);
            batch = RecordBatch.read(buf);
        }
        return recordValues;
    }

    private PartitionData mapToPartitionData(PartitionRecordValue val) {
        return new PartitionData(
                ErrorCode.NONE,
                val.partitionId(),
                val.leaderId(),
                val.leaderEpoch(),
                val.replicas(),
                val.inSyncReplicas(),
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
                RawTaggedFields.empty()
        );
    }
}
