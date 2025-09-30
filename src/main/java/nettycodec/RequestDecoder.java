package nettycodec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import model.Request;
import model.RequestHeader;
import protocol.PrimitiveTypesReader;

import java.util.List;

public class RequestDecoder extends ReplayingDecoder<Request> {


    @Override
    protected void decode(ChannelHandlerContext ctx,
                          ByteBuf in, List<Object> out) throws Exception {

        var request = new Request();
        var messageSize = in.readInt();
        request.setMessageSize(messageSize);
        var requestHeader = new RequestHeader(in);
        request.setHeader(requestHeader);

        out.add(request);
    }
}
