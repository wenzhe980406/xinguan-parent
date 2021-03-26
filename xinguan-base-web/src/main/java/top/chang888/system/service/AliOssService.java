package top.chang888.system.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author changyw
 * @date 2021/3/26
 */
public interface AliOssService {

    /**
     * 创建存储空间
     */
    void createBucket();

    /**
     * 上传文件
     * @param file 文件对象
     * @return str
     */
    String upload(MultipartFile file);

    /**
     * 下载文件
     * @param fileName 文件名称
     * @throws IOException io异常
     */
    void download(String fileName) throws IOException;

    /**
     * 列举文件
     */
    void listFile();

    /**
     * 删除文件
     * @param fileName 文件名称
     */
    void deleteFile(String fileName);
}
