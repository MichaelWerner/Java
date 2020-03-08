package net._4werners.SCD;

import javafx.beans.property.SimpleStringProperty;

public class SCDConfigData {
	private SimpleStringProperty sConfigType;
	private SimpleStringProperty sConfigValue;
	private SimpleStringProperty sConfigDescription;

	public SCDConfigData(SimpleStringProperty sConfigType, SimpleStringProperty sConfigValue, SimpleStringProperty sConfigDescription) {
		this.sConfigType = sConfigType;
		this.sConfigValue = sConfigValue;
		this.sConfigDescription = sConfigDescription;
	}

	public SCDConfigData(String sConfigType, String sConfigValue, String sConfigDescription) {
		this.sConfigType = new SimpleStringProperty(sConfigType);
		this.sConfigValue = new SimpleStringProperty(sConfigValue);
		this.sConfigDescription = new SimpleStringProperty(sConfigDescription);
	}
	
    public String getsConfigType() {
        return sConfigType.get();
    }
	
    public SimpleStringProperty sConfigTypeProperty() {
        return sConfigType;
    }

    public void setsConfigType(String sInput) {
        this.sConfigType.set(sInput);
    }

    public String getsConfigValue() {
        return sConfigValue.get();
    }
	
    public SimpleStringProperty sConfigValueProperty() {
        return sConfigValue;
    }

    public void setsConfigValue(String sInput) {
        this.sConfigValue.set(sInput);
    }

    public String getsConfigDescription() {
        return sConfigDescription.get();
    }
	
    public SimpleStringProperty sConfigDescriptionProperty() {
        return sConfigDescription;
    }

    public void setsConfigDescription(String sInput) {
        this.sConfigDescription.set(sInput);
    }
		
}
