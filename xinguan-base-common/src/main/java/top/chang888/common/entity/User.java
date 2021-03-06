package top.chang888.common.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author changyw
 * @since 2021-03-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_user")
@ApiModel(value="User对象", description="用户表")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户ID")
    @ExcelProperty(value = "用户ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户名")
    @ExcelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "昵称")
    @ExcelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "邮箱")
    @ExcelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "头像")
    @ExcelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "联系电话")
    @ExcelProperty(value = "联系电话")
    private String phoneNumber;

    @ApiModelProperty(value = "状态 0锁定 1有效")
    @ExcelProperty(value = "状态 0锁定 1有效")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    @ExcelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    @ExcelProperty(value = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date modifiedTime;

    @ApiModelProperty(value = "性别 0男 1女 2保密")
    @ExcelProperty(value = "性别 0男 1女 2保密")
    private Integer sex;

    @ApiModelProperty(value = "盐")
    @ExcelProperty(value = "盐")
    private String salt;

    @ApiModelProperty(value = "0:超级管理员，1：系统用户")
    @ExcelProperty(value = "0:超级管理员，1：系统用户")
    private Integer type;

    @ApiModelProperty(value = "密码")
    @ExcelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "生日")
    @ExcelProperty(value = "生日")
    @DateTimeFormat(value = "yyyy年MM月dd日 HH时mm分ss秒")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date birth;

    @ApiModelProperty(value = "部门ID")
    @ExcelProperty(value = "部门ID")
    private Long departmentId;

    @TableLogic(value = "0", delval = "1")
    @ApiModelProperty(value = "逻辑删除")
    @ExcelProperty(value = "逻辑删除")
    private Boolean deleted;

    @ApiModelProperty(value = "部门名称")
    @ExcelProperty(value = "部门名称")
    @TableField(exist = false)
    private String name;

}
