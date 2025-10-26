package api.describetopicpartitions;

import protocol.RawTaggedField;

public class DescribeTopicPartitionsHandler {
    public DescribeTopicPartitionsResponse handle(DescribeTopicPartitionsRequestBody request) {

        return new DescribeTopicPartitionsResponse(0, request.topicRequests(), (byte) -1, RawTaggedField.empty());
    }
}
