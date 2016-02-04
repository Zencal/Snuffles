package net.zencal.snuffles.command;

import net.zencal.snuffles.domain.dubtrack.payload.DubChatPayload;

import java.util.Set;

public interface DubBotCommand {
	public void execute(DubChatPayload chatPayload);
	
	public Set<String> getCommandTriggers();
}