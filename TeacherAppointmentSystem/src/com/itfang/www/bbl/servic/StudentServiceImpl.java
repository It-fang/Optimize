package com.itfang.www.bbl.servic;

import com.itfang.www.dal.dao.StudentUserDao;
import com.itfang.www.dal.dao.StudentUserDaoImpl;
import com.itfang.www.dal.po.ResultInfo;
import com.itfang.www.ui.BaseServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * @author it-fang
 * @Description 学生用户的所有相关操作
 * @date 2020-4-27
 */
public class StudentServiceImpl implements StudentService {
    ResultInfo resultInfo = new ResultInfo();
    StudentUserDao studentUserDao = new StudentUserDaoImpl();

    /**
     * 检查用户名是否存在
     * @param username
     * @throws SQLException
     * @return resultInfo
     */
    @Override
    public Object checkUsername(String username) throws SQLException {
        //判断提交的用户名是否为空
        if ("".equals(username)){
            resultInfo.setStatus(true);
            resultInfo.setMessage("用户名不能为空");
            return resultInfo;
        }
        boolean status = studentUserDao.isExit(username);
        if (status){
            resultInfo.setStatus(true);
            resultInfo.setMessage("用户名已经存在");
        }else {
            resultInfo.setStatus(false);
            resultInfo.setMessage("用户名可以使用");
        }
        return resultInfo;
    }
}
