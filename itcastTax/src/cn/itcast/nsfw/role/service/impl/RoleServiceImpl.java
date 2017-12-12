package cn.itcast.nsfw.role.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itcast.nsfw.role.dao.RoleDao;
import cn.itcast.nsfw.role.entity.Role;
import cn.itcast.nsfw.role.service.RoleService;
@Service("roleService")
public class RoleServiceImpl implements RoleService {
	@Resource
    private RoleDao roleDao;
	@Override
	public void save(Role role) {
		roleDao.save(role);
	}

	@Override
	public void update(Role role) {
		//1.删除该角色的所求权限  清除缓存
		roleDao.deleteRolePrivilegeByRoleId(role.getRoleId());
		//2.更新角色及其权限
		roleDao.update(role);
	}

	@Override
	public void delete(Serializable id) {
		roleDao.delete(id);
	}

	@Override
	public Role findObjectById(Serializable id) {
		return roleDao.findObjectById(id);
	}

	@Override
	public List<Role> findObjects() {
		return roleDao.findObjects();
	}

}
