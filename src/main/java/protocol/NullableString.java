package protocol;

import io.netty.buffer.ByteBuf;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class NullableString {
    private final static Charset CHARSET = StandardCharsets.UTF_8;

    /*
        Represents a sequence of characters or null. For non-null strings, first the length N is given as an INT16.
        Then N bytes follow which are the UTF-8 encoding of the character sequence.
        A null value is encoded with length of -1 and there are no following bytes.
     */
    public static String read(ByteBuf buf) {
        if (buf == null || buf.readableBytes() < 2) {
            return null;
        }


        var length = buf.readShort();

        if (length == -1) {
            return null;
        }

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

}
