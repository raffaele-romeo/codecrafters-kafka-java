import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.MultiThreadIoEventLoopGroup;
import io.netty.channel.nio.NioIoHandler;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class Server {
    private final int port;

    public Server(int port) {
        this.port = port;
    }

    public void run() throws Exception {
        // Boss EventLoop: Single-threaded, handles server socket accepts.
        // New connection is established from the client with a 3-way TCP handshake at the OS level,
        // epoll detects server FD ready → Java NIO selector fires OP_ACCEPT →
        // boss calls accept() to get new client FD → assigns client channel to worker.
        EventLoopGroup bossGroup = new MultiThreadIoEventLoopGroup(1, NioIoHandler.newFactory());

        // Worker EventLoops: Multi-threaded, each worker owns multiple client FDs.
        // Each worker registers its client FDs with its own epoll instance for I/O events.
        EventLoopGroup workerGroup = new MultiThreadIoEventLoopGroup(Runtime.getRuntime().availableProcessors(), NioIoHandler.newFactory());

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    // This is the main channel and the only responsibility is to accept incoming connection
                    .channel(NioServerSocketChannel.class)
                    // Netty creates a separate channel to manage the communication with the client once the connection is accepted
                    // It creates a new handler instance per channel.
                    // The logic should be defined in the pipeline as a list of consecutive steps
                    .childHandler(new BrokerInitializer())
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childOption(ChannelOption.SO_REUSEADDR, true);

            // Bind and start to accept incoming connections.
            ChannelFuture future = serverBootstrap.bind(port).sync();
            System.out.println("Server started and listening on port " + port);


            future.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
