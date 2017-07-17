package com.insigma.shiro.realm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasAuthenticationException;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.cas.CasToken;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.util.StringUtils;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.validation.Assertion;
import org.jasig.cas.client.validation.TicketValidationException;
import org.jasig.cas.client.validation.TicketValidator;

public class UserRealm extends CasRealm {
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		CasToken casToken = (CasToken) token;
		if (token == null) {
			return null;
		}
		String ticket = (String) casToken.getCredentials();
		if (!StringUtils.hasText(ticket)) {
			return null;
		}
		TicketValidator ticketValidator = ensureTicketValidator();
		try {
			Assertion casAssertion = ticketValidator.validate(ticket,getCasService());
			AttributePrincipal casPrincipal = casAssertion.getPrincipal();
			String userId = casPrincipal.getName();
			// new Object[] { ticket, getCasServerUrlPrefix(), userId };
			Map<String, Object> attributes = casPrincipal.getAttributes();
			casToken.setUserId(userId);
			String rememberMeAttributeName = getRememberMeAttributeName();
			String rememberMeStringValue = (String) attributes.get(rememberMeAttributeName);
			boolean isRemembered = rememberMeStringValue != null&& Boolean.parseBoolean(rememberMeStringValue);
			if (isRemembered) {
				casToken.setRememberMe(true);
			}
			List<Object> principals = CollectionUtils.asList(userId, attributes);
			PrincipalCollection principalCollection = new SimplePrincipalCollection(principals, getName());
			// 可以获取到Cas登录账号信息，此步将对应权限体系信息放到缓存
			return new SimpleAuthenticationInfo(principalCollection, ticket);
		} catch (TicketValidationException e) {
			throw new CasAuthenticationException("Unable to validate ticket ["+ ticket + "]", e);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimplePrincipalCollection principalCollection = (SimplePrincipalCollection) principals;
		List<Object> listPrincipals = principalCollection.asList();
		Map<String, String> attributes = (Map<String, String>) listPrincipals.get(1);
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		// 获取缓存信息放到认证实体中
		addRoles(simpleAuthorizationInfo, split(getDefaultRoles()));
		return simpleAuthorizationInfo;
	}

	
	private List<String> split(String s) {
		List<String> list = new ArrayList<String>();
		String[] elements = StringUtils.split(s, ',');
		if (elements != null && elements.length > 0) {
			for (String element : elements) {
				if (StringUtils.hasText(element)) {
					list.add(element.trim());
				}
			}
		}
		return list;
	}

	
	private void addRoles(SimpleAuthorizationInfo simpleAuthorizationInfo,
			List<String> roles) {
		for (String role : roles) {
			simpleAuthorizationInfo.addRole(role);
		}
	}
}
