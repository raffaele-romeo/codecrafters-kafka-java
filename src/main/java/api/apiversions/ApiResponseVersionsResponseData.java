package api.apiversions;

import api.common.ApiResponseMessage;
import io.netty.buffer.ByteBuf;
import model.ApiKey;
import model.ErrorCode;
import protocol.CompactArray;
import protocol.UnsignedVarInt;

import java.util.List;

public record ApiResponseVersionsResponseData(ErrorCode errorCode, List<ApiKey> apiKeys,
                                              int throttleTimeMs) implements ApiResponseMessage {

    @Override
    public void write(ByteBuf output) {
        errorCode.write(output);
        CompactArray.write(output, apiKeys, (buf, apiKey) -> apiKey.write(buf));

        output.writeInt(throttleTimeMs);
        UnsignedVarInt.write(output, 0);  //TODO Handle TAG_BUFFER
    }

    @Override
    public String toString() {
        return "ApiResponseVersionsResponseData{" +
                "errorCode=" + errorCode +
                ", apiKeys=" + apiKeys +
                ", throttleTimeMs=" + throttleTimeMs +
                '}';
    }
}