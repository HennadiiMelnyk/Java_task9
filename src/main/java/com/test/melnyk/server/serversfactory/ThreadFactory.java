package com.test.melnyk.server.serversfactory;

import com.test.melnyk.server.HTTPServer;
import com.test.melnyk.server.TCPServer;

public interface ThreadFactory {
    /**
     * start thread for tcpServer
     * @param tcpServer
     */
    void runTCPServer(TCPServer tcpServer);

    /**
     * start thread for http server
     * @param httpServer
     */
    void runHTTPServer(HTTPServer httpServer);
}
