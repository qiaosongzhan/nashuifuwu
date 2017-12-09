package cn.itcast.nsfw.user.service;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import javax.servlet.ServletOutputStream;

import cn.itcast.nsfw.user.entity.User;

public interface UserService {
	//新增
		public void save(User user);
	  //更新
		public void update(User user);
	  //根据id删除
		public void delete(Serializable id);
	  //根据id查找
		public User findObjectById(Serializable id);
		//根据id和account查找
		public List<User> findUserByAccountAndId(String id,String account);
	  //查找列表
		public List<User> findObjects();
		//导出列表
		public void exportExcel(List<User> userList,ServletOutputStream outputStream);
		//导入用户列表
		public void importExcel(File userExcel,String userExcelFileName);
}
