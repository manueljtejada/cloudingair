package com.twcam.uv.cloudingair.security;

import java.util.List;

import javax.transaction.Transactional;

import com.twcam.uv.cloudingair.domain.Agency;
import com.twcam.uv.cloudingair.repository.AgencyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Primary
public class UserDetailsServiceImpl implements UserDetailsService  {

	@Autowired
  @Lazy
	private BCryptPasswordEncoder encoder;

	@Autowired
	AgencyRepository agencyRepository;

	@Transactional
	public UserDetails loadUserByUsername(String username) {

		username = username.toLowerCase();
		Agency agency;

		try {
			agency = agencyRepository.findByUsername(username);
			agency.setPassword(encoder.encode(agency.getPassword()));
		} catch (ObjectRetrievalFailureException orfe) {
			throw new UsernameNotFoundException("User '" + username + "' could not be found.");
		}

		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
		                	.commaSeparatedStringToAuthorityList("ROLE_" + agency.getRole());

		return new User(agency.getUsername(), agency.getPassword(), grantedAuthorities);
	}
}