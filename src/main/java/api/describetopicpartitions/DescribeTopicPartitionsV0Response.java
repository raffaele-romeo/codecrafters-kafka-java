package api.describetopicpartitions;

import model.Response;
import model.ResponseHeaderV1;

public class DescribeTopicPartitionsV0Response extends Response {
    public DescribeTopicPartitionsV0Response(ResponseHeaderV1 header, DescribeTopicPartitionsV0ResponseBody body) {
        super(header, body);
    }
}
