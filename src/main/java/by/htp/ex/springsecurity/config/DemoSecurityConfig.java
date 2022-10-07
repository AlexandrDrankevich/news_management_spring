package by.htp.ex.springsecurity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		UserBuilder users = User.withDefaultPasswordEncoder();

		auth.inMemoryAuthentication().withUser(users.username("admin@gmail.com").password("123").roles("USER", "ADMIN"))
				.withUser(users.username("qq@gmail.com").password("123").roles("USER"));
		;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/", "/base_page", "/registration/**", "/resources/**")
		.permitAll()
				.anyRequest()
				.authenticated()
				.and().formLogin()
				.loginPage("/base_page")
				.loginProcessingUrl("/authenticateTheUser")
				.failureUrl("/base_page?error")
				.defaultSuccessUrl("/newsList")				
				.permitAll()
				.and()
				.logout()
				.logoutSuccessUrl("/")
				.permitAll();
		}
}
