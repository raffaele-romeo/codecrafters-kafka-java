package model;

import io.netty.buffer.ByteBuf;
import protocol.RawTaggedField;
import protocol.CompactString;

public record TopicRequest(String name, RawTaggedField taggedField) {

    public static TopicRequest read(ByteBuf buf) {
        var name = CompactString.read(buf);
        var taggedField = RawTaggedField.read(buf);

        return new TopicRequest(name, taggedField);
    }

    public static void write(ByteBuf buf, TopicRequest value) {
        CompactString.write(buf, value.name);
        buf.writeByte(0);
    }
}
