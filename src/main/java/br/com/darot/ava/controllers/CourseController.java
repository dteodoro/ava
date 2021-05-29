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

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.darot.ava.dto.CourseDTO;
import br.com.darot.ava.dto.CourseDetailsDTO;
import br.com.darot.ava.form.CourseForm;
import br.com.darot.ava.form.CourseFormUpdate;
import br.com.darot.ava.services.CourseService;
import javassist.NotFoundException;

@RestController
@RequestMapping("/courses")
public class CourseController {

	@Autowired
	private CourseService courseService;

	@GetMapping
	public List<CourseDTO> findAll() {
		return courseService.findAll();
	}

	@GetMapping("/{id}")
	@Transactional
	public ResponseEntity<CourseDetailsDTO> findById(@PathVariable Long id) {
		Optional<CourseDetailsDTO> courseDetailsDTO = courseService.findById(id);
		if (courseDetailsDTO.isPresent())
			return ResponseEntity.ok(courseDetailsDTO.get());
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@Transactional
	public ResponseEntity<CourseDTO> create(@RequestBody @Valid CourseForm courseForm) {
		return ResponseEntity.ok(courseService.createCourse(courseForm)); // TODO return status CREATED with URI
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<CourseDTO> update(@PathVariable Long id, @RequestBody @Valid CourseFormUpdate formUpdate) {
		try {
			return ResponseEntity.ok(courseService.update(id, formUpdate));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		try {
			courseService.delete(id);
			return ResponseEntity.ok().build();
		} catch (NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
