package service;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.io.File;
import java.io.RandomAccessFile;

public class LogFileReader {
    public static ByteBuf read(File file) {
        ByteBuf buffer = Unpooled.buffer((int) file.length());
        try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
            buffer.writeBytes(raf.getChannel(), 0L, (int) file.length());
            return buffer;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
