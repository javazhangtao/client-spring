package com.rpc.transfer.scoket.suppor;

import com.google.common.base.Strings;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by zhangtao on 2015/12/23.
 */
public abstract class AbstractSocket implements Socket{

    /** 用于分配处理业务线程的线程组个数*/
    final int BIZGROUPSIZE = Runtime.getRuntime().availableProcessors()*2;
    /** 业务出现线程大小*/
    final int BIZTHREADSIZE = 1000;
    final EventLoopGroup bossGroup = new NioEventLoopGroup(BIZGROUPSIZE);
    final EventLoopGroup workerGroup = new NioEventLoopGroup(BIZTHREADSIZE);


    private ServerBootstrap bootstrap;
    private ChannelInitializer<? extends SocketChannel> initializer;
    private String host;
    private Integer port;

    @Override
    public abstract void register() throws Exception;

    protected void createBootstrap() throws Exception{
        try {
            this.bootstrap=new ServerBootstrap();
            this.bootstrap.group(bossGroup, workerGroup);
            this.bootstrap.channel(NioServerSocketChannel.class);
            this.bootstrap.childHandler(initializer);
        } catch (Exception e) {
            throw(e);
        }
    }

    protected void start() throws Exception{
        try {
            if(null==this.bootstrap){
                if(null==initializer || Strings.isNullOrEmpty(this.host) || null==this.port){
                    throw new NullPointerException();
                }
                createBootstrap();
            }
            this.bootstrap.bind(this.host,this.port).sync();
        } catch (Exception e) {
            throw(e);
        }finally {
            this.bossGroup.shutdownGracefully();
            this.workerGroup.shutdownGracefully();
        }
    }

    public ChannelInitializer<? extends SocketChannel> getInitializer() {
        return initializer;
    }

    public void setInitializer(ChannelInitializer<? extends SocketChannel> initializer) {
        this.initializer = initializer;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public ServerBootstrap getBootstrap() {
        return bootstrap;
    }

    public void setBootstrap(ServerBootstrap bootstrap) {
        this.bootstrap = bootstrap;
    }
}
