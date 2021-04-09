package top.chang888.common.vo.system;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author changyw
 * @date 2021/4/9
 */
@Data
public class RoleVo {

    private Long id;

    @NotNull(message = "角色名为必填项")
    private String roleName;

    @NotNull(message = "角色描述信息为必填项")
    private String remark;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date createTime;

    private Date modifiedTime;

    private Boolean status;

}
