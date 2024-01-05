package com.duwan.hocba.config;

import java.io.IOException;

import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.duwan.hocba.dao.UserDao;
import com.duwan.hocba.object.UserObject;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests((requests) ->
                requests.requestMatchers("/notfound", "/assets/**").permitAll()
                        .requestMatchers("/hocsinh/**").hasAuthority("hs")
                        .requestMatchers("/giaovien/**").hasAuthority("gv")
                        .requestMatchers("/phuhuynh/**").hasAuthority("ph")
                        .requestMatchers("/quanlytaikhoan/**").hasAnyAuthority("hs", "gv", "ph")
                        .requestMatchers("/chart/**").hasAnyAuthority("hs", "gv", "ph")
                        .anyRequest().authenticated())
                .formLogin((form) ->
                        form.loginPage("/login").permitAll()
                                .failureUrl("/login")
                                .successHandler((request, response, authentication) -> {
                                    handleRoleRedirection(request, response, authentication);
                                }))
                .logout((logout) -> logout.permitAll().logoutSuccessUrl("/login"))
                .csrf(csrf -> csrf.disable());

		return http.build();
	}

	private void handleRoleRedirection(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
		Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
		String current_username = authentication.getName();
		HttpSession session = request.getSession();
	    session.setAttribute("current_username", current_username);
		if (roles.contains("hs")) {
			String linkhome = "/hocsinh/thoikhoabieu";
		    session.setAttribute("linkhome", linkhome);
			response.sendRedirect(linkhome);
		} else if (roles.contains("gv")) {
			String linkhome = "/giaovien/thoikhoabieu";
		    session.setAttribute("linkhome", linkhome);
			response.sendRedirect(linkhome);
		} else if (roles.contains("ph")) {
			String linkhome = "/phuhuynh/thoikhoabieu";
		    session.setAttribute("linkhome", linkhome);
			response.sendRedirect(linkhome);
		} else {
			response.sendRedirect("/notfound");
		}
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	private final UserDao userDao;

    public WebSecurityConfig(UserDao userDao) {
        this.userDao = userDao;
    }
    
    @Bean
    UserDetailsService userDetailsService() {
        return new CustomUserDetailsService(userDao);
    }

    private static class CustomUserDetailsService implements UserDetailsService {

        private final UserDao userDao;

        public CustomUserDetailsService(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            UserObject userObject = userDao.getUserByTendangnhap(username);

            if (userObject == null) {
                throw new UsernameNotFoundException("User not found with username: " + username);
            }
            else {
	            return User.withUsername(userObject.getTendangnhap())
	                    .password(userObject.getPassword())
	                    .authorities(userObject.getLoaitk())
	                    .build();
            }
        }
    }
}
