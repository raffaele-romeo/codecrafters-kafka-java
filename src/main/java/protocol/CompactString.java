package protocol;

import io.netty.buffer.ByteBuf;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class CompactString {
    private final static Charset CHARSET = StandardCharsets.UTF_8;

    /*
        Represents a sequence of characters. First the length N + 1 is given as an UNSIGNED_VARINT .
        Then N bytes follow which are the UTF-8 encoding of the character sequence.
     */
    public static String read(ByteBuf buf) {
        if (buf == null || buf.readableBytes() < 1) {
            return null;
        }
        var lengthPlusOne = VarInt.readUnsigned(buf);

        if (lengthPlusOne == 0) {
            return null;
        }

        int length = lengthPlusOne - 1;

        if (length == 0) {
            return "";
        }

        if (buf.readableBytes() < length) {
            throw new IllegalStateException(
                    String.format("Not enough bytes to read string. Expected: %d, available: %d",
                            length, buf.readableBytes())
            );
        }

        return buf.readCharSequence(length, CHARSET).toString();
    }

    public static void write(ByteBuf buf, String value) {
        int length;

        if (value == null) {
            length = 0;
        } else {
            length = value.length() + 1;
        }

        VarInt.writeUnsigned(buf, length);

        buf.writeCharSequence(value, CHARSET);
    }


}
