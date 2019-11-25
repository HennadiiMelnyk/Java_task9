package com.test.melnyk.controller.command;

import com.test.melnyk.controller.Command;

import java.util.Map;

public class CommandContainer {

    Map<String, Command> commands;

    public CommandContainer(Map<String, Command> map) {
        commands = map;
    }

    public Command get(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
            return commands.get("noCommand");
        }
        return commands.get(commandName);

    }


}
