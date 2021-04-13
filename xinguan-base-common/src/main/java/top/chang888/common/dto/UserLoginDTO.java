package top.chang888.common.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author changyw
 * @date 2021/4/13
 */

@Data
@Api(value = "用户登录表单")
public class UserLoginDTO {

    @NotNull(message = "用户名不许为空")
    @ApiModelProperty(value = "用户名", notes = "用户登录表单用户名")
    private String username;

    @NotNull(message = "密码不许为空")
    @ApiModelProperty(value = "密码", notes = "用户登录表单密码")
    private String password;

}
