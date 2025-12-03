package api;

import api.apiversions.ApiVersionsHandler;
import api.describetopicpartitions.DescribeTopicPartitionsHandler;
import api.describetopicpartitions.DescribeTopicPartitionsRequestBody;
import model.ApiKey;
import model.Request;

public class ApiResponseHandler {
    public static ResponseBody handle(Request request) {
        return switch (ApiKey.from(request.getHeader().getApiKey())) {
            case API_VERSIONS -> ApiVersionsHandler.handle(request.getHeader().getApiVersion());
            case DESCRIBE_TOPIC_PARTITIONS ->
                    DescribeTopicPartitionsHandler.handle((DescribeTopicPartitionsRequestBody) request.getBody());
        };
    }
}
