package model;

import io.netty.buffer.ByteBuf;

public class Request {
    private int messageSize;
    private RequestHeader header;

    private Request() {
    }

    private Request(int messageSize, RequestHeader header) {
        this.messageSize = messageSize;
        this.header = header;
    }

    public static Request parse(int messageSize, ByteBuf in) {
        var requestHeader = RequestHeader.parse(in);

        return new Request(messageSize, requestHeader);
    }

    public int getMessageSize() {
        return messageSize;
    }

    public RequestHeader getHeader() {
        return header;
    }

    @Override
    public String toString() {
        return "Request{" +
                "messageSize=" + messageSize +
                ", header=" + header.toString() +
                '}';
    }
}
