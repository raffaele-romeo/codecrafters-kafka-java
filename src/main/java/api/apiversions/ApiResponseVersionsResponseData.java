package api.apiversions;

import api.common.ApiResponseMessage;
import io.netty.buffer.ByteBuf;
import model.ApiKey;
import model.ErrorCode;
import protocol.UnsignedVarInt;

import java.util.Arrays;

public record ApiResponseVersionsResponseData(ErrorCode errorCode, ApiKey[] apiKeys,
                                              int throttleTimeMs) implements ApiResponseMessage {

    @Override
    public void write(ByteBuf output) {
        output.writeShort(errorCode.getCode());
        int apiKeysLength = apiKeys.length;
        UnsignedVarInt.write(output, apiKeysLength + 1);

        for (ApiKey apiKey : this.apiKeys) {
            output.writeBytes(apiKey.serialize());
        }

        output.writeInt(throttleTimeMs);
        UnsignedVarInt.write(output, 0);  // TAG_BUFFER
    }

    @Override
    public String toString() {
        return "ApiResponseVersionData{" +
                "errorCode=" + errorCode +
                ", apiKeys=" + Arrays.toString(apiKeys) +
                ", throttleTimeMs=" + throttleTimeMs +
                '}';
    }
}