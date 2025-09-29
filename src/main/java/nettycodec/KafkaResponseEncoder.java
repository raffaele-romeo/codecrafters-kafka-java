package nettycodec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import model.KafkaResponse;

public class KafkaResponseEncoder
        extends MessageToByteEncoder<KafkaResponse> {

    @Override
    protected void encode(ChannelHandlerContext ctx,
                          KafkaResponse msg, ByteBuf out) throws Exception {
        out.writeInt(msg.messageSize());
        out.writeInt(msg.header().correlationId());
    }
}