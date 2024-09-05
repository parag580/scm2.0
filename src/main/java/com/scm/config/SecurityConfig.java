package com.scm.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


import com.scm.services.impl.SecurityCustomUserDetailService;


@Configuration
public class SecurityConfig {
  //user create and login using java code with in memory service
  //@Bean
  
//   @Bean
//   public UserDetailsService userDetailsService(){
//      PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//     UserDetails user1=User.withUsername("admin123")
   
//     .password(encoder.encode("admin123"))
//     .roles("ADMIN","USER")
//     .build();

//     UserDetails user2=User.withUsername("beebot")
//     .password(encoder.encode("beebot"))
//     .roles("ADMIN","USER")
//     .build();
//     var inMemoryUserDetailsManager=new InMemoryUserDetailsManager(user1,user2);
//     return inMemoryUserDetailsManager;
//   }
          @Autowired
        private SecurityCustomUserDetailService userDetailService;
          @Autowired
        private OAuthAuthenticationSuccessHandler handler;
    
        //configuration of authentication provider for spring security
      @Bean
      public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
        //user details service ka object
        daoAuthenticationProvider.setUserDetailsService(userDetailService);
        //password encoder ka object
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
      }

      @Bean
      public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        
        //configuration
        //urls configure kiya hai ki konse public rahege aur konse private rahege
        httpSecurity.authorizeHttpRequests(authorize->{
            //authorize.requestMatchers("/home").permitAll();
            authorize.requestMatchers("/user/**").authenticated();
            authorize.anyRequest().permitAll();
        });
        //form default login
        //agar hume kuch bhi change karna hoga form releated login
//httpSecurity.formLogin(Customizer.withDefaults());
       httpSecurity.formLogin(formLogin->{
             formLogin.loginPage("/login");
             formLogin.loginProcessingUrl("/authenticate");
             //we can also use seperately as formLogin.loginProcessingUrl("/authenticate")
             //(chaining)loginproccesing iss page pe hogi jo form hum login page se submit karege
             formLogin.successForwardUrl("/user/profile");
          //   formLogin.failureForwardUrl("/login?error=true");
             formLogin.usernameParameter("email");
             formLogin.passwordParameter("password");
            //  formLogin.failureHandler(new AuthenticationFailureHandler() {

            //     @Override
            //     public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            //             AuthenticationException exception) throws IOException, ServletException {
            //         // TODO Auto-generated method stub
            //         throw new UnsupportedOperationException("Unimplemented method 'onAuthenticationFailure'");
            //     }
                
            //  });
            //  formLogin.successHandler(new AuthenticationSuccessHandler() {

            //     @Override
            //     public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            //             Authentication authentication) throws IOException, ServletException {
            //         // TODO Auto-generated method stub
            //         throw new UnsupportedOperationException("Unimplemented method 'onAuthenticationSuccess'");
            //     }
                
            //  });
          
          });
          httpSecurity.csrf(AbstractHttpConfigurer::disable);
         
          httpSecurity.logout(logoutForm->{
            logoutForm.logoutUrl("/logout");
           logoutForm.logoutSuccessUrl("/login?logout=true");
          });
            // oauth configurations
            httpSecurity.oauth2Login(oauth2->{
              oauth2.loginPage("/login");
              oauth2.successHandler(handler);
            });
             
      
        return httpSecurity.build();

      }

       @Bean
      public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
      }
}
