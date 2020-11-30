package com.expatrio.auth.service;

import com.expatrio.auth.beans.User;


//@Component
//@Service("customUserDetailsService")
public class CustomUserDetailsService extends User //implements UserDetailsService
 {

//	@Autowired
//	private PasswordEncoder passwordEncoder;
//
//	@Autowired
//	WebClientAPI webClientAPI;
//
//   public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//	   UserProfile profile = null;
//   	try {
//		   profile = webClientAPI.getUserFromUserService(email);
//	   } catch (IOException e) {
//		   e.printStackTrace();
//	   }
//
//   	 	 return new org.springframework.security.core.userdetails.User(profile.getEmail(),
//				 profile.getPassword(), getAuthorities(profile));
//     }
//
//	 public Collection<? extends GrantedAuthority> getAuthorities(UserProfile profile){
//		    return List.of(new SimpleGrantedAuthority("ROLE_Admin"));
//		}
//
//	private boolean decodePassword(String pwd,String dbPwd) {
//		return passwordEncoder.matches(pwd,dbPwd);
//	}
}
