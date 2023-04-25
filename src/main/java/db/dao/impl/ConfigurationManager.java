package db.dao.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

// This class have to read DB configuration from XML file
// and then provide it to DataSource class
public class ConfigurationManager {
	private final String filename = "database-config.xml";
	private static volatile ConfigurationManager instance;
	private Properties properties;

	private ConfigurationManager() throws IOException {
		properties = new Properties();
		try (InputStream input = ConfigurationManager.class.getClassLoader().getResourceAsStream(filename)) {
			properties.loadFromXML(input);
		}
	}

	// Double-Checked Instantiation
	public static ConfigurationManager getInstance() throws IOException {
		ConfigurationManager localInstance = instance;
		if (localInstance == null) {
			synchronized (ConfigurationManager.class) {
				localInstance = instance;
				if (localInstance == null) {
					instance = localInstance = new ConfigurationManager();
				}
			}
		}
		return localInstance;
	}

	public Properties getProperties() {
		return properties;
	}
}
