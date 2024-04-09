package com.example.introductiontose.controller.node;

import com.example.introductiontose.Application;
import com.example.introductiontose.controller.admin.hokhau.DanhSachNhanKhauController;
import com.example.introductiontose.controller.admin.hokhau.ThongTinNhanKhauController;
import com.example.introductiontose.controller.user.hokhau.TTNhanKhauController;
import com.example.introductiontose.dao.DataAccessObject;
import com.example.introductiontose.dao.HoKhauDAO;
import com.example.introductiontose.database.SqlConnection;
import com.example.introductiontose.model.HoKhau;
import com.example.introductiontose.model.NhanKhau;
import com.example.introductiontose.model.key.HoKhauKey;
import com.example.introductiontose.util.SQLUtils;
import com.example.introductiontose.view.icon.IconHoKhauController;
import com.example.introductiontose.view.icon.IconUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Pane hiển thị danh sách hộ khẩu hoặc danh sách nhân khẩu trong giao diện admin hoặc user.
 *
 * <p>Pane này có thể hiển thị danh sách hộ khẩu hoặc danh sách nhân khẩu dựa vào dữ liệu đầu vào.</p>
 *
 * <p>Nếu được sử dụng để hiển thị danh sách hộ khẩu, sẽ hiển thị các biểu tượng hộ khẩu.</p>
 *
 * <p>Nếu được sử dụng để hiển thị danh sách nhân khẩu, sẽ hiển thị các biểu tượng nhân khẩu.</p>
 *
 * <p>Pane này cũng có khả năng chuyển đến thông tin chi tiết của hộ khẩu hoặc nhân khẩu khi được nhấp vào biểu tượng tương ứng.</p>
 *
 * @author Duy
 * @version 1.0
 */
public class DanhSach extends Pane {
    private final Scene prevScene;
    
    @FXML
    private VBox mainVBox;
    
    @FXML
    private Pane paneContent;
    
    @FXML
    private ScrollPane scrollPane;
    
    /**
     * Constructor mặc định của DanhSach.
     */
    public DanhSach() {
        this(null);
    }
    
    /**
     * Constructor của DanhSach với scene trước đó.
     *
     * @param prevScene Scene trước đó để quay lại khi cần.
     */
    public DanhSach(Scene prevScene) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/introductiontose/view/danh-sach-Pane.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
        this.prevScene = prevScene;
        mainVBox.getChildren().clear();
    }
    
    /**
     * Phương thức hiển thị danh sách nhân khẩu.
     *
     * @param nhanKhauList Danh sách nhân khẩu cần hiển thị.
     */
    public void launch(List<NhanKhau> nhanKhauList) {
        mainVBox.getChildren().clear();
        try {
            HBox currentHBox = new HBox();
            for (NhanKhau nhanKhau : nhanKhauList) {
                if (currentHBox.getChildren().size() == 4) {
                    mainVBox.getChildren().add(currentHBox);
                    currentHBox = new HBox();
                }
                
                Button button = IconUtils.createButtonIcon(nhanKhau, this::eventClickIcon);
                currentHBox.getChildren().add(button);
            }
            if (!currentHBox.getChildren().isEmpty()) {
                mainVBox.getChildren().add(currentHBox);
            }
        } catch (IOException e) {
            // Xử lý lỗi phần mềm nếu cần thiết.
        }
    }
    
    /**
     * Phương thức hiển thị danh sách hộ khẩu.
     */
    public void launch() {
        mainVBox.getChildren().clear();
        Connection connection = SqlConnection.connect();
        DataAccessObject<HoKhau, HoKhauKey> accessObject = new HoKhauDAO(connection);
        List<HoKhau> danhSachHo = new ArrayList<>();
        
        try {
            danhSachHo = accessObject.getAll();
            SqlConnection.close(connection);
        } catch (SQLException e) {
            // Xử lý lỗi kết nối nếu cần thiết.
        }
        
        try {
            HBox currentHBox = new HBox();
            for (HoKhau hoKhau : danhSachHo) {
                if (currentHBox.getChildren().size() == 4) {
                    mainVBox.getChildren().add(currentHBox);
                    currentHBox = new HBox();
                }
                
                Button button = IconUtils.createButtonIcon(hoKhau, this::eventClickIcon);
                currentHBox.getStyleClass().add("transparent-hbox");
                currentHBox.getChildren().add(button);
            }
            if (!currentHBox.getChildren().isEmpty()) {
                currentHBox.getStyleClass().add("transparent-hbox");
                mainVBox.getChildren().add(currentHBox);
            }
        } catch (IOException e) {
            // Xử lý lỗi phần mềm nếu cần thiết.
        }
    }
    
    /**
     * Xử lý sự kiện khi nhấp vào biểu tượng nhân khẩu hoặc hộ khẩu.
     *
     * @param nhanKhau Thông tin nhân khẩu khi nhấp vào biểu tượng nhân khẩu.
     */
    private void eventClickIcon(NhanKhau nhanKhau) {
        try {
            if (prevScene != null) {
                String name = "/com/example/introductiontose/view/admin/hokhau/thong-tin-nhan-khau.fxml";
                FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource(name));
                Scene scene = mainVBox.getScene();
                Stage stage = (Stage) scene.getWindow();
                
                Scene nextScene = new Scene(fxmlLoader.load());
                ThongTinNhanKhauController controller = fxmlLoader.getController();
                
                controller.setTitle(nhanKhau);
                controller.setPrevStage(stage);
                controller.setPrevScene(scene);
                controller.setPrevPrevScene(prevScene);
                controller.setData(nhanKhau);
                stage.setScene(nextScene);
            } else {
                String name = "/com/example/introductiontose/view/user/hokhau/thong-tin-nhan-khau.fxml";
                FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource(name));
                Scene scene = mainVBox.getScene();
                Stage stage = (Stage) scene.getWindow();
                
                Scene nextScene = new Scene(fxmlLoader.load());
                TTNhanKhauController controller = fxmlLoader.getController();
                
                controller.setTitle(nhanKhau);
                controller.setPrevStage(stage);
                controller.setPrevScene(scene);
                controller.setPrevPrevScene(prevScene);
                controller.setData(nhanKhau);
                stage.setScene(nextScene);
            }
        } catch (IOException e) {
            // Xử lý lỗi phần mềm nếu cần thiết.
        }
    }
    
    /**
     * Xử lý sự kiện khi nhấp vào biểu tượng hộ khẩu.
     *
     * @param iconHoKhauController Controller của biểu tượng hộ khẩu.
     */
    private void eventClickIcon(IconHoKhauController iconHoKhauController) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("/com/example/introductiontose/view/admin/hokhau/danh-sach-nhan-khau.fxml"));
            Scene scene = mainVBox.getScene();
            Stage stage = (Stage) scene.getWindow();
            
            Scene nextScene = new Scene(fxmlLoader.load());
            DanhSachNhanKhauController controller = fxmlLoader.getController();
            controller.setTitle("Hộ khẩu " + iconHoKhauController.getData().getIdHoKhau());
            controller.setStage(stage);
            controller.setPrevScene(scene);
            
            List<NhanKhau> nhanKhauList = getNhanKhauList(iconHoKhauController);
            if (nhanKhauList == null) return;
            
            controller.launch(nhanKhauList);
            stage.setScene(nextScene);
        } catch (IOException e) {
            // Xử lý lỗi phần mềm nếu cần thiết.
        }
    }
    
    /**
     * Lấy danh sách nhân khẩu từ hộ khẩu.
     *
     * @param iconHoKhauController Controller của biểu tượng hộ khẩu.
     * @return Danh sách nhân khẩu thuộc hộ khẩu hoặc {@code null} nếu có lỗi.
     */
    private List<NhanKhau> getNhanKhauList(IconHoKhauController iconHoKhauController) {
        try {
            return SQLUtils.getNhanKhauFromHoKhau(iconHoKhauController.getData().getIdHoKhau());
        } catch (SQLException e) {
            // Xử lý lỗi kết nối nếu cần thiết.
        }
        return null;
    }
}
