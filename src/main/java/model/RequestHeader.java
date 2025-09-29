package model;

public record RequestHeader(short requestApiKey, short requestApiVersion, int correlationId) {
}
