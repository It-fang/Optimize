package com.itfang.www.bbl.servic;

import com.itfang.www.dal.dao.AdminUserDao;
import com.itfang.www.dal.dao.AdminUserDaoImpl;
import com.itfang.www.dal.po.AdminUser;
import com.itfang.www.dal.po.ResultInfo;

import java.sql.SQLException;

/**
 * @author it-fang
 * @Description 学生用户的所有相关操作
 * @date 2020-4-27
 */
public class AdminServiceImpl implements AdminService {
    AdminUserDao adminUserDao = new AdminUserDaoImpl();
    ResultInfo resultInfo = new ResultInfo();

    @Override
    public Object login(AdminUser adminUser) throws SQLException {
        if ("".equals(adminUser.getUsername())){
            resultInfo.setStatus(false);
            resultInfo.setMessage("用户名不能为空！");
            return resultInfo;
        }
        if ("".equals(adminUser.getPassword())){
            resultInfo.setStatus(false);
            resultInfo.setMessage("密码不能为空！");
            return resultInfo;
        }
        boolean status = adminUserDao.isExit(adminUser.getUsername());
        if (status){
            status = adminUserDao.checkPassword(adminUser);
            if (status){
                resultInfo.setStatus(true);
                resultInfo.setMessage("欢迎使用," + adminUser.getUsername());
            }else {
                resultInfo.setStatus(false);
                resultInfo.setMessage("密码不正确！");
            }
        }else {
            resultInfo.setStatus(false);
            resultInfo.setMessage("用户不存在！");
        }
        return resultInfo;
    }
}
