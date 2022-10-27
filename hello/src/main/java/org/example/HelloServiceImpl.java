package org.example;
import org.apache.thrift.TException;

public class HelloServiceImpl implements Hello.Iface {
    public String helloString(String para) throws TException {
        System.out.println("request: " + para);
        return "result: " + para;
    }
}