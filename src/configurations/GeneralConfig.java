package configurations;

public class GeneralConfig {

	/*
	 * generalConfigurationData.put("browserPath", browserPath.getText());
		generalConfigurationData.put("chkLimitOfSteps", chkLimitOfSteps.isSelected());
		generalConfigurationData.put("limitOfSteps", limitOfSteps.getText());
	 */
	
	private String browserPath;
	private boolean chkLimitOfSteps;
	private int limitOfSteps;
	private String browser;
	private String testDirectory;
	
	public GeneralConfig(String browserPath, boolean chkLimitOfSteps, int limitOfSteps, String browser, String testDirectory) {
		super();
		this.browserPath = browserPath;
		this.chkLimitOfSteps = chkLimitOfSteps;
		this.limitOfSteps = limitOfSteps;
		this.browser = browser;
		this.testDirectory = testDirectory;
		
	}



	public String getTestDirectory() {
		return testDirectory;
	}



	public void setTestDirectory(String testDirectory) {
		this.testDirectory = testDirectory;
	}



	public String getBrowserPath() {
		return browserPath;
	}

	public void setBrowserPath(String browserPath) {
		this.browserPath = browserPath;
	}

	public boolean isChkLimitOfSteps() {
		return chkLimitOfSteps;
	}

	public void setChkLimitOfSteps(boolean chkLimitOfSteps) {
		this.chkLimitOfSteps = chkLimitOfSteps;
	}

	public int getLimitOfSteps() {
		return limitOfSteps;
	}

	public void setLimitOfSteps(int limitOfSteps) {
		this.limitOfSteps = limitOfSteps;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}
	

	
	
	
}
