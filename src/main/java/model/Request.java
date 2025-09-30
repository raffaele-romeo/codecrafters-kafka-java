package model;

public class Request {
    private int messageSize;
    private RequestHeader header;

    public Request () {
    }

    public Request(int messageSize, RequestHeader header) {
        this.messageSize = messageSize;
        this.header = header;
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
