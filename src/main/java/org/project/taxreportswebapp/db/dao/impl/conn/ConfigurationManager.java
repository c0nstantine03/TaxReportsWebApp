package org.project.taxreportswebapp.db.dao.impl.conn;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

// This class have to read DB configuration from XML file
// and then provide it to DataSource class
class ConfigurationManager {
	private Properties properties;

	public ConfigurationManager() {
		loadProperties();
	}

	public void loadProperties() {
		String filename = "database-config.xml";

		try (InputStream input = getClass().getClassLoader().getResourceAsStream(filename)) {
			Properties localProperties = new Properties();
			localProperties.loadFromXML(input);
			properties = localProperties;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getDriver() {
		return properties.getProperty("driver");
	}

	public String getUrl() {
		return properties.getProperty("url");
	}

	public String getUsername() {
		return properties.getProperty("username");
	}

	public String getPassword() {
		return properties.getProperty("password");
	}
}
