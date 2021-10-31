package com.d2.dw.code;

public enum UserLevel {
	VIEWER,
	MODIFIER,
	ADMIN,
	;
	
	public boolean isView(UserLevel userLevel) {
		return (userLevel != null) ? true : false;
	}
	
	public boolean isModify(UserLevel userLevel) {
		return (UserLevel.ADMIN.equals(userLevel)
				|| UserLevel.MODIFIER.equals(userLevel)
				) ? true : false;
	}
	
	public boolean isManage(UserLevel userLevel) {
		return (UserLevel.ADMIN.equals(userLevel)) ? true : false;
	}
}
