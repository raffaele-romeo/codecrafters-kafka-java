package api.describetopicpartitions;

import api.common.RequestHandler;
import io.netty.buffer.ByteBufUtil;
import model.*;
import model.record.*;
import model.acl.AclOperation;
import model.header.RequestContext;
import model.header.ResponseHeaderV1;
import model.record.Record;
import protocol.RawTaggedFields;
import service.LogFileReader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

final public class DescribeTopicPartitionsHandler extends RequestHandler<DescribeTopicPartitionsRequest, DescribeTopicPartitionsV0Response> {
    @Override
    public DescribeTopicPartitionsV0Response handle(RequestContext requestContext, DescribeTopicPartitionsRequest request) {
        var file = new File("/tmp/kraft-combined-logs/__cluster_metadata-0/00000000000000000000.log");
        var buf = LogFileReader.read(file);

       System.out.println(ByteBufUtil.prettyHexDump(buf));

        try {
            var recordBatches = new ArrayList<RecordBatch>();
            var recordBatch = RecordBatch.read(buf);

            while (recordBatch.isPresent()) {
                System.out.println("RecordBatch is: " + recordBatch.get());
                recordBatches.add(recordBatch.get());
                recordBatch = RecordBatch.read(buf);
            }

            var topicResponse = request.getData().getTopicRequests().stream().map(topicRequest ->
                    makeTopicResponse(topicRequest.name(), recordBatches)
            ).toList();

            var data = new DescribeTopicPartitionsV0ResponseData(
                    0,
                    topicResponse, (byte) -1, RawTaggedFields.empty());

            return new DescribeTopicPartitionsV0Response(new ResponseHeaderV1(requestContext.correlationId(), RawTaggedFields.empty()), data);
        } finally {
            buf.release();
        }
    }

    private TopicResponse makeTopicResponse(String topicName, List<RecordBatch> recordBatches) {
        var topicRecordValues = getRecordValue(recordBatches, TopicRecordValue.class);
        var partitionRecordValue = getRecordValue(recordBatches, PartitionRecordValue.class);

        var topicIds = topicRecordValues.stream().filter(value -> value.topicName().equals(topicName))
                .map(TopicRecordValue::topicId)
                .toList();

        if (topicIds.isEmpty()) {
            return unknownTopicResponse(topicName);
        } else {
            var partitions = partitionRecordValue.stream().filter(value -> value.topicId().equals(topicIds.getFirst()))
                    .map(value -> new PartitionData(
                            ErrorCode.NONE,
                            value.partitionId(),
                            value.leaderId(),
                            value.leaderEpoch(),
                            value.replicas(),
                            value.inSyncReplicas(),
                            new ArrayList<>(),
                            new ArrayList<>(),
                            new ArrayList<>(),
                            RawTaggedFields.empty()
                    )).toList();

            return new TopicResponse(
                    ErrorCode.NONE,
                    topicName,
                    topicIds.getFirst(),
                    false,
                    partitions,
                    AclOperation.UNKNOWN,
                    RawTaggedFields.empty()
            );
        }
    }

    private <T extends RecordValue> List<T> getRecordValue(List<RecordBatch> recordBatches, Class<T> type) {
        var records = recordBatches.stream().map(RecordBatch::records)
                .flatMap(List::stream)
                .toList();

        return records.stream().map(Record::value)
                .filter(Objects::nonNull)
                .filter(type::isInstance)
                .map(type::cast)
                .toList();
    }

    private TopicResponse unknownTopicResponse(String topicName) {
        return new TopicResponse(
                ErrorCode.UNKNOWN_TOPIC_OR_PARTITION,
                topicName,
                UUID.fromString("00000000-0000-0000-0000-000000000000"),
                false,
                new ArrayList<>(),
                AclOperation.UNKNOWN,
                RawTaggedFields.empty()
        );
    }
}
