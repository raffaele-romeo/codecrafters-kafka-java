package nettycodec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import api.common.AbstractResponse;

public class ResponseEncoder
        extends MessageToByteEncoder<AbstractResponse> {

    @Override
    protected void encode(ChannelHandlerContext ctx,
                          AbstractResponse response, ByteBuf out) {
        ByteBuf responseBytes = null;

        try {
            responseBytes = response.serialize();
            int messageSize = responseBytes.readableBytes();

            out.writeInt(messageSize);
            out.writeBytes(responseBytes);
        } finally {
            if (responseBytes != null) {
                responseBytes.release();
            }
        }
    }
}