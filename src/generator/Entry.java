package generator;

public class Entry {

	public String name;
	public String detectionType;
	public String detectionValue;
	public String methodName;
	public boolean passElement;
	public boolean passBrowser;
	public int usageLimit=100;
	public boolean waitAfterMethod;
	public boolean pageChanger;
	
	
	public Entry(String name, String detectionType, String detectionValue, String methodName, boolean passElement,
			boolean passBrowser, int usageLimit, boolean waitAfterMethod, boolean pageChanger) {
		super();
		this.name = name;
		this.detectionType = detectionType;
		this.detectionValue = detectionValue;
		this.methodName = methodName;
		this.passElement = passElement;
		this.passBrowser = passBrowser;
		this.usageLimit = usageLimit;
		this.waitAfterMethod = waitAfterMethod;
		this.pageChanger = pageChanger;
	}
	

	public int getUsageLimit() {
		return usageLimit;
	}


	public boolean isWaitAfterMethod() {
		return waitAfterMethod;
	}


	public void setWaitAfterMethod(boolean waitAfterMethod) {
		this.waitAfterMethod = waitAfterMethod;
	}


	public boolean isPageChanger() {
		return pageChanger;
	}


	public void setPageChanger(boolean pageChanger) {
		this.pageChanger = pageChanger;
	}


	public void setUsageLimit(int usageLimit) {
		this.usageLimit = usageLimit;
	}


	public boolean isPassElement() {
		return passElement;
	}


	public void setPassElement(boolean passElement) {
		this.passElement = passElement;
	}


	public boolean isPassBrowser() {
		return passBrowser;
	}


	public void setPassBrowser(boolean passBrowser) {
		this.passBrowser = passBrowser;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDetectionType() {
		return detectionType;
	}
	public void setDetectionType(String detectionType) {
		this.detectionType = detectionType;
	}
	public String getDetectionValue() {
		return detectionValue;
	}
	public void setDetectionValue(String detectionValue) {
		this.detectionValue = detectionValue;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	
	
	
}
