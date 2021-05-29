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
package br.com.darot.ava.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.darot.ava.dto.CourseDTO;
import br.com.darot.ava.dto.CourseDetailsDTO;
import br.com.darot.ava.form.CourseForm;
import br.com.darot.ava.form.CourseFormUpdate;
import br.com.darot.ava.models.Course;
import br.com.darot.ava.models.User;
import br.com.darot.ava.repository.CourseRepository;
import javassist.NotFoundException;

@Service
public class CourseService {

	@Autowired
	private CourseRepository repository;

	@Autowired
	private UserService userService;

	public CourseDTO createCourse(CourseForm courseForm) {
		Course course = convert(courseForm);
		return CourseDTO.convert(repository.save(course));
	}

	public Optional<CourseDetailsDTO> findById(Long id) {
		Optional<Course> course = repository.findById(id);
		if (course.isPresent())
			return Optional.of(CourseDetailsDTO.convert(course.get()));
		return Optional.empty();
	}

	public List<CourseDTO> findAll() {
		List<Course> courses = repository.findAll();
		return courses.stream().map(CourseDTO::convert).collect(Collectors.toList());
	}

	public void delete(Long id) throws NotFoundException {
		if (repository.existsById(id)) {
			repository.deleteById(id);
		} else {
			throw new NotFoundException("Course ID Not Found");
		}
	}

	public CourseDTO update(Long id, @Valid CourseFormUpdate formUpdate) throws NotFoundException {
		Optional<Course> course = repository.findById(id);
		if (course.isPresent()) {
			course.get().setName(formUpdate.getName());
			course.get().setNickname(formUpdate.getNickname());
			course.get().setDescription(formUpdate.getDescription());
			course.get().setDuration(formUpdate.getDuration());
			return CourseDTO.convert(course.get());
		}
		throw new NotFoundException("Invalid Id");
	}


	private Course convert(CourseForm courseForm) {
		Course course = new Course();
		course.setName(courseForm.getName());
		course.setNickname(courseForm.getNickname());
		course.setDuration(courseForm.getDuration());
		course.setDescription(courseForm.getDescription());

		List<User> authors = courseForm.getAuthors().stream().map(author -> userService.findByEmail(author.getEmail()))
				.collect(Collectors.toList());

		course.setUsers(authors);
		return course;
	}

}
