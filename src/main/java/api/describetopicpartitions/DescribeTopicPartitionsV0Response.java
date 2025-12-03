package api.describetopicpartitions;

import api.ResponseBody;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import model.TopicResponse;
import protocol.CompactArray;
import protocol.RawTaggedFields;

import java.util.List;

public record DescribeTopicPartitionsV0Response(int throttleTime,
                                                List<TopicResponse> topicResponses,
                                                byte nextCursor,
                                                RawTaggedFields taggedFields)
        implements ResponseBody {

    @Override
    public ByteBuf serialize() {
        var result = Unpooled.buffer();
        try {
            result.writeInt(throttleTime);
            CompactArray.write(result, topicResponses, (suppliedBuf, topicResponse) -> topicResponse.write(suppliedBuf));
            result.writeByte(nextCursor);
            taggedFields.write(result);

            return result;
        } catch (Exception e) {
            result.release();
            throw e;
        }
    }
}
