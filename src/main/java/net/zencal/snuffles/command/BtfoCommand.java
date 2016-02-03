package net.zencal.snuffles.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.zencal.snuffles.domain.dubtrack.DubChatPayload;
import net.zencal.snuffles.service.DubtrackService;

@Service
@BotCommand(triggers = { "btfo" })
public class BtfoCommand extends AnnotationConfiguredDubBotCommand {

    @Autowired
    private DubtrackService dubtrackService;
	
	@Override
	public void execute(DubChatPayload chatPayload) {
		dubtrackService.sendBTFO();
	}
}