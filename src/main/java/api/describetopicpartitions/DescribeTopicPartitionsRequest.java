package api.describetopicpartitions;

import api.common.AbstractRequest;
import io.netty.buffer.ByteBuf;
import model.ApiKey;

public class DescribeTopicPartitionsRequest extends AbstractRequest {
    private final DescribeTopicPartitionsRequestData data;

    private DescribeTopicPartitionsRequest(DescribeTopicPartitionsRequestData data) {
        super(ApiKey.DESCRIBE_TOPIC_PARTITIONS);
        this.data = data;
    }

    public DescribeTopicPartitionsRequestData getData() {
        return data;
    }

    public static DescribeTopicPartitionsRequest parse(ByteBuf input) {
        return new DescribeTopicPartitionsRequest(new DescribeTopicPartitionsRequestData(input));
    }


}
