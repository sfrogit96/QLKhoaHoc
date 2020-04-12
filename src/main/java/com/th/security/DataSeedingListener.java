package com.th.security;
 
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.th.entity.ChucVu;
import com.th.entity.Role;
import com.th.entity.User;
import com.th.repository.ChucVuRepository;
import com.th.repository.RoleRepository;
import com.th.repository.UserRepository;
 

@Component
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired 
	private PasswordEncoder passwordEncoder;
	
	@Autowired 
	private ChucVuRepository chucVuRepository;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		// Roles
		if (roleRepository.findByName("ROLE_ADMIN") == null) {
			roleRepository.save(new Role("ROLE_ADMIN"));
		}
		
		if (roleRepository.findByName("ROLE_MANAGER") == null) {
			roleRepository.save(new Role("ROLE_MANAGER"));
		}
		
		// Admin account
		if (userRepository.findByEmail("admin@gmail.com") == null) {
			User admin = new User();
			admin.setEmail("admin@gmail.com");
			admin.setPassword(passwordEncoder.encode("Admin@1234"));
			HashSet<Role> roles = new HashSet<>();
			roles.add(roleRepository.findByName("ROLE_ADMIN"));
			roles.add(roleRepository.findByName("ROLE_MANAGER"));
			admin.setRoles(roles);
			userRepository.save(admin);
		}
		
		// Member account
		if (userRepository.findByEmail("manager@gmail.com") == null) {
			User user = new User();
			user.setEmail("manager@gmail.com");
			user.setPassword(passwordEncoder.encode("Manager@1234"));
			HashSet<Role> roles = new HashSet<>();
			roles.add(roleRepository.findByName("ROLE_MANAGER"));
			user.setRoles(roles);
			userRepository.save(user);
		}
		
		if (chucVuRepository.findByTenchucvu("Học Viên") == null) {
			ChucVu cv1 = new ChucVu();
			cv1.setTenchucvu("Học Viên");
			chucVuRepository.save(cv1);
		}
		
		if (chucVuRepository.findByTenchucvu("Giảng Viên") == null) {
			ChucVu cv2 = new ChucVu();
			cv2.setTenchucvu("Giảng Viên");
			chucVuRepository.save(cv2);
		}
	}
}
