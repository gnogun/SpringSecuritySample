package com.gno.sample;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.Filter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.gno.sample.dto.Person;
import com.gno.sample.repository.PersonRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/security-context.xml" })
@WebAppConfiguration
public class LoginTest {

	@Autowired
	private WebApplicationContext context;

	@Autowired
	private Filter springSecurityFilterChain;

	@Autowired
	private UserDetailsService userService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private PersonRepository repository;

	private MockMvc mvc;

	@Before
	public void setup() {
		mvc = MockMvcBuilders.webAppContextSetup(context)
				.addFilters(springSecurityFilterChain).build();

		Person person = new Person();

		person.setEmail("gnogun@naver.com");
		person.setId("gno");
		person.setPassword(passwordEncoder.encode("gno"));
		person.setAuth("ROLE_ADMIN");
		
		Person person2 = new Person();

		person2.setEmail("gnogun@naver.com");
		person2.setId("gno123");
		person2.setPassword(passwordEncoder.encode("gno123"));
		person2.setAuth("ROLE_USER");

		repository.save(person);
		repository.save(person2);
	}

//	@Test
	public void requestProtectedUrlWithUserDetails() throws Exception {
		UserDetails userDetails = userService.loadUserByUsername("gno");
		mvc.perform(get("/main.do").with(user(userDetails)))
		// Ensure we got past Security
				.andExpect(status().isNotFound())
				// Ensure it appears we are authenticated with user
				.andExpect(
						authenticated()
								.withAuthenticationPrincipal(userDetails));
	}

	@Test
	public void formLoginTest() throws Exception {
		mvc.perform(
				post("/loginProcess").param("username","gno").param("password", "gno"))
				.andDo(print())
				.andExpect(status().isFound());
		
		
	}
}
