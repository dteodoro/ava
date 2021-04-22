/*
 * Copyright 2021 - Dário Teodoro
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.com.darot.ava.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.darot.ava.dto.CourseDTO;
import br.com.darot.ava.models.Course;
import br.com.darot.ava.models.User;
import br.com.darot.ava.repository.CourseRepository;
import br.com.darot.ava.services.UserService;

@RestController
@RequestMapping("/courses")
public class CourseController {

	@Autowired
	private UserService userService;
	@Autowired
	private CourseRepository repository;

	@PostMapping
	public CourseDTO createCourse(@RequestBody CourseDTO courseDTO) {
		var course = new Course();
		List<User> authors = courseDTO.getAuthors().stream()
				.map(author -> userService.findByEmail(author.getEmail()))
				.collect(Collectors.toList());

		course.setName(courseDTO.getName());
		course.setNickname(courseDTO.getNickname());
		course.setDuration(courseDTO.getDuration());
		course.setDescription(courseDTO.getDescription());
		course.setAuthors(authors);
		course.setSubjects(new ArrayList<>());

		return CourseDTO.convert(repository.save(course));

	}

	@GetMapping
	public String hello() {
		return "Hello World";
	}

}
