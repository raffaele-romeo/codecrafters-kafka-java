package model.record;

import io.netty.buffer.ByteBuf;
import protocol.CompactArray;
import protocol.UUIDOps;
import protocol.VarInt;

import java.util.List;
import java.util.UUID;

public record PartitionRecordValue(byte frameVersion,
                                   byte type,
                                   int partitionId,
                                   UUID topicId,
                                   List<Integer> replicas,
                                   List<Integer> inSyncReplicas,
                                   List<Integer> removingReplicas,
                                   List<Integer> addingReplicas,
                                   int leaderId,
                                   int leaderEpoch,
                                   int partitionEpoch,
                                   List<UUID> directoriesArray,
                                   int taggedFieldsCount) implements RecordValue {

    @Override
    public byte type() {
        return 3;
    }

    public static PartitionRecordValue read(byte frameVersion, byte version, ByteBuf buf) {
        int partitionId = buf.readInt();
        UUID topicId = UUIDOps.readUuid(buf);
        List<Integer> replicaArray = CompactArray.read(buf, ByteBuf::readInt);
        List<Integer> inSyncReplicasArray = CompactArray.read(buf, ByteBuf::readInt);
        List<Integer> removingReplicas = CompactArray.read(buf, ByteBuf::readInt);
        List<Integer> addingReplicas = CompactArray.read(buf, ByteBuf::readInt);
        int leaderId = buf.readInt();
        int leaderEpoch = buf.readInt();
        int partitionEpoch = buf.readInt();
        List<UUID> directoriesArray = CompactArray.read(buf, UUIDOps::readUuid);
        int taggedFieldsCount = VarInt.readUnsigned(buf);

        return new PartitionRecordValue(frameVersion, version, partitionId, topicId,
                replicaArray, inSyncReplicasArray, removingReplicas,
                addingReplicas, leaderId, leaderEpoch, partitionEpoch,
                directoriesArray, taggedFieldsCount);
    }
}
