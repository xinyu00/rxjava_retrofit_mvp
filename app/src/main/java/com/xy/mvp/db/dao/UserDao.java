package com.xy.mvp.db.dao;


import com.xy.mvp.bean.User;

public interface UserDao {
	
	void addUser(User user);		// 添加User
	
	User getUser(int userId);		// 根据userId查询User
	
	void update(User user);			// 更新User
	
	void delete(User user);			// 删除User
	
	User findUser(String userName);	// 根据用户名查询User

}
