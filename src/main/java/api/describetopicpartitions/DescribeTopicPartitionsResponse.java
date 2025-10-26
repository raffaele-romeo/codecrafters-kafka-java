package api.describetopicpartitions;

import api.ResponseBody;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import model.TopicRequest;
import model.TopicResponse;
import protocol.CompactArray;
import protocol.RawTaggedField;

import java.util.List;

public record DescribeTopicPartitionsResponse(int throttleTime, List<TopicResponse> topicResponses, byte nextCursor, RawTaggedField taggedField)
        implements ResponseBody {

    @Override
    public ByteBuf serialize() {
        var result = Unpooled.buffer();
        try {
            result.writeInt(throttleTime);
            CompactArray.write(result, topicResponses, TopicRequest::write);
            result.writeByte(nextCursor);
            taggedField.write(result);

            return result;
        } catch (Exception e) {
            result.release();
            throw e;
        }
    }
}
