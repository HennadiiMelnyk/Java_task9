package com.test.melnyk.server.serversfactory;

import com.test.melnyk.server.HTTPServer;
import com.test.melnyk.server.TCPServer;

public class ThreadFactoryImpl implements ThreadFactory {
    @Override
    public void runTCPServer(TCPServer tcpServer) {
        Thread tcpServerThread = new Thread(tcpServer::startTCPServer);
        tcpServerThread.start();
    }

    @Override
    public void runHTTPServer(HTTPServer httpServer) {
        Thread httpServerThread = new Thread(httpServer::startHTTPServer);
        httpServerThread.start();
    }
}
