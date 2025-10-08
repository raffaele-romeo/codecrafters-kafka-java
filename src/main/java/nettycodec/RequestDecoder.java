package nettycodec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import model.Request;

import java.util.List;

public class RequestDecoder extends ReplayingDecoder<Request> {


    @Override
    protected void decode(ChannelHandlerContext ctx,
                          ByteBuf in, List<Object> out) throws Exception {

        var request = Request.from(in);
        out.add(request);
    }
}
