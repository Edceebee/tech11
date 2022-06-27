package com.Administrator.tech11;

import com.Administrator.tech11.models.Users;
import com.Administrator.tech11.models.dto.UsersDto;
import com.Administrator.tech11.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class Tech11ApplicationTests {

	@Autowired
	UserService userService;

	@Order(1)
	@Test
	void testWeCanGetAllUsers() {
		List<Users> foundCountries = userService.getAllUsers(0, 5);
		assertThat((long) foundCountries.size()).isEqualTo(5);
	}

	@Test
	@Order(2)
	void testToFindCountryByName() {
		Users foundUser = userService.findById(3L);

		assertThat(foundUser.getEmail()).isEqualTo("e.dan@email.com");
		assertThat(foundUser.getFirstName()).isEqualTo("Esther");
		assertThat(foundUser.getBirthday()).isEqualTo(LocalDate.of(1991, 12, 23));
		assertThat(foundUser.getLastName()).isEqualTo("Dan");
		assertThat(foundUser.getPassword()).isEqualTo("estherpass");
	}


	@Test
	@Order(3)
	void testToUser() {
		Users user = new Users();
		Users newUser = userService.addUser(user);

		assertThat(newUser).isNotNull();
		assertThat(newUser.getEmail()).isEqualTo(null);
		assertThat(newUser.getPassword()).isEqualTo(null);
		assertThat(newUser.getFirstName()).isEqualTo(null);
		assertThat(newUser.getId()).isNotNull();
	}

	@Test
	@Order(4)
	void testThatUserCanBeModified() {

		Users user = new Users(null, "Princess", "Felix", "p.felix@email.com", LocalDate.of(1995, 4, 17), "princesspass");

		userService.addUser(user);

		assertThat(user.getFirstName()).isEqualTo("Princess");
		assertThat(user.getLastName()).isEqualTo("Felix");
		assertThat(user.getEmail()).isEqualTo("p.felix@email.com");

		log.info("existing user ---> {}", user);

		UsersDto userDto = new UsersDto();
		userDto.setFirstName("Princess");
		userDto.setLastName("Charles");
		userDto.setEmail("p.charles@email.com");

		Users updatedUser = userService.modifyUser(user.getId(), userDto);

		assertThat(updatedUser.getFirstName()).isEqualTo("Princess");
		assertThat(updatedUser.getLastName()).isEqualTo("Charles");
		assertThat(updatedUser.getEmail()).isEqualTo("p.charles@email.com");

		log.info("updated user ---> {}", updatedUser);

	}
}
