module com.example.introductiontose {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires annotations;
    requires com.microsoft.sqlserver.jdbc;
    requires com.zaxxer.hikari;
    requires org.kordamp.ikonli.javafx;

    exports com.example.introductiontose;
    exports com.example.introductiontose.dao;
    exports com.example.introductiontose.model;
    exports com.example.introductiontose.controller;
    exports com.example.introductiontose.controller.dangkydangnhap;
    exports com.example.introductiontose.controller.hokhau;
    exports com.example.introductiontose.controller.guithongbao;
    exports com.example.introductiontose.controller.taoKhoanPhi;
    exports com.example.introductiontose.controller.admin;
    exports com.example.introductiontose.controller.admin.naptien;
    exports com.example.introductiontose.controller.node;
    exports com.example.introductiontose.controller.admin.hokhau;
    exports com.example.introductiontose.controller.user.hokhau;
    exports com.example.introductiontose.controller.dashboard;
    exports com.example.introductiontose.controller.trangchu;
    exports com.example.introductiontose.controller.thongke;
    exports com.example.introductiontose.controller.danhsachkhoanphi;
    exports com.example.introductiontose.controller.YeuCauNapTien;
    exports com.example.introductiontose.view.icon;
    
    opens com.example.introductiontose to javafx.fxml;
    opens com.example.introductiontose.dao to javafx.fxml;
    opens com.example.introductiontose.model to javafx.fxml;
    opens com.example.introductiontose.controller to javafx.fxml;
    opens com.example.introductiontose.controller.dangkydangnhap to javafx.fxml;
    opens com.example.introductiontose.controller.guithongbao to javafx.fxml;
    opens com.example.introductiontose.controller.hokhau to javafx.fxml;
    opens com.example.introductiontose.controller.taoKhoanPhi to javafx.fxml;
    opens com.example.introductiontose.controller.admin to javafx.fxml;
    opens com.example.introductiontose.controller.admin.naptien to javafx.fxml;
    opens com.example.introductiontose.controller.node to javafx.fxml;
    opens com.example.introductiontose.controller.admin.hokhau to javafx.fxml;
    opens com.example.introductiontose.controller.user.hokhau to javafx.fxml;
    opens com.example.introductiontose.controller.dashboard to javafx.fxml;
    opens com.example.introductiontose.controller.trangchu to javafx.fxml;
    opens com.example.introductiontose.controller.danhsachkhoanphi to javafx.fxml;
    opens com.example.introductiontose.view.icon to javafx.fxml;
    opens com.example.introductiontose.controller.thongke to javafx.fxml;
    opens com.example.introductiontose.controller.YeuCauNapTien to javafx.fxml;
}