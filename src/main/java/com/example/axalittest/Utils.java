package com.example.axalittest;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.opencv_core.Scalar;
import org.bytedeco.opencv.opencv_core.Size;
import org.opencv.core.CvType;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

import static org.bytedeco.opencv.global.opencv_imgcodecs.imread;
import static org.bytedeco.opencv.global.opencv_imgproc.*;

public class Utils {

    // Не забудьте сделать matFromPath.release(); чтоб очистить память, сама она не чистится
    public static Mat getMatFromPath(String path) {
        Mat matFromPath = imread(path);

        if (matFromPath.empty()) {
            System.out.println("!!!!! Failed to load image in the following path:" + "\n" + path);
        } else {
            System.out.println("Image loaded successfully");
        }

        return matFromPath;
    }

    // можно убрать конвертацию в бгр, но скорее всего если брать как есть с картинки он может вернтуь в бгр изначально
    public static Mat getMatFromByteArray(byte[] image, int width, int height) {
        Mat mat = new Mat(new Size(width, height), CvType.CV_8UC3);
        mat.data().put(image, 0, image.length);
//        cvtColor(mat, mat, COLOR_RGB2BGR);
        return mat;
    }

    public static byte[] getByteArrayFromMat(Mat mat, int channels) {
        byte[] destination = new byte[mat.size().height() * mat.size().width() * channels];
        mat.data().get(destination);
        return destination;
    }

    public static BufferedImage convertByteArrayToBufferedImage(byte[] byteArray, int channels, int width, int height) {
        int type = getBufferedImgTypeByMatChannels(channels);
        BufferedImage bufferedImage = new BufferedImage(width, height, type);
        byte[] data = ((DataBufferByte) bufferedImage.getRaster().getDataBuffer()).getData();
        System.arraycopy(byteArray, 0, data, 0, byteArray.length);
        return bufferedImage;
    }

    public static Image toFXImage(BufferedImage bufferedImage) {
        return SwingFXUtils.toFXImage(bufferedImage, null);
    }

    public static int getBufferedImgTypeByMatChannels(int channels) {
        Integer type = null;
        if (channels == 1)
            type = BufferedImage.TYPE_BYTE_GRAY;
        else if (channels == 3)
            type = BufferedImage.TYPE_3BYTE_BGR;
        else if (channels == 4)
            type = BufferedImage.TYPE_4BYTE_ABGR;
        if (type == null) {
//            Logger.log("Unknown channel number " + channels, LogType.ERROR);
            /*юзаем тип для трехканальной матрицы*/
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        return type;
    }

    /**
     * @param srcMat - 3-х канальная матрица
     * @return возвращает бинарную матрицу
     */
    public static Mat grayMat(Mat srcMat) {

        // Создаем новую матрицу для хранения оттенков серого
        Mat grayMat = new Mat();

        // Преобразование цветового пространства из BGR в оттенки серого
        cvtColor(srcMat, grayMat, COLOR_BGR2GRAY);
        return grayMat;
    }

    /**
     * на вход получает бинарную матрицу, которая проходит через пороговое значение
     * @param srcMat
     * @return возвращает бинарную матрицу, которая отобрана по какому-то выбранному порогу
     * возможно переделать чтоб на выход и вход были не бинарные матрицы
     */
    public static Mat thresholdMat(Mat srcMat, double thresholdValue, double maxValue, int thresholdType) {
        Mat thresholdMat = new Mat();

        // Применяем пороговую обработку к входной матрице
        threshold(srcMat, thresholdMat, thresholdValue, maxValue, thresholdType);

        return thresholdMat;
    }

    /**
     * @param srcMat
     * @return MatVector
     */
    public static MatVector findContoursOnMat(Mat srcMat) {

        MatVector srcMatContours = new MatVector();

        findContours(srcMat, srcMatContours, RETR_EXTERNAL, CHAIN_APPROX_SIMPLE);

        return srcMatContours;
    }

    /**
     * Метод отрисовывает контуры на оригинальной матрицу
     * @param srcMat
     */
    public static Mat drawContoursOnMat(Mat srcMat, MatVector contours, int contourIdx, Scalar color) {
        Mat counteredSrcMat = new Mat(srcMat);
        drawContours(counteredSrcMat, contours, contourIdx, color);
        return counteredSrcMat;
    }
}
