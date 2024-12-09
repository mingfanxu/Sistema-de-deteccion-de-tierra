package com.wing.yjyw.utils;

import com.wing.yjyw.common.CustomException;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * @Description:将 Spring Framework 提供的 MultipartFile 类型的文件转换为 Java 的 File 对象。
 */
public class FileUtil {
    public static File convertMultipartFileToFile(MultipartFile multipartFile) {
        File tempFile;
        try {
            // 创建临时文件
            tempFile = File.createTempFile("temp", null);
            // 将MultipartFile的内容复制到临时文件中
            FileCopyUtils.copy(multipartFile.getInputStream(), Files.newOutputStream(tempFile.toPath()));
        } catch (IOException e) {
            throw new CustomException("图片文件转化失败");
        }
        return tempFile;
    }
}
