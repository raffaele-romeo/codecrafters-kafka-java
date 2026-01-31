package model;

import api.common.AbstractRequest;
import io.netty.buffer.ByteBuf;
import model.header.RequestHeader;

public class RequestWithHeader {
    private final int messageSize;
    private final RequestHeader header;
    private final AbstractRequest request;

    private RequestWithHeader(int messageSize, RequestHeader header, AbstractRequest request) {
        this.messageSize = messageSize;
        this.header = header;
        this.request = request;
    }

    public static RequestWithHeader parse(int messageSize, ByteBuf input) {
        var requestHeader = RequestHeader.parse(input);
        System.out.println(requestHeader);
        var request = AbstractRequest.parseRequest(requestHeader.getApiKey(), input);

        return new RequestWithHeader(messageSize, requestHeader, request);
    }

    public RequestHeader getHeader() {
        return header;
    }

    public AbstractRequest getBody() {
        return request;
    }

    @Override
    public String toString() {
        return "Request{" +
                "messageSize=" + messageSize +
                ", header=" + header.toString() +
                ", request=" + request.toString() +
                '}';
    }
}
