package com.hust.hungry.mapper;



import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hust.hungry.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserMapperTest {
    //1、注入UserMapper接口对应的对象
    @Autowired
    private UserMapper userMapper;

    @Test
    public  void addTest(){
        //1、封装对象
        User user=new User();
        user.setUserId("12345672221");
        user.setUserName("张三");
        user.setUserSex(0);
        user.setUserImg("+0zWPUl8tYMA1qOwi82bxzAAvybikSfZhme0srdHm89Zq1XLF2lxd5uTMOzsZ2eQyrbvWfWKM0vl/N/2ETSZqf1oa9pWPXCsT/sPn9AOeIade/c5vZ0VizrU09GiKO/Zu/8oKCbNrqGa4XrVrD/LOmN4fISuaD1Ei4l47UCtCL9Qy9pA1sJx4wQAIIiijBZ1VtJ5gOShl20c7Six1gZrXTiAlzaQz5yQB/z/ASYreCvt25D1AAAAAElFTkSuQmCC");
        user.setPassword("1234567");
        user.setDelTag(1);
        //2、调用方法import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
        //import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
        int rs= userMapper.insert(user);
        //3、判断结果
        if(rs==1){
            System.out.println("添加成功");
        }else{
            System.out.println("添加失败");
        }
    }

    @Test
    public  void update(){
        //1、封装对象
        User user=new User();
        user.setUserId("12345672221");
        user.setUserName("jack");
        user.setUserSex(0);
        user.setUserImg("+0zWPUl8tYMA1qOwi82bxzAAvybikSfZhme0srdHm89Zq1XLF2lxd5uTMOzsZ2eQyrbvWfWKM0vl/N/2ETSZqf1oa9pWPXCsT/sPn9AOeIade/c5vZ0VizrU09GiKO/Zu/8oKCbNrqGa4XrVrD/LOmN4fISuaD1Ei4l47UCtCL9Qy9pA1sJx4wQAIIiijBZ1VtJ5gOShl20c7Six1gZrXTiAlzaQz5yQB/z/ASYreCvt25D1AAAAAElFTkSuQmCC");
        user.setPassword("123");
        user.setDelTag(1);
        //2、调用方法
        int rs= userMapper.updateById(user);
        //3、判断结果
        if(rs==1){
            System.out.println("修改成功");
        }else{
            System.out.println("修改失败");
        }
    }


    @Test
    public void del(){
        //1、封装对象
        User user=new User();
        user.setUserId("12345672221");
        //2、调用方法
        int rs= userMapper.deleteById(user);
        //3、判断结果
        if(rs==1){
            System.out.println("删除成功");
        }else{
            System.out.println("删除失败");
        }
    }


    @Test
    public void getUserById(){

        //查询条件
        LambdaQueryWrapper<User> lambdaQueryWrapper=new LambdaQueryWrapper();
        //查询条件
        lambdaQueryWrapper.eq(User::getUserId,"11111111111");
        //查询列
        lambdaQueryWrapper.select(true,User::getUserId,User::getUserName);

        User user= userMapper.selectOne(lambdaQueryWrapper);
        System.out.println(user);
    }

    @Test
    public void getUserById2(){
        //查询条件
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        //查询条件
        queryWrapper.eq("userId","11111111111");
        //查询列
        queryWrapper.select(true,"userId","userName","userSex");

        User user= userMapper.selectOne(queryWrapper);
        System.out.println(user);
    }

}
