package ru.strict.utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

/**
 * Управление изображениями
 */
public class UtilImage {

    /**
     * Загрузка и изменение размера изображения
     * @param pathImage Путь к изображению
     * @param width новая ширина
     * @param height новая высота
     * @return
     */
    public static ImageIcon resizeImage(String pathImage, int width, int height) {
        UtilLogger.info(UtilImage.class, "resizeImage - started");
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(new File(pathImage));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ColorModel cm = bufferedImage.getColorModel();
        WritableRaster raster = cm.createCompatibleWritableRaster(width, height);
        boolean isRasterPremultiplied = cm.isAlphaPremultiplied();
        BufferedImage target = new BufferedImage(cm, raster, isRasterPremultiplied, null);
        Graphics2D g2 = target.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        double scalex = (double) target.getWidth()/ bufferedImage.getWidth();
        double scaley = (double) target.getHeight()/ bufferedImage.getHeight();
        AffineTransform xform = AffineTransform.getScaleInstance(scalex, scaley);
        g2.drawRenderedImage(bufferedImage, xform);
        g2.dispose();
        ImageIcon iconBack = new ImageIcon(target);
        UtilLogger.info(UtilImage.class, "resizeImage - finished");
        return iconBack;
    }
}
