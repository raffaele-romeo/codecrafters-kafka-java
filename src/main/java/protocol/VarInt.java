package protocol;

import io.netty.buffer.ByteBuf;

public class VarInt {
    public static int readUnsigned(ByteBuf buf) {
        int value = 0;
        int shift = 0;

        while (shift < 32) {  // Max 5 bytes (5 * 7 = 35 bits, but we cap at 32 bit which is an int)
            byte b = buf.readByte();

            // The OR is needed here to compose the multiple bytes
            value |= (b & 0x7F) << shift;  // 0x7F = 01111111

            if ((b & 0x80) == 0) {  // 0x80 = 10000000
                return value; // No more bytes
            }

            shift += 7;
        }

        throw new IllegalStateException("Varint is too long");
    }

    public static void writeUnsigned(ByteBuf buf, int value) {
        while ((value & 0xFFFFFF80) != 0) {  // 0xFFFFFF80 = 11111111111111111111111110000000
            buf.writeByte((byte) ((value & 0x7F) | 0x80)); // 0x80 -> add 1 to signal another byte is needed
            value >>= 7; // value = value >> 7 (read 7 bits at time)
        }
        buf.writeByte((byte) (value & 0x7F));
    }
}
