package top.chang888.system.service.impl;

import top.chang888.common.entity.Log;
import top.chang888.system.mapper.LogMapper;
import top.chang888.system.service.LogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 操作日志表 服务实现类
 * </p>
 *
 * @author changyw
 * @since 2021-04-06
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {

}
