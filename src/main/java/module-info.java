module Kayttoliittymat {
    requires javafx.controls;
    requires javafx.fxml;

    opens fi.utu.sample to javafx.fxml;
    exports fi.utu.sample;
}