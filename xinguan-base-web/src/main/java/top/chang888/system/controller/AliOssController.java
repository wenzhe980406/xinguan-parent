package top.chang888.system.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.chang888.response.Result;
import top.chang888.utils.AliOssUtils;

/**
 * 阿里云对象存储模块
 * @author changyw
 * @date 2021/3/27
 */

@Api(value = "阿里云oss对象存储")
@RequestMapping("/alioss")
@RestController
public class AliOssController {

    @ApiOperation(value = "上传文件")
    @PostMapping("/upload")
    public Result uploadFile(MultipartFile file) {
        String uploadUrl = AliOssUtils.upload(file);
        return Result.ok().data("url", uploadUrl);
    }

    @ApiOperation(value = "删除文件")
    @DeleteMapping("/delete")
    public Result deleteFile(String file) {
        try {
            String[] split = file.split(".com/");
            AliOssUtils.deleteFile(split[1]);
            return Result.ok();
        }catch (Exception e) {
            return Result.error();
        }
    }


}
