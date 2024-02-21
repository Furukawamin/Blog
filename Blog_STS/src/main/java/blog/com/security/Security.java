package blog.com.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import blog.com.models.UsersEntity;
import blog.com.services.UserService;

@Configuration
@EnableWebSecurity
public class Security {
	@Bean
	/*
	 * formLogin()メソッドを使用して、フォームベースのログインを構成しています。
	 * ログインページのURL、成功時のリダイレクト先、リクエストパラメータの名前、失
	 * 敗時のリダイレクト先などを指定しています。また、logout()メソッドを使用してログアウト機能を構成しており、
	 * ログアウト成功時のリダイレクト先を指定しています。
	 */
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.formLogin(login -> login// フォーム認証の設定記述開始
				.loginPage("/login")// ログイン画面のURL
				.defaultSuccessUrl("/blog", true) // ログインが成功したときの遷移先
				.usernameParameter("username") // リクエストパラメータのname属性を明示
				.passwordParameter("password")// リクエストパラメータのname属性を明示
				.failureUrl("/login?error")// ログインに失敗したときの遷移先
				.permitAll()// ログイン画面は未ログインでもアクセス可能
		).logout(logout -> logout// ログアウトの設定の記載を開始していきます。
				.logoutSuccessUrl("/login")// ログアウト成功後の遷移先URL
		).authorizeHttpRequests(authz -> authz// URLごとの許可の設定の記述を開始します
				.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
				.requestMatchers("/register", "/css/**", "/image/**").permitAll()// ログイン無しでもアクセス可能
				.anyRequest().authenticated()// 他のURLはログイン後のみアクセス可能
		);
		return http.build();
	}

	@Autowired
	UserService userService;

	@Bean
	/*
	 * userDetailsService()メソッドを定義しています。 このメソッドは、Spring
	 * Securityによって提供されるUserDetailsServiceインターフェースを実装しています
	 */
	public UserDetailsService userDetailsService() {
		UserDetails user1 = User.withDefaultPasswordEncoder().username("alice@mail.com").password("123456")
				.roles("USER").build();

		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager(user1);

		manager.createUser(
				User.withDefaultPasswordEncoder().username("bob@mail.com").password("Bob12345").roles("USER").build());

		// for-each to get all user info
		List<UsersEntity> allUsers = userService.getAllAccounts();
		for (UsersEntity user : allUsers) {
			manager.createUser(User.withDefaultPasswordEncoder().username(user.getUsername())
					.password(user.getPassword()).roles("USER").build());
		}
		return manager;
	}

}
