package com.itfang.www.bbl.servic;

import com.itfang.www.dal.po.ResultInfo;
import com.itfang.www.dal.po.Teacher;
import com.itfang.www.dal.po.TeacherUser;
import com.itfang.www.dal.po.Upload;

import java.sql.SQLException;

/**
 * @author it-fang
 * @Description 教师用户的所有相关操作
 * @date 2020-4-27
 */
public interface TeacherService {

    /**
     * 检查用户名是否存在
     * @param username
     * @return
     * @throws SQLException
     */
    Object checkUsername(String username) throws SQLException;

    /**
     * 注册教师账号
     * @param teacher
     * @param teacherUser
     * @return
     * @throws SQLException
     */
    Object register(Teacher teacher, TeacherUser teacherUser) throws SQLException;

    /**
     * 教师用户登陆
     * @param teacherUser
     * @return
     * @throws SQLException
     */
    Object login(TeacherUser teacherUser) throws SQLException;

    /**
     * 查询预约请求
     * @param teacherId
     * @return
     * @throws SQLException
     */
    ResultInfo queryApplication(int teacherId) throws SQLException;

    /**
     * 根据学生Id获取学生对象信息
     * @param studentId
     * @return
     * @throws SQLException
     */
    ResultInfo getStudent(int studentId) throws SQLException;

    /**
     * 教师审批预约请求
     * @param studentId
     * @param teacherId
     * @param ifAgree
     * @return
     * @throws SQLException
     */
    Object agree(int studentId, int teacherId, String ifAgree) throws SQLException;

    /**
     * 删除预约请求
     * @param _studentId
     * @param _teacherId
     * @return
     * @throws SQLException
     */
    Object deleteApplication(String _studentId, String _teacherId) throws SQLException;

    /**
     * 获取教师对象并存入resultInfo数据域中
     * @param teacherId
     * @return
     * @throws SQLException
     */
    ResultInfo getTeacher(int teacherId) throws SQLException;

    /**
     * 修改教师对象信息
     * @param teacher
     * @param teacherId
     * @return
     * @throws SQLException
     */
    Object updateTeacher(Teacher teacher,int teacherId) throws SQLException;

    /**
     * 教师同意所选的预约请求
     * @param teacherId
     * @param studentIds
     * @return
     * @throws SQLException
     */
    Object agreeSelect(int teacherId, String[] studentIds) throws SQLException;

    /**
     * 根据学生用户Id获得学生用户上传的图片的信息
     * @param studentId
     * @return
     * @throws SQLException
     */
    Upload getUpload(int studentId) throws SQLException;

    /**
     * 判断用户输入的验证码是否正确
     * @param code
     * @param realCode
     * @return
     */
    Object checkCode(String code, String realCode);

    /**
     * 获取公告表该用户未读的信息
     * @param teacherUser
     * @return
     * @throws SQLException
     */
    Object queryInformationAuto(TeacherUser teacherUser) throws SQLException;

    /**
     * 获取公告表该用户已读的信息
     * @param teacherUser
     * @return
     * @throws SQLException
     */
    Object queryInformation(TeacherUser teacherUser) throws SQLException;
}
