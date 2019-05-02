package com.safexpress.propeli.security.configuration;

import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authz.Authorizer;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.safexpress.propeli.cache.manager.CredentialCacheManagerImpl;
import com.safexpress.propeli.security.realm.SecurityRealm;

@Configuration
public class SecurityConfiguration {

	@Bean
	public CacheManager cacheManager() {
		return new CredentialCacheManagerImpl();
	}

	@Bean
	public Realm realm() {
		SecurityRealm realm = new SecurityRealm();
		realm.setAuthorizationCachingEnabled(true);
		realm.setAuthenticationCachingEnabled(true);
		return realm;
	}

	@Bean
	public Authorizer authorizer() {
		SecurityRealm realm = new SecurityRealm();
		realm.setAuthenticationCachingEnabled(true);
		realm.setAuthorizationCachingEnabled(true);
		realm.setCacheManager(cacheManager());
		return realm;
	}

	@Bean
	public Authenticator authenticator() {
		DefaultWebSecurityManager securityMgr = new DefaultWebSecurityManager(realm());
		securityMgr.setCacheManager(cacheManager());
		return securityMgr;
	}

	@Bean
	public SessionManager sessionManager() {
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		sessionManager.setCacheManager(cacheManager());
		sessionManager.setSessionDAO(sessionDAO());
		sessionManager.setSessionIdCookie(cookie());
		return sessionManager;
	}

	@Bean
	public Cookie cookie() {
		Cookie cookie = new org.apache.shiro.web.servlet.SimpleCookie();
		cookie.setName("JSESSIONID");
		cookie.setPath("/");
		return cookie;
	}

	@Bean
	public SessionDAO sessionDAO() {
		SessionDAO sessionDAO = new EnterpriseCacheSessionDAO();
		return sessionDAO;
	}

	@Bean
	public ShiroFilterChainDefinition shiroFilterChainDefinition() {
		DefaultShiroFilterChainDefinition filter = new DefaultShiroFilterChainDefinition();
		filter.addPathDefinition("/**", "anon");
		filter.addPathDefinition("/secure", "authc");
		return filter;
	}
}
