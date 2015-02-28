package com.spring.config.common;

import javax.servlet.http.HttpSession;

public class SessionStore {
	private static ThreadLocal<HttpSession> mySession = new ThreadLocal<HttpSession>();

	public static HttpSession getWebSession() {
		HttpSession session = (HttpSession) mySession.get();
		return session;
	}

	public static void setWebSession(HttpSession session) {
		mySession.set(session);
	}
}
