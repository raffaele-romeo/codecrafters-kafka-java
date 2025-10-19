package api.apiversions;

import api.RequestBody;
import io.netty.buffer.ByteBuf;
import protocol.PrimitiveTypesReader;

public class ApiVersionRequestBody implements RequestBody {
    private final String clientSoftwareName;
    private final String clientSoftwareVersion;

    private ApiVersionRequestBody(String clientSoftwareName, String clientSoftwareVersion) {
        this.clientSoftwareName = clientSoftwareName;
        this.clientSoftwareVersion = clientSoftwareVersion;
    }

    public static ApiVersionRequestBody parse(ByteBuf input) {
        var clientSoftwareName = PrimitiveTypesReader.readCompactString(input);
        var clientSoftwareVersion = PrimitiveTypesReader.readCompactString(input);
        input.readByte(); // Tag buffer - discarded for now

        return new ApiVersionRequestBody(clientSoftwareName, clientSoftwareVersion);
    }

    @Override
    public String toString() {
        return "ApiVersionRequestBody{" +
                "clientSoftwareName='" + clientSoftwareName + '\'' +
                ", clientSoftwareVersion='" + clientSoftwareVersion + '\'' +
                '}';
    }
}
