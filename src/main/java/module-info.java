module com.example.mangastorecliente {
    requires javafx.controls;
    requires javafx.fxml;
            
        requires org.controlsfx.controls;
            requires com.dlsc.formsfx;
                    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires jasypt;
    requires java.desktop;

    opens com.example.mangastorecliente to javafx.fxml;
    exports com.example.mangastorecliente;
}