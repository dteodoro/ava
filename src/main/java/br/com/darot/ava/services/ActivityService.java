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

import br.com.darot.ava.dto.ActivityDTO;
import br.com.darot.ava.form.ActivityForm;
import br.com.darot.ava.models.Activity;
import br.com.darot.ava.models.Subject;
import br.com.darot.ava.models.enumerators.ActivityTypeEnum;
import br.com.darot.ava.repository.ActivityRepository;

@Service
public class ActivityService {
	
	@Autowired
	private ActivityRepository repository;

	public Optional<ActivityDTO> findById(Long id, Long subjectId) {
		Optional<Activity> activities = repository.findByIdAndSubjectId(id,subjectId);
		if (activities.isPresent())
			return Optional.of(ActivityDTO.convert(activities.get()));
		return Optional.empty();
	}

	public List<ActivityDTO> findAllActivitiesBySubjectId(Long subjectId) {
		List<Activity> activity = repository.findBySubjectId(subjectId);
		return activity.stream().map(ActivityDTO::convert).collect(Collectors.toList());
	}

	public ActivityDTO createCourse(@Valid ActivityForm activityForm) {
		Activity activity = new Activity();
		activity.setActivityType(ActivityTypeEnum.valueOf(activityForm.getActivityType()));
		activity.setTitle(activityForm.getTitle());
		
		Subject subject = new Subject();
		subject.setId(activityForm.getServiceId());
		
		activity.setSubject(subject);
		return ActivityDTO.convert(repository.save(activity));
	}
}
