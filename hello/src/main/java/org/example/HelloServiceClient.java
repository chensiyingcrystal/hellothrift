package org.example;

import org.apache.log4j.PropertyConfigurator;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;


public class HelloServiceClient {

    public static void main(String[] args) {
        System.out.println("客户端启动....");

        for (int i = 0; i < 1000; i++) {
                final int ii = i;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        TTransport transport = null;
                        try {
                            transport = new TSocket("localhost", 9898, 30000);
                            // 协议要和服务端一致
                            final TProtocol protocol = new TCompactProtocol(transport);
                            Hello.Client client = new Hello.Client(protocol);
                            transport.open();
                            System.out.println(client.helloString("hello"+ ii));
                        } catch (TTransportException e) {
                            throw new RuntimeException(e);
                        } catch (TException e) {
                            e.printStackTrace();
                        } finally {
                            if (null != transport) {
                                transport.close();
                            }
                        }
                    }
                }).start();
            }
        }
}
