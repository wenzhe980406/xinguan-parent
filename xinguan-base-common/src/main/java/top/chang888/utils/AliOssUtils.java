package top.chang888.utils;

import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.*;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author changyw
 * @date 2021/3/27
 */

public class AliOssUtils {

    private final static String endpoint = "oss-cn-shanghai.aliyuncs.com";

    private final static String accessKeyId = "LTAI5tMXvCuxnAaZR6DE6bEk";

    private final static String accessKeySecret = "lEmcn6SCUy5eNGvkhdOrjjXuqjkD7m";

    private final static String bucketName = "admin-parent";

    /**
     * 创建存储空间
     */
    public static void createBucket() {

        // 创建CreateBucketRequest对象。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

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
    public static String upload(MultipartFile file) {
        // 上传的地址。
        String uploadUrl = "";
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        try {

            if (!ossClient.doesBucketExist(bucketName)) {
                ossClient.createBucket(bucketName);
                ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicReadWrite);
            }

            InputStream inputStream = file.getInputStream();

            // 构建日期的文件夹路径 avatar/2021/03/27
            String datePath = new DateTime().toString("yyyy/MM/dd");

            // 获取上传文件的全名称
            String filename = file.getOriginalFilename();
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");

            String fileType = filename.substring(filename.lastIndexOf("."));

            String newFileName= "avatar/" + datePath + "/" + uuid + fileType;

            // 如果要实现图片预览，一定要设置以下几点
            // 1. 设置文件的acl权限 公共读/写
            // 2. 一定要设置文本类型(image/jpeg)
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setObjectAcl(CannedAccessControlList.PublicRead);
            objectMetadata.setContentType(getContentType(fileType));
            objectMetadata.setHeader("x-oss-storage-class", StorageClass.IA);

            // 创建PutObjectRequest对象。
            // 填写Bucket名称和Object完整路径。Object完整路径中不能包含Bucket名称。
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, newFileName, file.getInputStream(),
                    objectMetadata);

            // 上传文件。
            ossClient.putObject(putObjectRequest);
            uploadUrl = getFileUrl(newFileName, ossClient);
            ossClient.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return uploadUrl;
    }

    /**
     * 下载文件
     *
     * @param fileName 文件名称
     * @throws IOException io异常
     */
    public static void download(String fileName) throws IOException {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // ossObject包含文件所在的存储空间名称、文件名称、文件元信息以及一个输入流。
        OSSObject ossObject = ossClient.getObject(bucketName, fileName);

        // 读取文件内容。
        BufferedReader reader = new BufferedReader(new InputStreamReader(ossObject.getObjectContent()));
        while (true) {
            String line = reader.readLine();
            if (line == null) {
                break;
            }

            System.out.println("\n" + line);
        }
        // 数据读取完成后，获取的流必须关闭，否则会造成连接泄漏，导致请求无连接可用，程序无法正常工作。
        reader.close();

        // 关闭OSSClient。
        ossClient.shutdown();
    }


    /**
     * 列举文件
     */
    public static List<String> listFile() {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 列举文件。如果不设置KeyPrefix，则列举存储空间下的所有文件。如果设置KeyPrefix，则列举包含指定前缀的文件。
        ObjectListing objectListing = ossClient.listObjects(bucketName);
        List<OSSObjectSummary> sums = objectListing.getObjectSummaries();
        List<String> list = new ArrayList<>();
        for (OSSObjectSummary s : sums) {
            list.add(s.getKey());
        }

        // 关闭OSSClient。
        ossClient.shutdown();
        return list;
    }

    /**
     * 删除文件
     *
     * @param fileName 文件名称
     */
    public static void deleteFile(String fileName) {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        ossClient.deleteObject(bucketName, fileName);
        ossClient.shutdown();
    }

    /**
     * Description: 判断OSS服务文件上传时文件的contentType
     *
     * @param filenameExtension 文件后缀
     * @return String
     */
    public static String getContentType(String filenameExtension) {
        if (".bmp".equalsIgnoreCase(filenameExtension)) {
            return "image/bmp";
        }
        if (".gif".equalsIgnoreCase(filenameExtension)) {
            return "image/gif";
        }
        if (".jpeg".equalsIgnoreCase(filenameExtension) ||
                ".jpg".equalsIgnoreCase(filenameExtension) ||
                ".png".equalsIgnoreCase(filenameExtension)) {
            return "image/jpg";
        }
        if (".html".equalsIgnoreCase(filenameExtension)) {
            return "text/html";
        }
        if (".txt".equalsIgnoreCase(filenameExtension)) {
            return "text/plain";
        }
        if (".vsd".equalsIgnoreCase(filenameExtension)) {
            return "application/vnd.visio";
        }
        if (".pptx".equalsIgnoreCase(filenameExtension) ||
                ".ppt".equalsIgnoreCase(filenameExtension)) {
            return "application/vnd.ms-powerpoint";
        }
        if (".docx".equalsIgnoreCase(filenameExtension) ||
                ".doc".equalsIgnoreCase(filenameExtension)) {
            return "application/msword";
        }
        if (".xml".equalsIgnoreCase(filenameExtension)) {
            return "text/xml";
        }
        return "image/jpg";
    }

    /**
     * 通过文件名从oss中获取文件访问url地址
     * @param filename 文件名
     * @param ossClient oss连接器
     * @return url
     */
    public static String getFileUrl(String filename, OSS ossClient) {
        // 默认10年不过期
        Date expiration = new Date(System.currentTimeMillis() + 1000 * 60 * 10 );
        GeneratePresignedUrlRequest req = new GeneratePresignedUrlRequest(bucketName, filename, HttpMethod.GET);
        req.setExpiration(expiration);
        String url = ossClient.generatePresignedUrl(req).toString();
        return url.substring(0, url.indexOf("?"));
    }

}
