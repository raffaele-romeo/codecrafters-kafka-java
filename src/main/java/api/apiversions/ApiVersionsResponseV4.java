package api.apiversions;

import api.ResponseBody;
import io.netty.buffer.Unpooled;
import model.ErrorCode;

final public class ApiVersionsResponseV4 implements ResponseBody {
    private static final int VERSION = 4;
    private final ErrorCode errorCode;

    public ApiVersionsResponseV4(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public byte[] serialize() {
        var responseByte = Unpooled.buffer();
        responseByte.writeShort(errorCode.getCode());

        byte[] result = new byte[responseByte.readableBytes()];
        responseByte.readBytes(result);

        return result;
    }

    @Override
    public int getVersion() {
        return VERSION;
    }
}
