package protocol;

import io.netty.buffer.ByteBuf;
import model.Reader;
import model.Writer;

import java.util.ArrayList;
import java.util.List;

public class CompactArray {
    //Represents a sequence of objects of a given type T. Type T can be either a primitive type (e.g. STRING) or a structure.
    // First, the length N + 1 is given as an UNSIGNED_VARINT. Then N instances of type T follow.
    // A null array is represented with a length of 0. In protocol documentation an array of T instances is referred to as [T].
    public static <T> List<T> read(ByteBuf buf, Reader<T> reader) {
        if (buf == null || buf.readableBytes() < 1) {
            return null;
        }
        var lengthPlusOne = UnsignedVarInt.read(buf);

        if (lengthPlusOne == 0) {
            return null;
        }

        int length = lengthPlusOne - 1;

        var array = new ArrayList<T>();

        for (int i = 0; i < length; i++) {
            array.add(reader.read(buf));
        }

        return array;
    }

    public static <T> void write(ByteBuf buf, List<T> list, Writer<T> writer) {
        if (list == null) {
            UnsignedVarInt.write(buf, 0);
            return;
        }

        UnsignedVarInt.write(buf, list.size() + 1);

        for (T value : list) {
            writer.write(buf, value);
        }
    }
}


