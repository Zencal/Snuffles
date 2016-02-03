package net.zencal.snuffles.command;

import java.util.Set;

import net.zencal.snuffles.domain.dubtrack.DubChatPayload;

public interface DubBotCommand {
	public void execute(DubChatPayload chatPayload);
	
	public Set<String> getCommandTriggers();
}