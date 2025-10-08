package model;

import api.ResponseBody;
import io.netty.buffer.ByteBuf;

public class Response {
    private int messageSize;
    private ResponseHeader header;
    private ResponseBody body;

    public ResponseHeader getHeader() {
        return header;
    }

    public Response(int messageSize, ResponseHeader header, ResponseBody body) {
        this.messageSize = messageSize;
        this.header = header;
        this.body = body;
    }

    public int getMessageSize() {
        return messageSize;
    }

    public void setMessageSize(int messageSize) {
        this.messageSize = messageSize;
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

    public void writeTo (ByteBuf byteBuf) {
        byteBuf.writeInt(messageSize);
        header.writeTo(byteBuf);
        body.writeTo(byteBuf);
    }
}