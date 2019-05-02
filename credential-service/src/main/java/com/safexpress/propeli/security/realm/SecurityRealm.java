package com.safexpress.propeli.security.realm;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Authorizer;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safexpress.propeli.security.domain.Employee;
import com.safexpress.propeli.security.domain.Role;
import com.safexpress.propeli.security.service.CredentialService;
import com.safexpress.propeli.security.service.EmployeeService;

@Service
public class SecurityRealm extends JdbcRealm implements Authorizer {

	@Autowired
	private CredentialService credentialService;

	@Autowired
	private EmployeeService employeeService;

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

		UsernamePasswordToken uToken = (UsernamePasswordToken) token;

		if (isEmptyCredential(uToken) || !isAuthenticated(uToken)) {
			throw new UnknownAccountException("username or password not found!");
		}

		return new SimpleAuthenticationInfo(uToken.getUsername(), uToken.getPassword(), getName());
	}

	@SuppressWarnings("unchecked")
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Set<String> roleNames = new HashSet<>();
		Set<String> permissions = new HashSet<>();

		principals.forEach(p -> {
			try {
				Set<String> roles = getRoleNamesForUser(null, (String) p);
				roleNames.addAll(roles);
				permissions.addAll(getPermissions(null, null, roles));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		});

		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roleNames);
		info.setStringPermissions(permissions);
		return info;
	}

	@Override
	protected Set<String> getRoleNamesForUser(Connection conn, String username) throws SQLException {
		Set<Role> roles = employeeService.findRolesByUsername(username);

		return roles.stream().map(r -> r.getRoleName()).collect(Collectors.toSet());
	}

	@Override
	protected Set<String> getPermissions(Connection conn, String username, Collection<String> roleNames)
			throws SQLException {

		Set<Role> roles = employeeService.findRolesByUsername(username);
		Set<String> permissions = new HashSet<>();
		roles.stream().forEach(r -> permissions.addAll(mapToPermission(r)));
		return permissions;
	}

	@Override
	public void setAuthenticationCachingEnabled(boolean authenticationCachingEnabled) {
		super.setAuthenticationCachingEnabled(true);
	}

	private Set<String> mapToPermission(Role role) {
		return role.getPermissions().stream().map(p -> p.getName()).collect(Collectors.toSet());
	}

	private boolean isEmptyCredential(UsernamePasswordToken uToken) {
		return uToken.getUsername() == null || uToken.getUsername().isEmpty() || uToken.getPassword() == null
				|| uToken.getPassword().length == 0;
	}

	private boolean isAuthenticated(UsernamePasswordToken uToken) {
		Employee employee = new Employee();
		employee.setUsername(uToken.getUsername());
		employee.setPassword(String.copyValueOf(uToken.getPassword()));
		return credentialService.isAuthenticated(employee);
	}
}
