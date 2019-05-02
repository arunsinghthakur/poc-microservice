package com.safexpress.propeli.security.filter;

import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.safexpress.propeli.security.util.JWTUtil;

import io.jsonwebtoken.Claims;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SecurityFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		chain.doFilter(filterJSessionId((HttpServletRequest) request), response);
	}

	private HttpServletRequest filterJSessionId(HttpServletRequest request) {
		String jwtToken = request.getHeader("Authorization");
		if (null != jwtToken) {
			try {
				Claims claims = JWTUtil.parseJWT(jwtToken);
				LinkedHashMap map = (LinkedHashMap) claims.get(JWTUtil.CUSTOM_CLAIM_KEY);
				MutableHttpServletRequest mutableRequest = new MutableHttpServletRequest(request);
				mutableRequest.putHeader("JSESSIONID", ((String) map.get("jsessionid")));
				return mutableRequest;
			} catch (Exception e) {
				// add log.
				e.printStackTrace();
			}
		}
		return request;
	}

	final class MutableHttpServletRequest extends HttpServletRequestWrapper {
		private final Map<String, String> customHeaders;

		public MutableHttpServletRequest(HttpServletRequest request) {
			super(request);
			this.customHeaders = new HashMap<String, String>();
		}

		public void putHeader(String name, String value) {
			this.customHeaders.put(name, value);
		}

		public String getHeader(String name) {
			String headerValue = customHeaders.get(name);
			if (headerValue != null) {
				return headerValue;
			}
			return ((HttpServletRequest) getRequest()).getHeader(name);
		}

		public Enumeration<String> getHeaderNames() {
			Set<String> set = new HashSet<String>(customHeaders.keySet());
			Enumeration<String> e = ((HttpServletRequest) getRequest()).getHeaderNames();
			while (e.hasMoreElements()) {
				String n = e.nextElement();
				set.add(n);
			}
			return Collections.enumeration(set);
		}
	}
}
