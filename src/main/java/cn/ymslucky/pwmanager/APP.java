package cn.ymslucky.pwmanager;

import cn.ymslucky.pwmanager.model.Point;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URISyntaxException;

public class APP extends Application {

    public static Stage stage;
    public static String user;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        initSetting(primaryStage);

        primaryStage.show();

        stage = primaryStage;
    }

    /**
     * 加载主页面
     */
    public static void load() throws IOException, URISyntaxException {
        stage.close();

        Stage main = new Stage();

        Parent root = FXMLLoader.load(APP.class.getResource("/fxml/main.fxml"));
        Scene scene = new Scene(root);

        main.setScene(scene);
        main.initStyle(StageStyle.UNDECORATED);

        main.show();
        initSetting(main);
    }

    /**
     * 初始设置,鼠标拖动
     */
    public static void initSetting(Stage stage) throws URISyntaxException {
        stage.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            //记录鼠标位置和窗口位置
            Point.MOUSE.setX(event.getScreenX());
            Point.MOUSE.setY(event.getScreenY());
            Point.Stage.setX(stage.getX());
            Point.Stage.setY(stage.getY());
        });
        stage.addEventHandler(MouseEvent.MOUSE_DRAGGED, event -> {
            if (Point.MOUSE.getX() != event.getScreenX() || Point.MOUSE.getY() != event.getScreenY()) {
                //移动
                stage.setX(Point.Stage.getX() + event.getScreenX() - Point.MOUSE.getX());
                stage.setY(Point.Stage.getY() + event.getScreenY() - Point.MOUSE.getY());
            }
        });

        //添加系统图标
        stage.getIcons().add(new Image(String.valueOf(APP.class.getResource("/img/lock.png").toURI())));
    }


    public static void main(String[] args) {
        launch(args);
    }
}
