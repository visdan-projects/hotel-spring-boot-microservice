package com.visdan.hotel.authentication.model;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class AuthUserDetails implements UserDetails {
	private static final long serialVersionUID = 2548583892299419639L;

	private Collection<? extends GrantedAuthority> authorities;
	private String password;
	private String username;
	private boolean enabled;

	public AuthUserDetails(User user) {
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.enabled = user.isEnabled();
		this.authorities = translate(user.getRoles());
	}

	private Collection<? extends GrantedAuthority> translate(List<Role> roles) {
		return roles.stream().map(role -> {
			String name = role.getRole().toUpperCase();
			return (!name.startsWith("ROLE_")) ? "ROLE_" + name : name;
		}).map(s -> new SimpleGrantedAuthority(s)).collect(Collectors.toList());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}
}
