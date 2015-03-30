package com.spring.config.initializer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.sql.DataSource;
import javax.ws.rs.HttpMethod;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
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
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import com.spring.config.service.impl.UserInfoServiceImpl;

@Configuration
@EnableWebMvcSecurity
@Import({ DynamicDataSourceConfig.class })
@PropertySource({ "classpath:/config/properties/springSecurityConfig.properties" })
@Component
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

	@Value("${salt.field}")
	private String salt;

	@Value("${access.denied.url}")
	private String accessDeniedUrl;

	@Resource(name = "dynamicDataSource")
	private DataSource dynamicDataSource;

	private final String resourceQuery = "select re.resc_string, r.name from t_role r join t_resc_role rr "
	        + " on r.id = rr.role_id join t_resc re on re.id = rr.resc_id order by re.priority";

	public SpringSecurityConfig() {
	}

	/**
	 * 设置不拦截规则
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/images/**", "/js/**");
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.exceptionHandling().accessDeniedHandler(accessDeniedHandler()).and().logout().deleteCookies("JSESSIONID").invalidateHttpSession(true)
		        .logoutRequestMatcher(new AntPathRequestMatcher(logoutUrl, HttpMethod.GET)).and().formLogin()
		        .loginPage(loginUrl).usernameParameter(userName).passwordParameter(password)
		        .defaultSuccessUrl(successUrl).failureUrl(errorUrl).and().httpBasic().and()
		        .addFilter(this.filterSecurityInterceptor()).userDetailsService(userDetailsService())
		        .authenticationProvider(daoAuthenticationProvider());
	}

	@Bean(autowire = Autowire.BY_TYPE)
	public FilterSecurityInterceptor filterSecurityInterceptor() {
		FilterSecurityInterceptor fsi = new FilterSecurityInterceptor();
		fsi.setSecurityMetadataSource(fsiMetadataSource());
		fsi.setAccessDecisionManager(accessDecisionManager());
		fsi.setAuthenticationManager(providerManager());
		return fsi;
	}

	/**
	 * 自定义访问拒绝页面
	 * 
	 * @return
	 */
	@Bean
	public AccessDeniedHandler accessDeniedHandler() {
		AccessDeniedHandlerImpl accessDeniedHandler = new AccessDeniedHandlerImpl();
		accessDeniedHandler.setErrorPage(accessDeniedUrl);
		return accessDeniedHandler;
	}

	/**
	 * md5加密，用户名作为盐值
	 * 
	 * @return
	 */
	public AuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider dap = new DaoAuthenticationProvider();
		dap.setUserDetailsService(userDetailsService());
		dap.setHideUserNotFoundExceptions(false);
		dap.setPasswordEncoder(new Md5PasswordEncoder());
		ReflectionSaltSource saltSource = new ReflectionSaltSource();
		saltSource.setUserPropertyToUse(salt);
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
		List<AuthenticationProvider> providers = new ArrayList<AuthenticationProvider>();
		providers.add(anonymousAuthenticationProvider());
		providers.add(this.daoAuthenticationProvider());

		AuthenticationManager manager = new ProviderManager(providers);
		return manager;
	}

	@Bean
	public FilterInvocationSecurityMetadataSource fsiMetadataSource() {
		JdbcFilterInvocationDefinitionSourceFactoryBean bean = new JdbcFilterInvocationDefinitionSourceFactoryBean();

		bean.setResourceQuery(resourceQuery);
		bean.setDataSource(dynamicDataSource);
		return bean.getObject();
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
