package api.describetopicpartitions;

import api.common.ApiResponseMessage;
import io.netty.buffer.ByteBuf;
import model.Topic;
import protocol.CompactArray;
import protocol.RawTaggedFields;

import java.util.List;

public record DescribeTopicPartitionsV0ResponseData(int throttleTime,
                                                    List<Topic> topics,
                                                    byte nextCursor,
                                                    RawTaggedFields taggedFields)
        implements ApiResponseMessage {

    @Override
    public void write(ByteBuf output) {
        output.writeInt(throttleTime);
        CompactArray.write(output, topics, (suppliedBuf, topic) -> topic.write(suppliedBuf));
        output.writeByte(nextCursor);
        taggedFields.write(output);
    }
}
