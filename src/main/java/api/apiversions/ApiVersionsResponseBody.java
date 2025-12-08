package api.apiversions;

import api.ResponseBody;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import model.ApiKey;
import model.ErrorCode;
import protocol.UnsignedVarInt;

import java.util.Arrays;

public record ApiVersionsResponseBody(ErrorCode errorCode, ApiKey[] apiKeys, int throttleTimeMs) implements ResponseBody {

    @Override
    public ByteBuf serialize() {
        var result = Unpooled.buffer();
        try {
            result.writeShort(errorCode.getCode());
            int apiKeysLength = apiKeys.length;
            UnsignedVarInt.write(result, apiKeysLength + 1);

            for (ApiKey apiKey : apiKeys) {
                result.writeBytes(apiKey.serialize());
            }

            result.writeInt(throttleTimeMs);
            UnsignedVarInt.write(result, 0);  // TAG_BUFFER

            return result;
        } catch (Exception e) {
            result.release();
            throw e;
        }
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