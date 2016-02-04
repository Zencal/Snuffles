package net.zencal.snuffles.command;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

public abstract class FileConfiguredDubBotCommand implements DubBotCommand {
    @Autowired
    private ResourceLoader resourceLoader;
	
	protected JSONObject configurationJson;

	@PostConstruct
	private void init() {
		Resource resource = resourceLoader.getResource("classpath:" + getConfigFilePath());

		try (InputStream inputStream = resource.getInputStream()) {
			String json = IOUtils.toString(inputStream);
			configurationJson = new JSONObject(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected abstract String getConfigFilePath();
	
	@Override
	public Set<String> getCommandTriggers() {
		JSONArray triggersJson = configurationJson.getJSONArray("commandTriggers");
		
		Set<String> triggers = new HashSet<>();
		
		for (int i = 0; i < triggersJson.length(); i++) {
			triggers.add(triggersJson.getString(i));
		}
		
		return triggers;
	}
}
