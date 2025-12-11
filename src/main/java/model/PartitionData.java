package model;

import io.netty.buffer.ByteBuf;
import protocol.CompactArray;
import protocol.RawTaggedFields;
import protocol.UnsignedVarInt;

import java.util.List;

public record PartitionData(
        ErrorCode errorCode,
        int partitionIndex,
        int leaderId,
        int leaderEpoch,
        List<Integer> replicaNodes,
        List<Integer> isrNodes,
        List<Integer> eligibleLeaderReplicas,
        List<Integer> lastKnownElr,
        List<Integer> offlineReplicas,
        RawTaggedFields taggedFields
) {
    public void write(ByteBuf output) {
        errorCode.write(output);
        output.writeInt(partitionIndex);
        output.writeInt(leaderId);
        output.writeInt(leaderEpoch);
        CompactArray.write(output, replicaNodes, ByteBuf::writeInt);
        CompactArray.write(output, isrNodes, ByteBuf::writeInt);
        CompactArray.write(output, eligibleLeaderReplicas, ByteBuf::writeInt);
        CompactArray.write(output, lastKnownElr, ByteBuf::writeInt);
        CompactArray.write(output, offlineReplicas, ByteBuf::writeInt);
        taggedFields.write(output);
    }
}
