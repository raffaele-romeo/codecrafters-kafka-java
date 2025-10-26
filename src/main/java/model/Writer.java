package model;

import io.netty.buffer.ByteBuf;

@FunctionalInterface
public interface Writer<T> {
    void write(ByteBuf buf, T value);
}
