package configurations;

public class EndConfig {

	/*
	 * 		
	 * endData.put("useEnd", useEnd.isSelected());
		endData.put("useOtherMethod", useOtherMethod.isSelected());
		endData.put("otherMethod", methodBox.getSelectedItem().toString());
	 */
	
	private boolean useEnd;
	private boolean useOtherMethod;
	private String otherMethodName;
	
	public EndConfig(boolean useEnd, boolean useOtherMethod, String otherMethodName) {
		super();
		this.useEnd = useEnd;
		this.useOtherMethod = useOtherMethod;
		this.otherMethodName = otherMethodName;
	}

	public boolean isUseEnd() {
		return useEnd;
	}

	public void setUseEnd(boolean useEnd) {
		this.useEnd = useEnd;
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
