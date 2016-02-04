package net.zencal.snuffles.command.configuration;

import java.util.List;

public class Configuration {
    protected List<String> commandTriggers;

    public List<String> getCommandTriggers() {
        return commandTriggers;
    }

    public void setCommandTriggers(List<String> commandTriggers) {
        this.commandTriggers = commandTriggers;
    }
}
