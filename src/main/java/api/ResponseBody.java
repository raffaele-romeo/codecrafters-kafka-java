package api;

import io.netty.buffer.ByteBuf;

public interface ResponseBody {
    ByteBuf serialize();
    int getVersion();
}
