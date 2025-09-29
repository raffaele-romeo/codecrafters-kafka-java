import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import nettycodec.RequestDecoder;
import nettycodec.ResponseEncoder;


public class BrokerInitializer extends ChannelInitializer<Channel> {
    @Override
    protected void initChannel(Channel channel) {
        ChannelPipeline pipeline = channel.pipeline();

        pipeline.addLast(new RequestDecoder(), new ResponseEncoder(), new RequestHandler());
    }
}
