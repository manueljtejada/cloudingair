package com.twcam.uv.cloudingair.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.twcam.uv.cloudingair.domain.Agency;
import com.twcam.uv.cloudingair.repository.AgencyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
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

	private List<Agency> agencies = new ArrayList<Agency>();

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		// hard coding the users. All passwords must be encoded.
		// final List<Agency> users = Arrays.asList(
		// 	new AppUser(1, "omar", encoder.encode("12345"), "USER"),
		// 	new AppUser(2, "admin", encoder.encode("12345"), "ADMIN")
		// );

		final List<Agency> users = agencyRepository.findAll().stream().map(a -> {
			String password = encoder.encode(a.getPassword());
			a.setPassword(password);
			return a;
		}).collect(Collectors.toList());


		for(Agency appUser: users) {
			if(appUser.getUserName().equals(username)) {

				// Remember that Spring needs roles to be in this format: "ROLE_" + userRole (i.e. "ROLE_ADMIN")
				// So, we need to set it to that format, so we can verify and compare roles (i.e. hasRole("ADMIN")).
				List<GrantedAuthority> grantedAuthorities = AuthorityUtils
		                	.commaSeparatedStringToAuthorityList("ROLE_" + appUser.getRole());

				// The "User" class is provided by Spring and represents a model class for user to be returned by UserDetailsService
				// And used by auth manager to verify and check user authentication.
				return new User(appUser.getUserName(), appUser.getPassword(), grantedAuthorities);
			}
		}

		// If user not found. Throw this exception.
		throw new UsernameNotFoundException("Username: " + username + " not found");
	}

	// A (temporary) class represent the user saved in the database.
	private static class AppUser {
		private Integer id;
	    private String username, password;
	    private String role;

		public AppUser(Integer id, String username, String password, String role) {
	    	this.id = id;
	    	this.username = username;
	    	this.password = password;
	    	this.role = role;
	    }

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
	    public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}
	}
}