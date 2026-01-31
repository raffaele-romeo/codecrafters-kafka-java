
import api.common.ApiRegistry;
import api.common.RequestHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import model.RequestWithHeader;
import model.header.RequestContext;

public class RequestProcessor extends ChannelInboundHandlerAdapter {
    private final ApiRegistry apiRegistry;

    public RequestProcessor(ApiRegistry apiRegistry) {
        this.apiRegistry = apiRegistry;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            RequestWithHeader request = (RequestWithHeader) msg;
            var requestHeader = request.getHeader();
            var requestContext = new RequestContext(requestHeader.getCorrelationId(), requestHeader.getApiVersion());
            RequestHandler handler = apiRegistry.getHandler(requestHeader.getApiKey());

            var response = handler.handle(requestContext, request.getBody());

            System.out.println(response);
            ctx.writeAndFlush(response);
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}