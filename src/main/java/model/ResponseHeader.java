package model;

public class ResponseHeader {
    private int correlationId;

    public ResponseHeader(int correlationId) {
        this.correlationId = correlationId;
    }

    public int getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(int correlationId) {
        this.correlationId = correlationId;
    }
}