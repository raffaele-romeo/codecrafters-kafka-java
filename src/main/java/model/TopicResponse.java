package model;

import java.util.UUID;

public record TopicResponse(
        UUID topicId,
        String name,
        boolean isInternal,
        List<PartitionData> partitions,
        int authorizedOperations,
        RawTaggedFields taggedFields
) {}