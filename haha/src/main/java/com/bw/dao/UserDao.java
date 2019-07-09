package com.bw.dao;

import java.util.List;

import com.bw.bean.Moive;
import com.bw.bean.User;

public interface UserDao {

	User login(User user);

	List<Moive> findall();

	Moive shangjia(Integer mid);

	int updatejia(Moive m);

}
