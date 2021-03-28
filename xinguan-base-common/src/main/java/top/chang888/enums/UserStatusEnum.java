package top.chang888.enums;

/**
 * @author changyw
 * @date 2021/3/27
 */
public enum UserStatusEnum {

    /* 锁定 */
    AVAILABLE(0),

    /* 有效 */
    DISABLE(1),

    ;
    private int typeCode;

    UserStatusEnum(int typeCode) {
        this.typeCode = typeCode;
    }

    public int getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(int typeCode) {
        this.typeCode = typeCode;
    }
}
