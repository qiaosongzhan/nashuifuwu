package cn.itcast.nsfw.user.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import cn.itcast.core.dao.impl.BaseDaoImpl;
import cn.itcast.nsfw.user.dao.UserDao;
import cn.itcast.nsfw.user.entity.User;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	@Override
	public List<User> findUserByAccountAndId(String id, String account) {
		String hql = "FROM User WHERE account=?";
		if(StringUtils.isNotBlank(id)){
			//这里专为editUI.jsp服务  排除自己验证
			hql+=" AND id !=?";
			List list = getSession().createQuery(hql).setParameter(0, account).setParameter(1, id).list();
			System.out.println(list.size());
			return list;
		}
		return getSession().createQuery(hql).setParameter(0, account).list();
	}

}
