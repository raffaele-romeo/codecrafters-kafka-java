package api.common;

import api.apiversions.ApiVersionsRequest;
import api.describetopicpartitions.DescribeTopicPartitionsRequest;
import io.netty.buffer.ByteBuf;
import model.ApiKey;

public abstract class AbstractRequest {
    private final ApiKey apiKey;

    public AbstractRequest(ApiKey apiKey) {
        this.apiKey = apiKey;
    }

    public ApiKey apiKey() {
        return this.apiKey;
    }

    public static AbstractRequest parseRequest(ApiKey apiKey, ByteBuf input) {
        return switch (apiKey) {
            case API_VERSIONS -> ApiVersionsRequest.parse(input);
            case DESCRIBE_TOPIC_PARTITIONS -> DescribeTopicPartitionsRequest.parse(input);
            case FETCH -> throw new UnsupportedOperationException();
        };
    }
}
