package com.spring.config.aspect;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.config.common.SessionStore;
import com.spring.config.model.Logs;
import com.spring.config.model.UserInfo;
import com.spring.config.service.LogsService;
import com.spring.config.service.impl.LogsServiceImpl;

@Component
@Aspect
public class DbLogAspect {

	@Autowired
	private LogsService logsService;

	@Before(value = "execution(* *..service.impl.*Impl.*(..))")
	public void doBefore(JoinPoint jp) {
		Object target = jp.getTarget();
		String canonicalName = target.getClass().getCanonicalName();
		String methodName = jp.getSignature().getName();
		if (LogsServiceImpl.class.getCanonicalName().equals(canonicalName) && "save".equalsIgnoreCase(methodName)) {
			return;
		}
		HttpSession session = SessionStore.getWebSession();

		if (session == null) {
			return;
		}
		System.out.println("before: " + jp.getTarget().getClass().getName() + "." + jp.getSignature().getName());
		
		UserInfo user = null;
		try {
			user = (UserInfo) session.getAttribute("sessionUserInfo");
		} catch (IllegalStateException e) {
			return;
		}
		Logs logs = new Logs();
		logs.setUserInfo(user);
		logs.setClassName(jp.getTarget().getClass().getName());
		logs.setMethodName(jp.getSignature().getName());
		logs.setDeleteFlag(1);
		logsService.save(logs);
	}

}
