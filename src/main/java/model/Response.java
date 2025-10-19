package model;

import api.ApiResponseFactory;
import api.ResponseBody;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class Response {
    private final ResponseHeader header;
    private final ResponseBody body;

    private Response(ResponseHeader header, ResponseBody body) {
        this.header = header;
        this.body = body;
    }

    public static Response handle(Request request) {
        var requestHeader = request.getHeader();
        var responseHeader = new ResponseHeader(requestHeader.getCorrelationId());
        var responseBody = ApiResponseFactory.make(requestHeader.getApiKey(), requestHeader.getApiVersion());

        return new Response(responseHeader, responseBody);
    }

    public ByteBuf serialize() {
        ByteBuf headerBuf = null;
        ByteBuf bodyBuf = null;

        try {
            headerBuf = header.serialize();
            bodyBuf = body.serialize();

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
                ", body=" + body.toString() +
                '}';
    }
}