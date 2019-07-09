package com.bw.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bw.bean.Moive;
import com.bw.bean.User;
import com.bw.dao.UserDao;
@Service
public class UserServiceimp implements UserService {
	
	@Resource
	private UserDao ud;
	
	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		return ud.login(user);
	}

	@Override
	public List<Moive> findall() {
		// TODO Auto-generated method stub
		return ud.findall();
	}

	@Override
	public Moive shangjia(Integer mid) {
		// TODO Auto-generated method stub
		return ud.shangjia(mid);
	}

	@Override
	public int updatejia(Moive m) {
		// TODO Auto-generated method stub
		return ud.updatejia(m);
	}

}
