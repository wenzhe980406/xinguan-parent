package top.chang888.common.vo.system;

import lombok.Data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author changyw
 * @date 2021/4/7
 */
@Data
public class MenuNodeVo {

    private Long id;

    private Long parentId;

    private String menuName;

    private String url;

    private String icon;

    private Long orderNum;

    private boolean disabled;

    private String perms;

    private Integer type;

    private List<MenuNodeVo> children = new ArrayList<>();

    public MenuNodeVo() {
        url = "";
    }

    public MenuNodeVo(Long parentId, String menuName, Long orderNum, boolean disabled, Integer type) {
        this.parentId = parentId;
        this.menuName = menuName;
        this.orderNum = orderNum;
        this.disabled = disabled;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Long getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Long orderNum) {
        this.orderNum = orderNum;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<MenuNodeVo> getChildren() {
        return children;
    }

    public void setChildren(List<MenuNodeVo> children) {
        this.children = children;
    }

    public static Comparator<MenuNodeVo> comparator() {
        return (o1, o2) -> (int) (o1.getOrderNum() - o2.getOrderNum());
    }
}
