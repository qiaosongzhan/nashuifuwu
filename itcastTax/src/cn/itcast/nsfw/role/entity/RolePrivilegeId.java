package cn.itcast.nsfw.role.entity;

import java.io.Serializable;

public class RolePrivilegeId implements Serializable{
	 private String code;
	 private Role role;
	public String getCode() {
		return code;
	}
	public RolePrivilegeId() {
		super();
	}
	public RolePrivilegeId(String code, Role role) {
		super();
		this.code = code;
		this.role = role;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
}
