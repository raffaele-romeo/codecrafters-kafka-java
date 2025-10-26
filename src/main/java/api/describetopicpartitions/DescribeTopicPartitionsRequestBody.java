package api.describetopicpartitions;

import api.RequestBody;
import io.netty.buffer.ByteBuf;
import model.TopicRequest;
import protocol.CompactArray;
import protocol.RawTaggedField;

import java.util.List;

public record DescribeTopicPartitionsRequestBody(List<TopicRequest> topicRequests, int numberOfPartitions, byte cursor,
                                                 RawTaggedField taggedField)
        implements RequestBody {

    public static DescribeTopicPartitionsRequestBody parse(ByteBuf input) {
        var topics = CompactArray.read(input, TopicRequest::read);
        var numberOfPartitions = input.readInt();
        var cursor = input.readByte();
        var taggedField = RawTaggedField.read(input);

        return new DescribeTopicPartitionsRequestBody(topics, numberOfPartitions, cursor, taggedField);
    }
}
