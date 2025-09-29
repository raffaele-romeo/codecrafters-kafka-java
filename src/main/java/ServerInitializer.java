import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import nettycodec.KafkaResponseEncoder;


public class ServerInitializer extends ChannelInitializer<Channel> {
    @Override
    protected void initChannel(Channel channel) {
        ChannelPipeline pipeline = channel.pipeline();

        pipeline.addLast(new KafkaResponseEncoder(), new ProcessingHandler());
    }
}
