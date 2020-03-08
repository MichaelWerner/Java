package net._4werners.SCD;

import javafx.beans.property.SimpleStringProperty;

public class SCDData {
    private SimpleStringProperty sDate;
    private SimpleStringProperty sType;

    public SCDData(String sDate, String sType) {
        this.sDate = new SimpleStringProperty(sDate);
        this.sType = new SimpleStringProperty(sType);
    }

    public String getsType() {
        return sType.get();
    }

    public SimpleStringProperty sTypeProperty() {
        return sType;
    }

    public void setsType(String sType) {
        this.sType.set(sType);
    }

    public String getsDate() {
        return sDate.get();
    }

    public SimpleStringProperty sDateProperty() {
        return sDate;
    }

    public void setsDate(String sDate) {
        this.sDate.set(sDate);
    }

}
