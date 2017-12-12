package cn.itcast.nsfw.role.dao;

import java.io.Serializable;
import java.util.List;

import cn.itcast.core.dao.BaseDao;
import cn.itcast.nsfw.role.entity.Role;

public interface RoleDao extends BaseDao<Role>{
		  //新增
			public void save(Role role);
		  //更新
			public void update(Role role);
		  //根据id删除
			public void delete(Serializable id);
		  //根据id查找
			public Role findObjectById(Serializable id);
		  //查找列表
			public List<Role> findObjects();
			//根据角色id删除权限
			public	void deleteRolePrivilegeByRoleId(String roleId);
}
