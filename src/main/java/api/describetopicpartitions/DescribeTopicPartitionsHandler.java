package api.describetopicpartitions;

import api.common.RequestHandler;
import model.*;
import model.acl.AclOperation;
import model.header.RequestContext;
import model.header.ResponseHeaderV1;
import protocol.RawTaggedField;

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
                        AclOperation.UNKNOWN
                )
        ).toList();

        var data = new DescribeTopicPartitionsV0ResponseData(
                0,
                topicResponse, (byte) -1);

        return new DescribeTopicPartitionsV0Response(new ResponseHeaderV1(requestContext.correlationId()), data);
    }
}
