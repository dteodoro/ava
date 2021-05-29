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

import br.com.darot.ava.dto.SubjectDTO;
import br.com.darot.ava.dto.SubjectDetailsDTO;
import br.com.darot.ava.form.SubjectForm;
import br.com.darot.ava.form.SubjectFormUpdate;
import br.com.darot.ava.services.SubjectService;
import javassist.NotFoundException;

@RestController
@RequestMapping("/courses/{courseId}/subjects")
public class SubjectController {

	@Autowired
	private SubjectService subjectService;

	@GetMapping("/{id}")
	@Transactional
	public ResponseEntity<SubjectDetailsDTO> findById(@PathVariable Long id) {
		try {
			return ResponseEntity.ok(subjectService.findById(id));
		} catch (NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping
	public ResponseEntity<List<SubjectDTO>> findAllSubjectByCourseId(@PathVariable Long courseId) {
		return ResponseEntity.ok(subjectService.findAllSubjectByCourseId(courseId));
	}

	@PostMapping
	@Transactional
	public SubjectDTO create(@PathVariable Long courseId, @RequestBody SubjectForm subjectForm) {
		return subjectService.create(courseId, subjectForm);// TODO return status CREATED with URI
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<SubjectDTO> update(@PathVariable Long id, @RequestBody @Valid SubjectFormUpdate formUpdate) {
		try {
			return ResponseEntity.ok(subjectService.update(id, formUpdate));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		try {
			subjectService.delete(id);
			return ResponseEntity.ok().build();
		} catch (NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}


}
