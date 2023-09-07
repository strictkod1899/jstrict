package ru.strict.view.swing;

import lombok.experimental.UtilityClass;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@UtilityClass
public class ImageUtil {

    public ImageIcon resizeImage(String imagePath, int width, int height) {
        BufferedImage bufferedImage;
        try {
            bufferedImage = ImageIO.read(new File(imagePath));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        return resizeImage(bufferedImage, width, height);
    }

    public ImageIcon resizeImage(File imageFile, int width, int height) {
        BufferedImage bufferedImage;
        try {
            bufferedImage = ImageIO.read(imageFile);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        return resizeImage(bufferedImage, width, height);
    }

    public ImageIcon resizeImage(InputStream imageInputStream, int width, int height) {
        BufferedImage bufferedImage;
        try {
            bufferedImage = ImageIO.read(imageInputStream);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        return resizeImage(bufferedImage, width, height);
    }

    public ImageIcon resizeImage(BufferedImage bufferedImage, int width, int height) {
        var cm = bufferedImage.getColorModel();
        var raster = cm.createCompatibleWritableRaster(width, height);
        var isRasterPremultiplied = cm.isAlphaPremultiplied();
        var target = new BufferedImage(cm, raster, isRasterPremultiplied, null);
        var g2 = target.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        var scalex = (double)target.getWidth() / (double)bufferedImage.getWidth();
        var scaley = (double)target.getHeight() / (double)bufferedImage.getHeight();
        var xform = AffineTransform.getScaleInstance(scalex, scaley);
        g2.drawRenderedImage(bufferedImage, xform);
        g2.dispose();

        return new ImageIcon(target);
    }
}
