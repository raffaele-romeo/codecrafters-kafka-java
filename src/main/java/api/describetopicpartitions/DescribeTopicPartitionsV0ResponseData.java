package api.describetopicpartitions;

import api.common.ApiResponseMessage;
import io.netty.buffer.ByteBuf;
import model.TopicResponse;
import protocol.CompactArray;
import protocol.RawTaggedField;

import java.util.List;

public record DescribeTopicPartitionsV0ResponseData(int throttleTime,
                                                    List<TopicResponse> topicResponses,
                                                    byte nextCursor,
                                                    RawTaggedField taggedField)
        implements ApiResponseMessage {

    @Override
    public void write(ByteBuf output) {
        output.writeInt(throttleTime);
        CompactArray.write(output, topicResponses, (suppliedBuf, topicResponse) -> topicResponse.write(suppliedBuf));
        output.writeByte(nextCursor);
        taggedField.write(output);
    }
}
