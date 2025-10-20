module ua.opnu.list {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    requires org.controlsfx.controls;

    opens ua.opnu.list to javafx.fxml;
    exports ua.opnu.list;
}