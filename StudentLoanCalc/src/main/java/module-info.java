module StudentLoanCalc {
	
	exports app;
	requires javafx.base;
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;
	requires javafx.media;
	requires poi;
	requires java.base;
	
	opens pkgLogic to javafx.base;

	opens app.controller to javafx.fxml;
}