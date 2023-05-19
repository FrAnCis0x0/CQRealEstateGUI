module cqrealestatepackage {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens cqrealestatepackage to javafx.fxml;
    opens cqrealestatepackage.controller to javafx.fxml;
    opens cqrealestatepackage.model to javafx.base;
    exports cqrealestatepackage;
}
