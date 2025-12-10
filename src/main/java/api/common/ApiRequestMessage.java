package api.common;

import io.netty.buffer.ByteBuf;

public interface ApiRequestMessage {
    void read(ByteBuf input);
}
