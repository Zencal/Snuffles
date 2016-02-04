package net.zencal.snuffles.command;

import org.springframework.stereotype.Component;

@Component
public class MemeCommand extends ResponseCommand {
    @Override
    protected String getConfigFilePath() {
        return "meme.json";
    }
}
