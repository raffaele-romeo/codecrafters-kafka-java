package api.describetopicpartitions;

import api.common.AbstractResponse;
import model.header.ResponseHeaderV1;

public class DescribeTopicPartitionsV0Response extends AbstractResponse {
    public DescribeTopicPartitionsV0Response(ResponseHeaderV1 header, DescribeTopicPartitionsV0ResponseData body) {
        super(header, body);
    }
}
