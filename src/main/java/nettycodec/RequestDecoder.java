package nettycodec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import model.Request;

import java.util.List;

public class RequestDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        // Check if we have at least 4 bytes for message size
        if (in.readableBytes() < 4) {
            return;  // Wait for more data
        }

        // Mark position in case we need to reset
        in.markReaderIndex();

        int messageSize = in.readInt();

        // Check if we have the complete message
        if (in.readableBytes() < messageSize) {
            in.resetReaderIndex();  // Not enough data, reset and wait
            return;
        }

        Request request = Request.parse(messageSize, in);

        System.out.println(request);
        out.add(request);
    }
}
