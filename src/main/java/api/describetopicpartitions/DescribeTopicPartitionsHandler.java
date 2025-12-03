package api.describetopicpartitions;

import model.AclOperation;
import model.ErrorCode;
import model.TopicResponse;
import protocol.RawTaggedField;
import protocol.RawTaggedFields;

import java.util.ArrayList;
import java.util.UUID;

public class DescribeTopicPartitionsHandler {
    public static DescribeTopicPartitionsV0Response handle(DescribeTopicPartitionsRequestBody request) {

        var topicResponse = request.topicRequests().stream().map(topicRequest -> new TopicResponse(
                        ErrorCode.UNKNOWN_TOPIC_OR_PARTITION,
                        topicRequest.name(),
                        UUID.fromString("00000000-0000-0000-0000-000000000000"),
                        false,
                        new ArrayList<>(),
                        AclOperation.UNKNOWN,
                        RawTaggedField.empty()
                )
        ).toList();

        return new DescribeTopicPartitionsV0Response(
                0,
                topicResponse, (byte) -1, RawTaggedFields.empty());
    }


}
