package api.apiversions;

import api.RequestBody;
import io.netty.buffer.ByteBuf;
import protocol.RawTaggedField;
import protocol.CompactString;

public class ApiVersionsRequestBody implements RequestBody {
    private final String clientSoftwareName;
    private final String clientSoftwareVersion;
    private final RawTaggedField taggedField;

    private ApiVersionsRequestBody(String clientSoftwareName, String clientSoftwareVersion, RawTaggedField taggedField) {
        this.clientSoftwareName = clientSoftwareName;
        this.clientSoftwareVersion = clientSoftwareVersion;
        this.taggedField = taggedField;
    }

    public static ApiVersionsRequestBody parse(ByteBuf input) {
        var clientSoftwareName = CompactString.read(input);
        var clientSoftwareVersion = CompactString.read(input);
        var taggedField = RawTaggedField.read(input);

        return new ApiVersionsRequestBody(clientSoftwareName, clientSoftwareVersion, taggedField);
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
