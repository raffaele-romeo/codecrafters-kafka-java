package api.describetopicpartitions;

import api.common.RequestHandler;
import model.*;
import model.acl.AclOperation;
import protocol.RawTaggedField;
import protocol.RawTaggedFields;

import java.util.ArrayList;
import java.util.UUID;

final public class DescribeTopicPartitionsHandler extends RequestHandler<DescribeTopicPartitionsRequest, DescribeTopicPartitionsV0Response> {
    @Override
    public DescribeTopicPartitionsV0Response handle(RequestContext requestContext, DescribeTopicPartitionsRequest request) {

        var topicResponse = request.getData().getTopicRequests().stream().map(topicRequest -> new TopicResponse(
                        ErrorCode.UNKNOWN_TOPIC_OR_PARTITION,
                        topicRequest.name(),
                        UUID.fromString("00000000-0000-0000-0000-000000000000"),
                        false,
                        new ArrayList<>(),
                        AclOperation.UNKNOWN,
                        RawTaggedField.empty()
                )
        ).toList();

        var responseBody = new DescribeTopicPartitionsV0ResponseData(
                0,
                topicResponse, (byte) -1, RawTaggedField.empty());

        return new DescribeTopicPartitionsV0Response(new ResponseHeaderV1(requestContext.correlationId(), RawTaggedField.empty()), responseBody);
    }
}
