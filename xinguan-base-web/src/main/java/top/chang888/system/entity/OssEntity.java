package top.chang888.system.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author changyw
 * @date 2021/3/26
 */
@Data
@Component
public class OssEntity {

    @Value("${alioss.endpoint}")
    private String endpoint;

    @Value("${alioss.accessKeyId}")
    private String accessKeyId;

    @Value("${alioss.accessKeySecret}")
    private String accessKeySecret;

    @Value("${alioss.bucketName}")
    private String bucketName;
}
