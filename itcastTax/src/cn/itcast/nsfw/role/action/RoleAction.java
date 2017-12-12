package cn.itcast.nsfw.role.action;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import cn.itcast.core.action.BaseAction;
import cn.itcast.core.constant.Constant;
import cn.itcast.core.exception.ActionException;
import cn.itcast.nsfw.role.entity.Role;
import cn.itcast.nsfw.role.entity.RolePrivilege;
import cn.itcast.nsfw.role.entity.RolePrivilegeId;
import cn.itcast.nsfw.role.service.RoleService;

import com.opensymphony.xwork2.ActionContext;

public class RoleAction extends BaseAction{
	@Resource
	private RoleService roleService;
	private List<Role> roleList;
	private Role role; 
	private String[] privilegeIds; 
	
	public List<Role> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	//列表页面
	public String listUI() throws ActionException{
		try {
			//加载权限集合
			ActionContext.getContext().getContextMap().put("privilegeMap", Constant.PRIVILEGE_MAP);
			roleList = roleService.findObjects();
		} catch (Exception e) {
			throw new ActionException("Action 出现异常;"+e.getMessage());
		}
		return "listUI";
		
	}
	//跳转到新增页面
	public String addUI(){
		//加载权限集合
		ActionContext.getContext().getContextMap().put("privilegeMap", Constant.PRIVILEGE_MAP);
		return "addUI";
		
	}
	//保存新增
	public String add(){
		try {
		     if (role!=null) {
		    	 if(privilegeIds!=null){
		    		 Set<RolePrivilege> set = new HashSet<RolePrivilege>(); 
		    		 for (String privilegeId : privilegeIds) {
		    			 set.add(new RolePrivilege(new RolePrivilegeId(privilegeId, role)));
					  }
		    		 role.setRolePrivileges(set);
		    	 }
				roleService.save(role);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		return "list";
		
	}
	//跳转到编辑页面
	public String editUI(){
		//加载权限集合
		ActionContext.getContext().getContextMap().put("privilegeMap", Constant.PRIVILEGE_MAP);
		if (role !=null) {
			role = roleService.findObjectById(role.getRoleId());
			//处理权限回显
			if(role.getRolePrivileges()!=null){
				privilegeIds = new String[role.getRolePrivileges().size()];
				int i=0;
				for(RolePrivilege rp:role.getRolePrivileges()){
					privilegeIds[i++] = rp.getId().getCode();
				}
			}
		}
		return "editUI";
		
	}
	//保存编辑
	public String edit(){
		try {
		     if (role!=null) {
		    	 if(privilegeIds!=null){
		    		 Set<RolePrivilege> set = new HashSet<RolePrivilege>(); 
		    		 for (String privilegeId : privilegeIds) {
		    			 set.add(new RolePrivilege(new RolePrivilegeId(privilegeId, role)));
		    		 }
		    		 role.setRolePrivileges(set);
		         }
				roleService.update(role);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		return "list";
		
	}
	//删除
	public String delete(){
		if (role !=null) {
			roleService.delete(role.getRoleId());
		}
		return "list";
		
	}
	//批量删除  
	public String deleteSelected(){
		//可以循环删除
		if(selectedRow != null){
			for(String id:selectedRow){
				roleService.delete(id);
			}
		}
		return "list";
	}
	public void setPrivilegeIds(String[] privilegeIds) {
		this.privilegeIds = privilegeIds;
	}
	public String[] getPrivilegeIds() {
		return privilegeIds;
	}
	
}

