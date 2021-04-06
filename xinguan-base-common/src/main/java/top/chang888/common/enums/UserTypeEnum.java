package top.chang888.common.enums;

/**
 * @author changyw
 * @date 2021/3/27
 */
public enum UserTypeEnum {

    /* 系统管理员 */
    SYSTEM_ADMIN(0),

    /* 系统管理员 */
    SYSTEM_USER(1),

    ;
    private int typeCode;

    UserTypeEnum(int typeCode) {
        this.typeCode = typeCode;
    }

    public int getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(int typeCode) {
        this.typeCode = typeCode;
    }
}
