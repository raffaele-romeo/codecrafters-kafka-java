import api.ApiResponseHandler;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import model.Request;
import model.ResponseHeader;
import model.Response;

public class RequestHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {

        Request request = (Request) msg;
        var requestHeader = request.getHeader();
        var responseHeader = new ResponseHeader(requestHeader.getCorrelationId());
        var responseBody = ApiResponseHandler.handle(requestHeader.getRequestApiKey(), requestHeader.getRequestApiVersion());
        Response responseData = new Response(0, responseHeader, responseBody);

        ChannelFuture future = ctx.writeAndFlush(responseData);
        future.addListener(ChannelFutureListener.CLOSE);

        System.out.println(responseData.getMessageSize());
        System.out.println(responseData.getHeader().getCorrelationId());
    }
}