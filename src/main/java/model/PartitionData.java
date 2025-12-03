package model;

import io.netty.buffer.ByteBuf;
import protocol.CompactArray;
import protocol.RawTaggedField;

import java.util.List;

public record PartitionData(
        short errorCode,
        int partitionIndex,
        int leaderId,
        int leaderEpoch,
        List<Integer> replicaNodes,
        List<Integer> isrNodes,
        List<Integer> eligibleLeaderReplicas,
        List<Integer> lastKnownElr,
        List<Integer> offlineReplicas,
        RawTaggedField taggedFields
) {
    public void write(ByteBuf buf) {
        buf.writeShort(errorCode);
        buf.writeInt(partitionIndex);
        buf.writeInt(leaderId);
        buf.writeInt(leaderEpoch);
        CompactArray.write(buf, replicaNodes, ByteBuf::writeInt);
        CompactArray.write(buf, isrNodes, ByteBuf::writeInt);
        CompactArray.write(buf, eligibleLeaderReplicas, ByteBuf::writeInt);
        CompactArray.write(buf, lastKnownElr, ByteBuf::writeInt);
        CompactArray.write(buf, offlineReplicas, ByteBuf::writeInt);
        taggedFields.write(buf);
    }
}
