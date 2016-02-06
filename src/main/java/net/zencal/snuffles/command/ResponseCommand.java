package net.zencal.snuffles.command;

import com.google.gson.Gson;
import net.zencal.snuffles.command.configuration.ResponseConfiguration;
import net.zencal.snuffles.domain.dubtrack.payload.DubChatPayload;
import net.zencal.snuffles.service.DubtrackService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;

public abstract class ResponseCommand extends FileConfiguredDubBotCommand {
    @Autowired
    protected DubtrackService dubtrackService;

    protected abstract String getConfigFilePath();

    @Override
    public void execute(DubChatPayload chatPayload) {
        ResponseConfiguration responseConfiguration = new Gson().fromJson(configurationJson.toString(), ResponseConfiguration.class);
        Random random = new Random();
        Integer randomInt = random.nextInt(responseConfiguration.getResponses().size());
        String response = responseConfiguration.getResponses().get(randomInt);
        dubtrackService.sendMessage(response.replaceAll("<<username>>", chatPayload.getUser().getUsername()));
    }
}
