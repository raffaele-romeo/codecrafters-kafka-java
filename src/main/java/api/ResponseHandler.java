package api;

import model.RequestContext;
import model.Response;

public abstract class ResponseHandler<REQ extends RequestBody, RES extends Response>  {
    protected abstract RES handle(RequestContext requestContext, REQ requestBody);
}
    /*
    public static ResponseBody handle(Request request) {
        return switch (ApiKey.from(request.getHeader().getApiKey())) {
            case API_VERSIONS -> ApiVersionsHandler.handle(request.getHeader().getApiVersion());
            case DESCRIBE_TOPIC_PARTITIONS ->
                    DescribeTopicPartitionsHandler.handle((DescribeTopicPartitionsRequestBody) request.getBody());
        };
    }
     */
