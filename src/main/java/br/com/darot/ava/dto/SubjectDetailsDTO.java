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
package br.com.darot.ava.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.darot.ava.models.Subject;
import lombok.Data;

@Data
public class SubjectDetailsDTO {
	private Long id;
	private String title;
	private Integer sequence;
	private List<ActivityDTO> activities;

	public static SubjectDetailsDTO convert(Subject subject) {
		SubjectDetailsDTO subjectDto = new SubjectDetailsDTO();
		subjectDto.setId(subject.getId());
		subjectDto.setTitle(subject.getTitle());
		subjectDto.setSequence(subject.getSequence());
		subjectDto
				.setActivities(subject.getActivities().stream().map(ActivityDTO::convert).collect(Collectors.toList()));
		return subjectDto;
	}
}
