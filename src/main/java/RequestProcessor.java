
import api.common.ApiRegistry;
import api.common.RequestHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import model.Request;
import model.RequestContext;

public class RequestProcessor extends ChannelInboundHandlerAdapter {
    private final ApiRegistry apiRegistry;

    public RequestProcessor(ApiRegistry apiRegistry) {
        this.apiRegistry = apiRegistry;
    }

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            Request request = (Request) msg;
            var requestHeader = request.getHeader();
            var requestContext = new RequestContext(requestHeader.getCorrelationId(), requestHeader.getApiVersion());
            var handler = apiRegistry.getHandler(request.getHeader().getApiKey());

            var response = ((RequestHandler) handler).handle(requestContext, request.getBody());

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