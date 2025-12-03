package model;

import io.netty.buffer.ByteBuf;

@FunctionalInterface
public interface IOSupplier<T> {
    T read(ByteBuf buf);
}
