package com.hanson.rpc;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 服务端实现一个简单的计算器服务，客户端可以远程调用该服务进行加法运算。
 *
 * 服务端代码（CalculatorService.java）：
 *
 * @author hanson
 * @date 2024/3/13 16:02
 */
public interface CalculatorService extends Remote {
    int add(int a,int b) throws RemoteException;
}
