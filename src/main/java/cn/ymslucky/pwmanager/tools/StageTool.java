package cn.ymslucky.pwmanager.tools;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.net.URISyntaxException;

@Slf4j
public class StageTool {

    private static Stage INFO;
    private static Label LABEL;

    static {
        INFO = new Stage();
        LABEL = new Label();
        BorderPane borderPane = new BorderPane();
        LABEL.setStyle("-fx-font-size: 36px Bold");
        LABEL.setPadding(new Insets(0, 0, 0, 20));
        borderPane.setCenter(LABEL);
        INFO.setScene(new Scene(borderPane, 250, 150));
        INFO.setResizable(false);
        INFO.initModality(Modality.APPLICATION_MODAL);
        INFO.setTitle("提示！");

        try {
            INFO.getIcons().add(new Image(String.valueOf(StageTool.class.getResource("/img/lock.png").toURI())));
        } catch (URISyntaxException e) {
            log.warn("图片（/img/lock.png）加载失败！");
            log.error(e.getMessage());
        }
    }

    public static void INFO(String message) {
        LABEL.setText(message);

        INFO.show();
    }
}