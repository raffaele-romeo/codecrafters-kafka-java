package nettycodec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import model.Request;
import model.RequestHeader;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class RequestDecoder extends ReplayingDecoder<Request> {

    private final Charset charset = StandardCharsets.UTF_8;

    @Override
    protected void decode(ChannelHandlerContext ctx,
                          ByteBuf in, List<Object> out) throws Exception {

        var messageSize = in.readInt();
        var requestHeader = new RequestHeader(in.readShort(), in.readShort(), in.readInt());
        var request = new Request(messageSize, requestHeader);
        out.add(request);
    }
}
