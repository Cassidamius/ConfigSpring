package com.spring.config.initializer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.sql.DataSource;
import javax.ws.rs.HttpMethod;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.authentication.AnonymousAuthenticationProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.dao.ReflectionSaltSource;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.access.channel.ChannelDecisionManager;
import org.springframework.security.web.access.channel.ChannelDecisionManagerImpl;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.access.channel.ChannelProcessor;
import org.springframework.security.web.access.channel.InsecureChannelProcessor;
import org.springframework.security.web.access.channel.SecureChannelProcessor;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.access.intercept.DefaultFilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.spring.config.service.impl.UserInfoServiceImpl;

@Configuration
@EnableWebMvcSecurity
@Import({ DynamicDataSourceConfig.class })
@PropertySource({ "classpath:/config/properties/springSecurityConfig.properties" })
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Value("${login.url}")
	private String loginUrl;

	@Value("${login.logout.url}")
	private String logoutUrl;

	@Value("${login.username}")
	private String userName;

	@Value("${login.password}")
	private String password;

	@Value("${login.success.url}")
	private String successUrl;

	@Value("${login.error.url}")
	private String errorUrl;

	@Resource(name = "dynamicDataSource")
	private DataSource dynamicDataSource;

	private String authoritiesByUsernameQuery = "select u.username,r.name as authority from t_user u "
	        + " join t_user_role ur on u.id=ur.user_id join t_role r on r.id=ur.role_id " + " where u.username=?";

	private String usersByUsernameQuery = "select username, password, delete_flag as enabled from t_user " + " where username=?";

	private String resourceQuery = "select re.resc_string, r.name from t_role r join t_resc_role rr "
	        + " on r.id = rr.role_id join t_resc re on re.id = rr.resc_id order by re.priority";

	/**
	 * 设置不拦截规则
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/images/**", "/js/**");
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {

		ExceptionTranslationFilter etf = new ExceptionTranslationFilter(authenticationEntryPoint());
		etf.setAccessDeniedHandler(accessDeniedHandler());

		FilterSecurityInterceptor fsi = new FilterSecurityInterceptor();
		fsi.setSecurityMetadataSource(fsiMetadataSource());
		fsi.setAccessDecisionManager(accessDecisionManager());

		ChannelProcessingFilter cpf = new ChannelProcessingFilter();
		cpf.setChannelDecisionManager(channelDecisionManager());
		cpf.setSecurityMetadataSource(cpfMetadataSource());

		SimpleUrlLogoutSuccessHandler slsh = new SimpleUrlLogoutSuccessHandler();
		slsh.setDefaultTargetUrl(loginUrl);
		SecurityContextLogoutHandler sclh = new SecurityContextLogoutHandler();
		sclh.setClearAuthentication(true);

		LogoutFilter lf = new LogoutFilter(slsh, sclh);
		RequestMatcher logoutRequestMatcher = new AntPathRequestMatcher(logoutUrl, HttpMethod.GET);
		lf.setLogoutRequestMatcher(logoutRequestMatcher);

		http.exceptionHandling().accessDeniedHandler(accessDeniedHandler()).and()
		        .addFilterAfter(lf, LogoutFilter.class)
		        .addFilterBefore(usernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
		        .addFilter(fsi).addFilter(cpf).authorizeRequests().anyRequest().authenticated().and().httpBasic();
	}

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.jdbcAuthentication().authoritiesByUsernameQuery(authoritiesByUsernameQuery)
//		        .usersByUsernameQuery(usersByUsernameQuery).dataSource(dynamicDataSource);
//	}

	public UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter() {
		MyUsernamePasswordAuthenticationFilter upaf = new MyUsernamePasswordAuthenticationFilter();
		upaf.setAuthenticationManager(providerManager());

		upaf.setUsernameParameter(userName);
		upaf.setPasswordParameter(password);
		upaf.setAuthenticationSuccessHandler(new SimpleUrlAuthenticationSuccessHandler(successUrl));
		upaf.setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler(errorUrl));
		upaf.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(loginUrl, HttpMethod.POST));

		return upaf;
	}

	@Bean
	public AuthenticationEntryPoint authenticationEntryPoint() {
		return new LoginUrlAuthenticationEntryPoint(loginUrl);
	}

	/**
	 * 自定义访问拒绝页面
	 * 
	 * @return
	 */
	@Bean
	public AccessDeniedHandler accessDeniedHandler() {
		AccessDeniedHandlerImpl accessDeniedHandler = new AccessDeniedHandlerImpl();
		accessDeniedHandler.setErrorPage("/WEB-INF/content/accessDenied.jsp");
		return accessDeniedHandler;
	}

	/**
	 * md5加密，用户名作为盐值
	 * 
	 * @return
	 */
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider dap = new DaoAuthenticationProvider();
		dap.setUserDetailsService(userDetailsService());
		dap.setHideUserNotFoundExceptions(false);
		dap.setPasswordEncoder(new Md5PasswordEncoder());
		ReflectionSaltSource saltSource = new ReflectionSaltSource();
		saltSource.setUserPropertyToUse("salt");
		dap.setSaltSource(saltSource);
		return dap;
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new UserInfoServiceImpl();
	}

	public AuthenticationProvider anonymousAuthenticationProvider() {
		AnonymousAuthenticationProvider provider = new AnonymousAuthenticationProvider(UUID.randomUUID().toString());
		return provider;
	}

	public AuthenticationManager providerManager() {
		List<AuthenticationProvider> providersParent = new ArrayList<AuthenticationProvider>();
		providersParent.add(authenticationProvider());
		AuthenticationManager parent = new ProviderManager(providersParent);

		List<AuthenticationProvider> providers = new ArrayList<AuthenticationProvider>();
		providers.add(anonymousAuthenticationProvider());

		AuthenticationManager manager = new ProviderManager(providers, parent);
		return manager;
	}

	public FilterInvocationSecurityMetadataSource fsiMetadataSource() {
		JdbcFilterInvocationDefinitionSourceFactoryBean bean = new JdbcFilterInvocationDefinitionSourceFactoryBean();

		bean.setResourceQuery(resourceQuery);
		bean.setDataSource(dynamicDataSource);
		return bean.getObject();
	}

	public FilterInvocationSecurityMetadataSource cpfMetadataSource() {
		LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> map = new LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>>();

		// AntPathRequestMatcher matcher0 = new
		// AntPathRequestMatcher("/**/security/logout");
		// Collection<ConfigAttribute> atts0 = new ArrayList<ConfigAttribute>();
		// ConfigAttribute ca0 = new
		// SecurityConfig("REQUIRES_INSECURE_CHANNEL");
		// atts0.add(ca0);
		// map.put(matcher0, atts0);

		AntPathRequestMatcher matcher = new AntPathRequestMatcher("/**/security/**");
		Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
		ConfigAttribute ca = new SecurityConfig("REQUIRES_SECURE_CHANNEL");
		atts.add(ca);
		map.put(matcher, atts);

		AntPathRequestMatcher inMatcher = new AntPathRequestMatcher("/**");
		Collection<ConfigAttribute> inAtts = new ArrayList<ConfigAttribute>();
		ConfigAttribute inCa = new SecurityConfig("REQUIRES_INSECURE_CHANNEL");
		inAtts.add(inCa);
		map.put(inMatcher, inAtts);

		DefaultFilterInvocationSecurityMetadataSource bean = new DefaultFilterInvocationSecurityMetadataSource(map);

		return bean;
	}

	public ChannelDecisionManager channelDecisionManager() {
		ChannelDecisionManagerImpl channelDecisionManager = new ChannelDecisionManagerImpl();
		SecureChannelProcessor secureChannelProcessor = new SecureChannelProcessor();
		InsecureChannelProcessor insecureChannelProcessor = new InsecureChannelProcessor();

		List<ChannelProcessor> channelProcessors = new ArrayList<ChannelProcessor>();
		channelProcessors.add(insecureChannelProcessor);
		channelProcessors.add(secureChannelProcessor);

		channelDecisionManager.setChannelProcessors(channelProcessors);

		return channelDecisionManager;
	}

	/**
	 * 
	 * 这里可以增加自定义的投票器
	 */
	@SuppressWarnings("rawtypes")
	public AccessDecisionManager accessDecisionManager() {
		List<AccessDecisionVoter> decisionVoters = new ArrayList<AccessDecisionVoter>();
		decisionVoters.add(new RoleVoter());
		decisionVoters.add(new AuthenticatedVoter());
		decisionVoters.add(webExpressionVoter());// 启用表达式投票器

		AffirmativeBased accessDecisionManager = new AffirmativeBased(decisionVoters);
		accessDecisionManager.setAllowIfAllAbstainDecisions(false);

		return accessDecisionManager;
	}

	/**
	 * 表达式投票器
	 */
	public WebExpressionVoter webExpressionVoter() {
		WebExpressionVoter voter = new WebExpressionVoter();
		voter.setExpressionHandler(webSecurityExpressionHandler());
		return voter;
	}

	/**
	 * 表达式控制器
	 */
	public DefaultWebSecurityExpressionHandler webSecurityExpressionHandler() {
		DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
		return handler;
	}

}
