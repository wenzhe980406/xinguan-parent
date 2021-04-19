package top.chang888.common.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import top.chang888.common.vo.system.UserInfoVo;

import javax.validation.constraints.NotNull;
import java.util.Collection;

/**
 * @author changyw
 * @date 2021/4/13
 */

@Api(value = "用户登录表单")
@Data
@ToString
public class UserLoginDTO implements UserDetails {

    @NotNull(message = "用户名不许为空")
    @ApiModelProperty(value = "用户名", notes = "用户登录表单用户名")
    private String username;

    @NotNull(message = "密码不许为空")
    @ApiModelProperty(value = "密码", notes = "用户登录表单密码")
    private String password;

    @ApiModelProperty(value = "状态", notes = "用户当前账号是否锁定")
    private Boolean enabled;

    @ApiModelProperty(value = "角色", notes = "用户绑定的角色")
    private Collection<? extends GrantedAuthority> authorities;

    @ApiModelProperty(value = "用户Vo对象", notes = "该用户页面显示信息")
    private UserInfoVo userInfoVo;

    public UserLoginDTO() {
    }

    public UserLoginDTO(@NotNull(message = "用户名不许为空") String username, @NotNull(message = "密码不许为空") String password) {
        this.username = username;
        this.password = password;
        this.enabled = true;
        this.authorities = null;
    }

    public UserLoginDTO(@NotNull(message = "用户名不许为空") String username, @NotNull(message = "密码不许为空") String password,
                        Boolean enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.authorities = null;
    }

    public UserLoginDTO(@NotNull(message = "用户名不许为空") String username, @NotNull(message = "密码不许为空") String password,
                        Boolean enabled, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.authorities = authorities;
    }

    public UserInfoVo getUserInfoVo() {
        return userInfoVo;
    }

    public void setUserInfoVo(UserInfoVo userInfoVo) {
        this.userInfoVo = userInfoVo;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
