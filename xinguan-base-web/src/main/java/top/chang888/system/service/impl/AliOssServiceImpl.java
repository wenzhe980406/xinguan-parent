package top.chang888.system.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CreateBucketRequest;
import com.aliyun.oss.model.PutObjectRequest;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.chang888.system.entity.OssEntity;
import top.chang888.system.service.AliOssService;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * @author changyw
 * @date 2021/3/26
 */

@Service
public class AliOssServiceImpl implements AliOssService, InitializingBean {

    @Autowired
    private OssEntity ossEntity;

    private String endpoint;

    private String accessKeyId;

    private String accessKeySecret;

    private String bucketName;

    /**
     * 初始化bean之后装载
     * @throws Exception 1
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        endpoint = ossEntity.getEndpoint();
        accessKeyId = ossEntity.getAccessKeyId();
        accessKeySecret = ossEntity.getAccessKeySecret();
        bucketName = ossEntity.getBucketName();
    }

    /**
     * 创建存储空间
     */
    @Override
    public void createBucket() {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 创建CreateBucketRequest对象。

        if (ossClient.doesBucketExist(bucketName)) {
            throw new RuntimeException(bucketName + " 在对象存储的列表中已存在");
        }
        CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
        // 创建存储空间。
        ossClient.createBucket(createBucketRequest);

        // 关闭OSSClient。
        ossClient.shutdown();
    }

    /**
     * 上传文件
     *
     * @param file 文件对象
     * @return str
     */
    @Override
    public String upload(MultipartFile file) {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 填写字符串。
        String content = "Hello OSS";

        // 创建PutObjectRequest对象。
        // 填写Bucket名称和Object完整路径。Object完整路径中不能包含Bucket名称。
        PutObjectRequest putObjectRequest = null;
        try {
            putObjectRequest = new PutObjectRequest("examplebucket", "exampleobject.txt",
                    file.getInputStream());
            // 上传字符串。
            ossClient.putObject(putObjectRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }



        // 关闭OSSClient。
        ossClient.shutdown();
        return "ok";
    }

    /**
     * 下载文件
     *
     * @param fileName 文件名称
     * @throws IOException io异常
     */
    @Override
    public void download(String fileName) throws IOException {

    }

    /**
     * 列举文件
     */
    @Override
    public void listFile() {

    }

    /**
     * 删除文件
     *
     * @param fileName 文件名称
     */
    @Override
    public void deleteFile(String fileName) {

    }

}
