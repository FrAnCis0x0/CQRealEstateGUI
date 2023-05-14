module cqrealestatepackage {
    requires javafx.controls;
    requires javafx.fxml;

    opens cqrealestatepackage to javafx.fxml;
    opens cqrealestatepackage.controller to javafx.fxml;
    exports cqrealestatepackage;
}
