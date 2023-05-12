module HarryPotterAtHome_graphic {
    requires javafx.controls;
    requires javafx.fxml;

    exports play;
    opens play to javafx.fxml;

}