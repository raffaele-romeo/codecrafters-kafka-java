package api.describetopicpartitions;

import api.common.RequestHandler;
import model.*;
import model.acl.AclOperation;
import model.header.RequestContext;
import model.header.ResponseHeaderV1;
import protocol.RawTaggedFields;
import service.ClusterMetadataReader;

import java.util.ArrayList;
import java.util.UUID;

final public class DescribeTopicPartitionsHandler extends RequestHandler<DescribeTopicPartitionsRequest, DescribeTopicPartitionsV0Response> {
    private final ClusterMetadataReader clusterMetadataReader;

    public DescribeTopicPartitionsHandler(ClusterMetadataReader clusterMetadataReader) {
        this.clusterMetadataReader = clusterMetadataReader;
    }

    @Override
    public DescribeTopicPartitionsV0Response handle(RequestContext requestContext, DescribeTopicPartitionsRequest request) {
        var metadataMap = clusterMetadataReader.loadMetadata();

        var topicResponses = request.getData().getTopicRequests().stream()
                .map(req -> metadataMap.getOrDefault(req.name(), unknownTopicResponse(req.name())))
                .sorted(Topic.BY_NAME)
                .toList();

        var data = new DescribeTopicPartitionsV0ResponseData(
                0,
                topicResponses, (byte) -1, RawTaggedFields.empty());

        return new DescribeTopicPartitionsV0Response(new ResponseHeaderV1(requestContext.correlationId(), RawTaggedFields.empty()), data);
    }

    private Topic unknownTopicResponse(String topicName) {
        return new Topic(
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
