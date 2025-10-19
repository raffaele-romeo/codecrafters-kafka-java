package protocol;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class PrimitiveTypesReader {
    private final static Charset CHARSET = StandardCharsets.UTF_8;

    /*
        Represents a sequence of characters or null. For non-null strings, first the length N is given as an INT16.
        Then N bytes follow which are the UTF-8 encoding of the character sequence.
        A null value is encoded with length of -1 and there are no following bytes.
     */
    public static String readNullableString(ByteBuf buf) {
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

    /*
        Represents a sequence of characters. First the length N + 1 is given as an UNSIGNED_VARINT .
        Then N bytes follow which are the UTF-8 encoding of the character sequence.
     */
    public static String readCompactString(ByteBuf buf) {
        if(buf == null || buf.readableBytes() < 1) {
            return null;
        }
        var lengthPlusOne = readUnsignedVarint(buf);

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

    public static int readUnsignedVarint(ByteBuf buf) {
        int value = 0;
        int shift = 0;

        while (shift < 32) {  // Max 5 bytes (5 * 7 = 35 bits, but we cap at 32)
            byte b = buf.readByte();


            value |= (b & 0x7F) << shift;

            if ((b & 0x80) == 0) {
                return value;
            }

            shift += 7;
        }

        throw new IllegalStateException("Varint is too long");
    }
}
