package protocol;

import io.netty.buffer.ByteBuf;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class RawTaggedFields {
    private final Map<Integer, RawTaggedField> fields;

    public RawTaggedFields(Map<Integer, RawTaggedField> fields) {
        this.fields = fields;
    }

    public static RawTaggedFields read(ByteBuf buf) {
        int numFields = UnsignedVarInt.read(buf);

        if (numFields == 0) {
            return empty();
        }

        Map<Integer, RawTaggedField> fields = new HashMap<>();
        for (int i = 0; i < numFields; i++) {
            RawTaggedField field = RawTaggedField.read(buf);
            fields.put(field.getTag(), field);
        }

        return new RawTaggedFields(fields);
    }

    public static RawTaggedFields empty() {
        return new RawTaggedFields(Map.of());
    }

    @Override
    public String toString() {
        return "RawTaggedFields{" +
                "fields=" + fields +
                '}';
    }
}