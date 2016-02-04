package net.zencal.snuffles.command.configuration;

import java.util.List;

public class ResponseConfiguration extends Configuration {
    protected List<String> responses;

    public List<String> getResponses() {
        return responses;
    }

    public void setResponses(List<String> responses) {
        this.responses = responses;
    }
}
