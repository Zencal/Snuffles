package net.zencal.snuffles.service;

import com.google.gson.Gson;
import net.zencal.snuffles.command.DubBotCommand;
import net.zencal.snuffles.domain.dubtrack.payload.DubChatPayload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommandService implements BeanPostProcessor {
    @Autowired
    protected DubtrackService dubtrackService;
    @Autowired
    protected DubBotService dubBotService;
    @Autowired
    protected ResourceLoader resourceLoader;

    private Logger logger = LogManager.getLogger(CommandService.class);
    private Logger chatLogger = LogManager.getLogger("chat");

    private List<DubBotCommand> commands = new ArrayList<>();

    public void handleCommand(JSONObject json) {
        DubChatPayload chatPayload = new Gson().fromJson(json.toString(), DubChatPayload.class);
        dubBotService.sendDubtrackMessage(chatPayload.getUser().getUsername(), chatPayload.getMessage());
        chatLogger.trace(chatPayload.getUser().getUsername() + ": " + chatPayload.getMessage());

        String[] arguments = chatPayload.getMessage().split(" ");
        String trigger = arguments[0].substring(1).toLowerCase(); // strip the leading ! from the command 
        
        for (DubBotCommand command : commands) {
        	if (command.getCommandTriggers().contains(trigger)) {
        		logger.info("Activating command " + command.getClass().getName() + " via trigger " + trigger);
        		command.execute(chatPayload);
        	}
        }
    }

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		// Nothing to do
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		logger.info("postProcessAfterInitialization beginning for bean of class " + bean.getClass().getSimpleName());
		
		if (DubBotCommand.class.isAssignableFrom(bean.getClass())) {
			logger.info("Bean " + bean.getClass().getSimpleName() + " is a DubBotCommand, storing it");
			
			DubBotCommand command = DubBotCommand.class.cast(bean);
			commands.add(command);
		}
		
		return bean;
	}
}
