package fr.formation.inti;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

import fr.formation.inti.service.UserService;

//@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserService userService;

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
//	@Bean
//	public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
//	    StrictHttpFirewall firewall = new StrictHttpFirewall();
//	    firewall.setAllowUrlEncodedSlash(true);
//	    firewall.setAllowSemicolon(true);
//	    return firewall;
//	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		// Users in memory.

//        auth.inMemoryAuthentication().withUser("user1").password("{noop}12345").roles("USER");
//        auth.inMemoryAuthentication().withUser("admin1").password("{noop}12345").roles("USER, ADMIN");

		// For User in database.
		auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();

        // The pages does not require login
        http.authorizeRequests().antMatchers("/","/css/**","/*.css","/add", "/login", "/logout","/bootstrap/**","/static/**","/resources/static/**","/signup").permitAll();
        

         // userInfo page requires login as USER or ADMIN.
         // If no login, it will redirect to /login page.
        http.authorizeRequests().antMatchers("/**").access("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')");

        // For ADMIN only.
//        http.authorizeRequests().antMatchers("/admin").access("hasRole('ROLE_ADMIN')");

        // When the user has logged in as XX.
        // But access a page that requires role YY,
        // AccessDeniedException will throw.
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");

        // Config for Login Form
        http.authorizeRequests().and().formLogin()//
                // Submit URL of login page.
//                .loginProcessingUrl("/j_spring_security_check") // Submit URL
                .loginPage("/login")//
                .defaultSuccessUrl("/accueil-logged")//
                .failureUrl("/login?error=true")//
                .usernameParameter("username")//
                .passwordParameter("password")
                // Config for Logout Page
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/login");
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
	    web
	            .ignoring()
	            .antMatchers("/**.css","/resources/**", "/static/**", "/css/**", "/js/**", "/img/**", "/icon/**","/**.css","/webfonts/**","/error");
//	    super.configure(web);
	    // @formatter:off
//	    web.httpFirewall(allowUrlEncodedSlashHttpFirewall());
	}
	
}

