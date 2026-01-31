package model;

import io.netty.buffer.ByteBuf;
import model.acl.AclOperation;
import protocol.*;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public record Topic(
        ErrorCode errorCode,
        String name,
        UUID topicId,
        boolean isInternal,
        List<PartitionData> partitions,
        AclOperation authorizedOperations,
        RawTaggedFields taggedFields
) {
    public static final Comparator<Topic> BY_NAME =
            Comparator.comparing(Topic::name);

    public void write(ByteBuf output) {
        errorCode.write(output);
        CompactString.write(output, name);
        UUIDOps.writeUuid(output, topicId);
        output.writeBoolean(isInternal);
        CompactArray.write(output, partitions,
                (suppliedBuf, value) -> value.write(suppliedBuf));
        authorizedOperations.write(output);
        taggedFields.write(output);
    }
}