module net._4werners.supi {
    requires transitive javafx.controls;
    requires transitive javafx.fxml;
	requires transitive javafx.base;
	requires transitive java.sql;
	
    opens net._4werners.supi to javafx.fxml;
    exports net._4werners.supi;
}