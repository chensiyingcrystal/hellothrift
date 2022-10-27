package org.example;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;


public class HelloServiceServer {
    /**
     * 启动thrift服务器
     * @param args
     */
    public static void main(String[] args) {
//        try {
//            System.out.println("服务端开启....");
//            TProcessor tprocessor = new Hello.Processor<Hello.Iface>(new HelloServiceImpl());
//            // 简单的单线程服务模型
//            TServerSocket serverTransport = new TServerSocket(9898);
//            TServer.Args tArgs = new TServer.Args(serverTransport);
//            tArgs.processor(tprocessor);
//            tArgs.protocolFactory(new TBinaryProtocol.Factory());
//            TServer server = new TSimpleServer(tArgs);
//            server.serve();
//        }catch (TTransportException e) {
//            e.printStackTrace();
//        }

        try {
            System.out.println("阻塞式多线程服务端开启....");
            //设置传输通道，普通通道
            TServerTransport serverTransport = new TServerSocket(9898);
            //使用高密度二进制协议
            TProtocolFactory proFactory = new TCompactProtocol.Factory();
            //设置处理器
            TProcessor processor = new Hello.Processor(new HelloServiceImpl());
            //创建服务器
            TServer server = new TThreadPoolServer(
                    new TThreadPoolServer.Args(serverTransport)
                            .protocolFactory(proFactory)
                            .processor(processor)
            );


            //System.out.println("Start server on port "+ port +"...");
            server.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
