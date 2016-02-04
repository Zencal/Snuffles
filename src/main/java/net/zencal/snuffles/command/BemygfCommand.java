package net.zencal.snuffles.command;

import org.springframework.stereotype.Component;

@Component
public class BemygfCommand extends ResponseCommand {
    @Override
    protected String getConfigFilePath() {
        return "bemygf.json";
    }
}
