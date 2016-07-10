package configurations;

public class StartConfig {

	/*
	 * startData.put("useStart", useStart.isSelected());
		startData.put("useOtherMethod", useOtherMethod.isSelected());
		startData.put("otherMethod", methodBox.getSelectedItem().toString());
	 */
	
	private boolean useStart;
	private boolean useOtherMethod;
	private String otherMethodName;
	
	public StartConfig(boolean useStart, boolean useOtherMethod, String otherMethodName) {
		super();
		this.useStart = useStart;
		this.useOtherMethod = useOtherMethod;
		this.otherMethodName = otherMethodName;
	}

	public boolean isUseStart() {
		return useStart;
	}

	public void setUseStart(boolean useStart) {
		this.useStart = useStart;
	}

	public boolean isUseOtherMethod() {
		return useOtherMethod;
	}

	public void setUseOtherMethod(boolean useOtherMethod) {
		this.useOtherMethod = useOtherMethod;
	}

	public String getOtherMethodName() {
		return otherMethodName;
	}

	public void setOtherMethodName(String otherMethodName) {
		this.otherMethodName = otherMethodName;
	}
	
	
	
}
