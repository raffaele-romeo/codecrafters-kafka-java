package model;

import io.netty.buffer.ByteBuf;

@FunctionalInterface
public interface IOConsumer<T> {
    void accept(ByteBuf buf, T value);
}
