package com.rpc.transfer.scoket.suppor.client;

import com.google.common.base.Strings;
import com.rpc.transfer.scoket.suppor.AbstractSocket;
import io.netty.channel.Channel;

/**
 * Created by zhangtao on 2015/12/23.
 */
public abstract class AbstractClientSocket extends AbstractSocket implements ClientSocket{
    private String host;
    private Integer port;

    @Override
    public void register() throws Exception {}

    @Override
    public Channel getChannel() throws Exception {
        try {
            if(null==super.getBootstrap()){
                super.createBootstrap();
            }
            if(Strings.isNullOrEmpty(this.host) || null==this.port){
                throw new NullPointerException();
            }
            return super.getBootstrap().bind(this.host,this.port).sync().channel();
        } catch (Exception e) {
            throw(e);
        }
    }

    @Override
    public void closeChannel(Channel channel) throws Exception {
        if(null!=channel){
            channel.closeFuture().sync();
        }
    }

    @Override
    public abstract String getServerHostAndPort(String serverName) throws Exception;
}
