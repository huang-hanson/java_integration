package com.hanson.rpc;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author hanson
 * @date 2024/3/13 16:03
 */
public class CalculatorServiceImpl extends UnicastRemoteObject implements CalculatorService {

    protected CalculatorServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public int add(int a, int b) throws RemoteException {
        return a + b;
    }
}
