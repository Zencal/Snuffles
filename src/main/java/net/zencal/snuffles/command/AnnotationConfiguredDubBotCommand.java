package net.zencal.snuffles.command;

import java.util.Set;

import org.apache.commons.lang3.Validate;

import com.google.common.collect.Sets;

public abstract class AnnotationConfiguredDubBotCommand implements DubBotCommand {

	private Set<String> triggers;
	
	public AnnotationConfiguredDubBotCommand() {
		BotCommand commandAnnotation = this.getClass().getAnnotation(BotCommand.class);
		
		Validate.notNull(commandAnnotation, "Found " + this.getClass().getSimpleName() + " that was missing the @BotCommand annotation");
		
		triggers = Sets.newHashSet(commandAnnotation.triggers());
	}
	
	@Override
	public Set<String> getCommandTriggers() {
		return triggers;
	}
}