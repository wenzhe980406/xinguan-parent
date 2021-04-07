package top.chang888.system.service.impl;

import top.chang888.common.entity.LoginLog;
import top.chang888.system.mapper.LoginLogMapper;
import top.chang888.system.service.LoginLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 登录日志表 服务实现类
 * </p>
 *
 * @author changyw
 * @since 2021-04-06
 */
@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog> implements LoginLogService {

}
