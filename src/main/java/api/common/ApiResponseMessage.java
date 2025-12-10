package api.common;

import io.netty.buffer.ByteBuf;

public interface ApiResponseMessage {
    void write(ByteBuf output);
}
