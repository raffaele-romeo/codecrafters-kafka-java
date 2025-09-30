package model;

public class Response {
    private int messageSize;
    private ResponseHeader header;

    public Response(int messageSize, ResponseHeader header) {
        this.messageSize = messageSize;
        this.header = header;
    }

    public int getMessageSize() {
        return messageSize;
    }

    public void setMessageSize(int messageSize) {
        this.messageSize = messageSize;
    }

    public ResponseHeader getHeader() {
        return header;
    }

    public void setHeader(ResponseHeader header) {
        this.header = header;
    }
}