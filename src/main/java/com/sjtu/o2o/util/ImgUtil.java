package com.sjtu.o2o.util;


import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Position;
import net.coobird.thumbnailator.geometry.Positions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author ：Fang Jiangjing
 * @date ：Created in 2022/10/8 11:57
 * @description：图片工具类
 * @modified By：
 * @version: $
 */
public class ImgUtil {
    private static final Logger logger = LoggerFactory.getLogger(ImgUtil.class);

    private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final Random r = new Random();

    public static void main(String[] args) throws IOException {
        Thumbnails.Builder<File> size = Thumbnails.of(new File("/Users/wall-f/codeTest/water.jpg")).size(200, 200);
        System.out.println(size);
        BufferedImage read = ImageIO.read(new File("/Users/wall-f/codeTest/test.jpg"));
        System.out.println(read);

        Thumbnails.Builder<File> watermark = size.watermark(Positions.BOTTOM_RIGHT,
                read, 0.25f);
        watermark.outputQuality(0.8f)
                .toFile("/Users/wall-f/codeTest/newTest.jpg");
    }

    /**
     * 处理缩略图，返回图片新生成的文件名
     * @param thumbnail
     * @param targetAddr
     * @return
     */
    public static String generateThumbnail(InputStream thumbnail, String fileName, String targetAddr){
        String realFileName = getRandomFileName();
        String extension = getFileExtension(fileName);
        makeDirPath(targetAddr);
        String relativeAddr = targetAddr + realFileName + extension;
        logger.debug("生成图片的文件名是:[{}]",relativeAddr);
        File dest = new File(PathUtil.getImgBasePath()+relativeAddr);
        logger.debug("生成图片的绝对路径是：[{}]",dest.toString());
        try{
            System.out.println(basePath);
            Thumbnails.of(thumbnail).size(200,200)
                      .watermark(Positions.BOTTOM_RIGHT,
                                 ImageIO.read(new File( basePath + "/watermark.jpg")),
                                 0.25f)
                      .outputQuality(0.8f).toFile(dest);
        } catch (IOException e) {
            logger.error("生成压缩图片错误" + e);
            e.printStackTrace();
        }
        return relativeAddr;
    }
    /**
     * 生成随机文件名，当前年月日小时分钟秒+5位随机数
     * @return
     */
    public static String getRandomFileName(){
        int rannum = r.nextInt(89999) + 10000;
        String nowTimeStr = sDateFormat.format(new Date());
        return nowTimeStr + rannum;
    }

    /**
     * 获取输入文件流的扩展名
     * @param fileName
     * @return
     */
    private static String getFileExtension(String fileName){
        return fileName.substring(fileName.lastIndexOf("."));
    }

    private static void makeDirPath(String targetAddr){
        String realFileParentPath = PathUtil.getImgBasePath() + targetAddr;
        File dirPath = new File(realFileParentPath);
        if(!dirPath.exists()){
            dirPath.mkdirs();
        }
    }

    public static File transferCommonMultipartFileToFile(CommonsMultipartFile cFile){
        File newFile = new File(cFile.getOriginalFilename());
        try {
            cFile.transferTo(newFile);
        } catch (IOException e) {
            logger.error("转换文件流异常" + e);
            e.printStackTrace();
        }
        return newFile;
    }
}
