package net._4werners.supi;

import javafx.beans.property.SimpleStringProperty;
import java.lang.String;

public class SupplyData {
	private final SimpleStringProperty sPart;
	private final SimpleStringProperty sType;
	private final SimpleStringProperty sPlace;
	private final SimpleStringProperty sShelf;
	private final SimpleStringProperty sContainer;
	private final SimpleStringProperty sQuantity;
	
	public SupplyData(String sNewPart, String sNewType, String sNewPlace, String sNewShelf, String sNewContainer, String sNewQuantity) {
        this.sPart = new SimpleStringProperty(sNewPart);
        this.sType = new SimpleStringProperty(sNewType);
        this.sPlace = new SimpleStringProperty(sNewPlace);
        this.sShelf = new SimpleStringProperty(sNewShelf);
        this.sContainer = new SimpleStringProperty(sNewContainer);
        this.sQuantity = new SimpleStringProperty(sNewQuantity);
    }	
	
	
	public void setsPart(String sNewPart) {
		sPart.set(sNewPart);
	}
    
	public String getsPart() {
        return sPart.get();
    }
    
	public SimpleStringProperty sPartProperty() {
        return sPart;
    }
    
	public void setsType(String sNewType) {
		sType.set(sNewType);
	}
    
	public String getsType() {
        return sType.get();
    }

	public SimpleStringProperty sTypeProperty() {
        return sType;
    }	
	
	public void setsPlace(String sNewPlace) {
		sPlace.set(sNewPlace);
	}
	
	public String getsPlace() {
        return sPlace.get();
    }

	public SimpleStringProperty sPlaceProperty() {
		return sPlace;
	}
	
	public void setsShelf(String sNewShelf) {
		sShelf.set(sNewShelf);
	}
    
	public String getsShelf() {
        return sShelf.get();
    }
	
	public SimpleStringProperty sShelfProperty() {
		return sShelf;
	}
	
	public void setsContainer(String sNewContainer) {
		sContainer.set(sNewContainer);
	}
    
	public String getsContainer() {
        return sContainer.get();
    }	
	
	public SimpleStringProperty sContainerProperty() {
		return sContainer;
	}
	
	public void setsQuantity(String sNewQuantity) {
		sQuantity.set(sNewQuantity);
	}
    
	public String getsQuantity() {
        return sQuantity.get();
    }
	
	public SimpleStringProperty sQuantityProperty() {
		return sQuantity;
	}
}
