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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.darot.ava.dto.ActivityDTO;
import br.com.darot.ava.form.ActivityForm;
import br.com.darot.ava.services.ActivityService;
import br.com.darot.ava.services.SubjectService;

@RestController
@RequestMapping("/courses/{courseId}/subjects/{subjectId}/activities")
public class ActivityController {

	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private SubjectService subjectService;

	@GetMapping("/{id}")
	public ResponseEntity<ActivityDTO> findById(@PathVariable Long id, @PathVariable Long subjectId) {
		Optional<ActivityDTO> activity = activityService.findById(id,subjectId);
		if (activity.isPresent())
			return ResponseEntity.ok(activity.get());
		return ResponseEntity.notFound().build();
	}

	@GetMapping
	public ResponseEntity<List<ActivityDTO>> findAllActivitiesBySubjectId(@PathVariable Long subjectId) {
		return ResponseEntity.ok(activityService.findAllActivitiesBySubjectId(subjectId));
	}

	@PostMapping
	@Transactional
	public ResponseEntity<ActivityDTO> create(@PathVariable Long courseId, @PathVariable Long subjectId, @RequestBody @Valid ActivityForm activityForm) {
		if(subjectService.existsByIdAndCourseId(subjectId, courseId)) {
			activityForm.setServiceId(subjectId);
			return ResponseEntity.ok(activityService.createCourse(activityForm)); // TODO return status CREATED with URI
		}else {
			return ResponseEntity.notFound().build();
		}
	}
}
