package api;

import io.netty.buffer.ByteBuf;

public interface ResponseBody {
    byte[] serialize();
    int getVersion();

    default void writeTo (ByteBuf byteBuf) {
        byteBuf.writeBytes(serialize());
    }
}
