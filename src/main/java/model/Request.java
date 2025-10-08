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

    public static Request from(ByteBuf in) {
        var messageSize = in.readInt();
        var requestHeader = RequestHeader.from(in);

        return new Request(messageSize, requestHeader);
    }

    public RequestHeader getHeader() {
        return header;
    }

    public int getMessageSize() {
        return messageSize;
    }


    public void setHeader(RequestHeader header) {
        this.header = header;
    }

    public void setMessageSize(int messageSize) {
        this.messageSize = messageSize;
    }
}
