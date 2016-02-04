package net.zencal.snuffles.command;

import net.zencal.snuffles.domain.dubtrack.payload.DubChatPayload;
import net.zencal.snuffles.service.DubtrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@BotCommand(triggers = { "btfo" })
public class BtfoCommand extends AnnotationConfiguredDubBotCommand {

    @Autowired
    private DubtrackService dubtrackService;
	
	@Override
	public void execute(DubChatPayload chatPayload) {
		dubtrackService.sendMessage("B T F O");
		dubtrackService.sendMessage("T");
		dubtrackService.sendMessage("F");
		dubtrackService.sendMessage("O");
	}
}