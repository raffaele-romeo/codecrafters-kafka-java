package api.apiversions;

import api.common.ApiRequestMessage;
import io.netty.buffer.ByteBuf;
import protocol.RawTaggedField;
import protocol.CompactString;

public class ApiResponseVersionsRequestData implements ApiRequestMessage {
    String clientSoftwareName;
    String clientSoftwareVersion;
    RawTaggedField taggedField;

    public ApiResponseVersionsRequestData(ByteBuf input) {
        this.read(input);
    }

    public final void read(ByteBuf input) {
        this.clientSoftwareName = CompactString.read(input);
        this.clientSoftwareVersion = CompactString.read(input);
        this.taggedField = RawTaggedField.read(input);
    }


    @Override
    public String toString() {
        return "ApiVersionRequestBody{" +
                "clientSoftwareName='" + clientSoftwareName + '\'' +
                ", clientSoftwareVersion='" + clientSoftwareVersion + '\'' +
                ", taggedField=" + taggedField +
                '}';
    }
}
