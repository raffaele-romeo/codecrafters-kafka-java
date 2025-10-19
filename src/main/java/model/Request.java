package model;

import api.ApiRequestBodyParser;
import api.RequestBody;
import io.netty.buffer.ByteBuf;

public class Request {
    private final int messageSize;
    private final RequestHeader header;
    private final RequestBody body;

    private Request(int messageSize, RequestHeader header, RequestBody body) {
        this.messageSize = messageSize;
        this.header = header;
        this.body = body;
    }

    public static Request parse(int messageSize, ByteBuf in) {
        var requestHeader = RequestHeader.parse(in);
        var requestBody = ApiRequestBodyParser.parse(requestHeader.getApiKey(), in);

        return new Request(messageSize, requestHeader, requestBody);
    }

    public RequestHeader getHeader() {
        return header;
    }

    @Override
    public String toString() {
        return "Request{" +
                "messageSize=" + messageSize +
                ", header=" + header.toString() +
                ", body=" + body.toString() +
                '}';
    }
}
