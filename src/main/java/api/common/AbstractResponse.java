package api.common;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import model.ResponseHeader;

public abstract class AbstractResponse {
    protected final ResponseHeader header;
    protected final ApiResponseMessage message;

    protected AbstractResponse(ResponseHeader header, ApiResponseMessage message) {
        this.header = header;
        this.message = message;
    }

    public ByteBuf serialize() {
        ByteBuf headerBuf = Unpooled.buffer();
        ByteBuf bodyBuf = Unpooled.buffer();

        try {
            header.write(headerBuf);
            message.write(bodyBuf);

            int totalSize = headerBuf.readableBytes() + bodyBuf.readableBytes();

            ByteBuf response = Unpooled.buffer(totalSize);
            response.writeBytes(headerBuf);
            response.writeBytes(bodyBuf);

            return response;
        } finally {
            if (headerBuf != null) headerBuf.release();
            if (bodyBuf != null) bodyBuf.release();
        }
    }

    @Override
    public String toString() {
        return "Response{" +
                "header=" + header.toString() +
                ", message=" + message.toString() +
                '}';
    }
}