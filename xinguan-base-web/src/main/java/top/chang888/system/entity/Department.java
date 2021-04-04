package top.chang888.system.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author changyw
 * @since 2021-03-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_department")
@ApiModel(value="Department对象", description="部门表")
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "系名")
    private String name;

    @ApiModelProperty(value = "系办公电话")
    private String phone;

    @ApiModelProperty(value = "办公室地点")
    private String address;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date modifiedTime;

    @ApiModelProperty(value = "系主任id，关联用户表")
    private Long mgrId;

    @ApiModelProperty(value = "当前部门人数")
    @TableField(exist = false)
    private Integer deptCount;
}
