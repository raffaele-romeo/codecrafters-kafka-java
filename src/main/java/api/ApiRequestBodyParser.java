package api;

import api.apiversions.ApiVersionsRequestBody;
import api.describetopicpartitions.DescribeTopicPartitionsRequestBody;
import io.netty.buffer.ByteBuf;
import model.ApiKey;

public class ApiRequestBodyParser {
    public static RequestBody parse(short apiKey, ByteBuf input) {
        return switch (ApiKey.from(apiKey)) {
            case API_VERSIONS -> ApiVersionsRequestBody.parse(input);
            case DESCRIBE_TOPIC_PARTITIONS -> DescribeTopicPartitionsRequestBody.parse(input);
        };
    }
}
