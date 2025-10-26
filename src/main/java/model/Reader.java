package model;

import io.netty.buffer.ByteBuf;

@FunctionalInterface
public interface Reader<T> {
    T read(ByteBuf buf);
}
