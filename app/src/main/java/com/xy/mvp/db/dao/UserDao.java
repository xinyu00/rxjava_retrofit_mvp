package com.xy.mvp.db.dao;


import com.xy.mvp.entity.UserEntity;

public interface UserDao {
	
	void addUser(UserEntity user);		// 添加User
	
	UserEntity getUser(int userId);		// 根据userId查询User
	
	void update(UserEntity user);			// 更新User
	
	void delete(UserEntity user);			// 删除User
	
	UserEntity findUser(String userName);	// 根据用户名查询User

}
