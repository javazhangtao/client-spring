package com.rpc.transfer.scoket.suppor.client;

import io.netty.channel.Channel;

/**
 * Created by zhangtao on 2015/12/23.
 * 客户端通信管道创建接口
 */
public interface ClientSocket {


    /**
     * 获取通信管道
     * @return
     * @throws Exception
     */
    Channel getChannel() throws Exception;

    /**
     * 关闭通信管道
     * @param channel
     * @throws Exception
     */
    void closeChannel(final Channel channel) throws Exception;

    /**
     * 根据调用服务获取服务所在host和端口
     * @param serverName
     * @return
     * @throws Exception
     */
    String getServerHostAndPort(final String serverName) throws Exception;
}
