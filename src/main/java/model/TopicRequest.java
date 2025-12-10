package model;

import io.netty.buffer.ByteBuf;
import protocol.CompactString;
import protocol.RawTaggedFields;

public record TopicRequest(String name, RawTaggedFields taggedField) {

    public static TopicRequest read(ByteBuf buf) {
        var name = CompactString.read(buf);
        var taggedField = RawTaggedFields.read(buf);

        return new TopicRequest(name, taggedField);
    }
}
