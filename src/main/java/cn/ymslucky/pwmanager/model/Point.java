package cn.ymslucky.pwmanager.model;

import lombok.Data;

/**
 * 二维---点，辅助模型
 */
@Data
public class Point {
    public static Point MOUSE = new Point();
    public static Point Stage = new Point();

    private double X;
    private double Y;
}
