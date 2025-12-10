package model;

import io.netty.buffer.ByteBuf;
import model.acl.AclOperation;
import protocol.*;

import java.util.List;
import java.util.UUID;

public record TopicResponse(
        ErrorCode errorCode,
        String name,
        UUID topicId,
        boolean isInternal,
        List<PartitionData> partitions,
        AclOperation authorizedOperations,
        RawTaggedField taggedField
) {
    public void write(ByteBuf buf) {
        buf.writeShort(errorCode.getCode());
        CompactString.write(buf, name);
        UUIDOps.writeUuid(buf, topicId);
        buf.writeBoolean(isInternal);
        CompactArray.write(buf, partitions,
                (suppliedBuf, value) -> value.write(suppliedBuf));
        buf.writeByte(authorizedOperations.code());
        buf.writeByte(0);
    }
}