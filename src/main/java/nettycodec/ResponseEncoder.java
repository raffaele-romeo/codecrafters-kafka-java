package nettycodec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import model.Response;

public class ResponseEncoder
        extends MessageToByteEncoder<Response> {

    @Override
    protected void encode(ChannelHandlerContext ctx,
                          Response response, ByteBuf out) throws Exception {
        response.writeTo(out);
    }
}