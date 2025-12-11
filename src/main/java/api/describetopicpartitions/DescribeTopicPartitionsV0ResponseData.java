package api.describetopicpartitions;

import api.common.ApiResponseMessage;
import io.netty.buffer.ByteBuf;
import model.TopicResponse;
import protocol.CompactArray;
import protocol.UnsignedVarInt;

import java.util.List;

public record DescribeTopicPartitionsV0ResponseData(int throttleTime,
                                                    List<TopicResponse> topicResponses,
                                                    byte nextCursor)
        implements ApiResponseMessage {

    @Override
    public void write(ByteBuf output) {
        output.writeInt(throttleTime);
        CompactArray.write(output, topicResponses, (suppliedBuf, topicResponse) -> topicResponse.write(suppliedBuf));
        output.writeByte(nextCursor);
        UnsignedVarInt.write(output, 0);  //TODO Handle TAG_BUFFER
    }
}
