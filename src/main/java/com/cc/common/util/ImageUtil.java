package com.cc.common.util;

import com.cc.exception.BizException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class ImageUtil {

    /**
     * 上传图片
     *
     * @param imageFile    图片文件
     * @param savePath     保存物理路径
     * @param relativePath 相对路径
     * @param imageSize    图片尺寸限制大小（单位：KB）
     * @return 图片地址（存于数据库）
     */
    public static String uploadImage(MultipartFile imageFile, String savePath, String relativePath, Integer imageSize) {

        // 图片大小验证，默认500K
        checkFile(imageFile, imageSize);

        // 获取图片的文件名
        String fileName = imageFile.getOriginalFilename();

        // 获取图片的扩展名
        String extensionName = fileName.substring(fileName.lastIndexOf(".") + 1);

        // 新的图片文件名 = 获取时间戳+"."图片扩展名
        // String newFileName = String.valueOf(System.currentTimeMillis())+ "." + extensionName;
        String newFileName = UUID.randomUUID() + "." + extensionName;

        // 服务器存图片路径
        File saveFile = new File(savePath);
        if (!saveFile.exists()) {
            saveFile.mkdirs();
        }

        // 保存图片
        String filePath = newFileName;
        File file = new File(filePath);
        try {
            imageFile.transferTo(file);
        } catch (IllegalStateException | IOException e) {
            throw new BizException("上传文件失败！", e);
        }

        // 图片存放相对路径-存于数据的路径
        String imgPath = relativePath + newFileName;

        return imgPath;
    }

    /**
     * 将文件重新命名成唯一
     *
     * @param imageFile
     * @return
     */
    public static String reNameFile(MultipartFile imageFile) {

        // 获取图片的文件名
        String fileName = imageFile.getOriginalFilename();

        // 获取图片的扩展名
        String extensionName = fileName.substring(fileName.lastIndexOf(".") + 1);

        // 新的图片文件名 = 获取时间戳+"."图片扩展名
        // String newFileName = String.valueOf(System.currentTimeMillis())+ "." + extensionName;
        String newFileName = UUID.randomUUID() + "." + extensionName;

        return newFileName;
    }

    /**
     * @param imageFile
     * @param imageSize
     */
    public static void checkFile(MultipartFile imageFile, Integer imageSize) {
        // 图片大小验证，默认500K
        if (imageSize == null) {
            imageSize = 500;
        }
        Long size = imageFile.getSize();
        Long limitedSize = 1024l * imageSize;
        if (limitedSize < size) {
            throw new BizException("图片大小不能超过" + imageSize + "KB!");
        }
    }

}