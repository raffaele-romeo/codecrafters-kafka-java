package api.describetopicpartitions;

import api.ResponseHandler;
import model.*;
import protocol.RawTaggedField;
import protocol.RawTaggedFields;

import java.util.ArrayList;
import java.util.UUID;

final public class DescribeTopicPartitionsHandler extends ResponseHandler<DescribeTopicPartitionsRequestBody, DescribeTopicPartitionsV0Response> {
    @Override
    public DescribeTopicPartitionsV0Response handle(RequestContext requestContext, DescribeTopicPartitionsRequestBody request) {

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

        var responseBody = new DescribeTopicPartitionsV0ResponseBody(
                0,
                topicResponse, (byte) -1, RawTaggedFields.empty());

        return new DescribeTopicPartitionsV0Response(new ResponseHeaderV1(requestContext.correlationId(), RawTaggedFields.empty()), responseBody);
    }
}
