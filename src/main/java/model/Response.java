package model;

import api.ApiResponseHandler;
import api.ResponseBody;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class Response {
    private ResponseHeader header;
    private ResponseBody body;

    private Response(ResponseHeader header, ResponseBody body) {
        this.header = header;
        this.body = body;
    }

    public static Response handle(Request request) {
        var requestHeader = request.getHeader();
        var responseHeader = new ResponseHeader(requestHeader.getCorrelationId());
        var responseBody = ApiResponseHandler.handle(requestHeader.getRequestApiKey(), requestHeader.getRequestApiVersion());

        return new Response(responseHeader, responseBody);
    }

    public void setHeader(ResponseHeader header) {
        this.header = header;
    }

    public ResponseBody getBody() {
        return body;
    }

    public void setBody(ResponseBody body) {
        this.body = body;
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
}