import api.apiversions.ApiVersionsHandler;
import api.common.ApiRegistry;
import api.describetopicpartitions.DescribeTopicPartitionsHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import model.ApiKey;
import nettycodec.RequestDecoder;
import nettycodec.ResponseEncoder;


public class BrokerInitializer extends ChannelInitializer<SocketChannel> {
    private final ApiRegistry apiRegistry;

    public BrokerInitializer() {
        this.apiRegistry = new ApiRegistry();
        apiRegistry.add(ApiKey.API_VERSIONS, new ApiVersionsHandler());
        apiRegistry.add(ApiKey.DESCRIBE_TOPIC_PARTITIONS, new DescribeTopicPartitionsHandler());
    }

    @Override
    protected void initChannel(SocketChannel channel) {
        ChannelPipeline pipeline = channel.pipeline();

        pipeline.addLast(new RequestDecoder(), new ResponseEncoder(), new RequestProcessor(apiRegistry));
    }
}
