package org.ksk_team.projects.CorporateCardControlProject.service.versioning;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class GitVersionControl {
	
	private final static String DEFAULT_CONFIG_PATH = "/home/jack/workspace/CardControlProject/target/classes/version/version.properties";

	private final static String VERSION_PROPERTY = "project.version";
	
	private Properties versionConfig = new Properties();

	private static GitVersionControl instance = null;
	
	public static GitVersionControl getInstance() throws FileNotFoundException, IOException{
		if(instance == null){
			return instance = new GitVersionControl();
		}else{
			return instance;
		}
	}
	
	private GitVersionControl() throws FileNotFoundException, IOException {
		this(DEFAULT_CONFIG_PATH);
	}

	private GitVersionControl(String versionConfigPath) throws FileNotFoundException, IOException {
		versionConfig.load(new FileInputStream(versionConfigPath));
	}
	
	public String getGitVersion(){
		return versionConfig.getProperty(VERSION_PROPERTY);
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		System.out.println(getInstance().getGitVersion());
	}
}
