package com.icfcc.modules.sys;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 系统管理，安全相关实体的管理类,包括用户、角色、菜单.
 * Created by admin on 2015/8/17.
 */
@Service
@Transactional(readOnly = true)
public class SystemService {
}
