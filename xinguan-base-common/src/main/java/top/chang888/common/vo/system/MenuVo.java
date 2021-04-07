package top.chang888.common.vo.system;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author changyw
 * @date 2021/4/7
 */
@Data
public class MenuVo {

    private Long id;

    @NotNull(message = "父ID不许为空")
    private Long parentId;

    @NotNull(message = "菜单名称不能为空")
    private String menuName;

    private String url;

    private String perms;

    private String icon;

    @NotNull(message = "菜单类型不能为空")
    private String type;

    @NotNull(message = "排序不能为空")
    private Long orderNum;

    private Date createTime;

    private Date modifiedTime;

    @NotNull(message = "菜单状态不能为空")
    private Boolean disabled;

    private Integer open;
}
