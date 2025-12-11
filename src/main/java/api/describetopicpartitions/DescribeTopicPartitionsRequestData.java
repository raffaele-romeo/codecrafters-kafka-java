package api.describetopicpartitions;

import api.common.ApiRequestMessage;
import io.netty.buffer.ByteBuf;
import model.TopicRequest;
import protocol.CompactArray;
import protocol.RawTaggedFields;

import java.util.List;

public class DescribeTopicPartitionsRequestData implements ApiRequestMessage {
    List<TopicRequest> topicRequests;
    int numberOfPartitions;
    byte cursor;
    RawTaggedFields taggedFields;

    public DescribeTopicPartitionsRequestData(ByteBuf input) {
        this.read(input);
    }

    public final void read(ByteBuf input) {
        this.topicRequests = CompactArray.read(input, TopicRequest::read);
        this.numberOfPartitions = input.readInt();
        this.cursor = input.readByte();
        this.taggedFields = RawTaggedFields.read(input);
    }

    public RawTaggedFields getTaggedField() {
        return taggedFields;
    }

    public byte getCursor() {
        return cursor;
    }

    public int getNumberOfPartitions() {
        return numberOfPartitions;
    }

    public List<TopicRequest> getTopicRequests() {
        return topicRequests;
    }

    @Override
    public String toString() {
        return "DescribeTopicPartitionsRequestData{" +
                "topicRequests=" + topicRequests +
                ", numberOfPartitions=" + numberOfPartitions +
                ", cursor=" + cursor +
                ", taggedFields=" + taggedFields +
                '}';
    }
}
