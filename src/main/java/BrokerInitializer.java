import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import nettycodec.RequestDecoder;
import nettycodec.ResponseEncoder;


public class BrokerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel channel) {
        ChannelPipeline pipeline = channel.pipeline();

        pipeline.addLast(new RequestDecoder(), new ResponseEncoder(), new RequestHandler());
    }
}
