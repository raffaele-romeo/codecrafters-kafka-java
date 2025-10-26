package model;

public enum ErrorCode {
    UNSUPPORTED_VERSION(35, false),
    NONE(0, false),
    UNKNOWN_TOPIC_OR_PARTITION(3, true);

    private final short code;
    private final boolean retriable;

    ErrorCode(int code, boolean retriable) {
        this.code = (short) code;
        this.retriable = retriable;
    }

    public short getCode() {
        return code;
    }
}
