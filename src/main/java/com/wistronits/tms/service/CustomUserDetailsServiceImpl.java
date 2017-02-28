package com.wistronits.tms.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.wistronits.tms.dao.IUserDao;
import com.wistronits.tms.entity.UserBean;
import com.wistronits.tms.entity.customerUser;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {
	@Resource
	private IUserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		customerUser user = null;

		try {
			UserBean dbUser = userDao.listByName(username).get(0);
			
				if (dbUser.getUsername().equals(username) == true) {

					user=		new customerUser(dbUser.getId(), dbUser.getUsername(), dbUser.getPassword().toLowerCase(),
									true, true, true, true, getAuthorities(dbUser.getPermission()));
				}
			

		} catch (Exception e) {
			throw new UsernameNotFoundException("Error in retrieving user");
		}
		
		return user;
	}
	/**
	 * 获得访问角色权限
	 * 
	 * @param access
	 * @return
	 */
	public Collection<GrantedAuthority> getAuthorities(Integer access) {

		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(2);

		// 所有的用户默认拥有ROLE_USER权限

		authList.add(new SimpleGrantedAuthority("ROLE_USER"));

		// 如果参数access为1.则拥有ROLE_ADMIN权限
		if (access.compareTo(1) == 0) {
			authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}

		return authList;
	}
}
