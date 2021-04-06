package top.chang888.common.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 字段创建的时候 自动增加创建和修改时间
 * @author changyw
 * @date 2021/3/28
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");

        // 起始版本 3.3.0(推荐使用)
        this.strictInsertFill(metaObject, "createTime", Date.class, new Date());
        // 在增加的时候 不仅要给create时间 也要给modifiedTime时间
        this.strictInsertFill(metaObject, "modifiedTime", Date.class, new Date());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        // 起始版本 3.3.0(推荐使用)
        this.strictUpdateFill(metaObject, "modifiedTime", Date.class, new Date());

    }
}
