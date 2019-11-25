package com.test.melnyk.server.commands;

import java.io.PrintWriter;

public interface Command {

    void execute(String request, PrintWriter printWriter);
}
