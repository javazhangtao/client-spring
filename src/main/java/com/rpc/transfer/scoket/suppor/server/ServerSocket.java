package com.rpc.transfer.scoket.suppor.server;

import com.rpc.transfer.scoket.suppor.AbstractSocket;
import org.apache.log4j.Logger;

/**
 * Created by zhangtao on 2015/12/23.
 */
public class ServerSocket extends AbstractSocket {

    Logger logger=Logger.getLogger(ServerSocket.class);

    @Override
    public void register() throws Exception {
        try {
            if(null!=super.getBootstrap()){
                super.start();//启动服务
                //do some   服务加入注册中心
                logger.info(" SERVER STARTED ");
            }
        } catch (Exception e) {
            throw(e);
        }
    }
}
