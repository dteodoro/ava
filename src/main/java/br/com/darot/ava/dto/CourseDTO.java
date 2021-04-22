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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.darot.ava.models.Course;
import lombok.Data;

@Data
public class CourseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private String nickname;
	private String description;
	private int duration;
	private List<AuthorDTO> authors = new ArrayList<>();
	private List<Long> subjectIds = new ArrayList<>();

	public static CourseDTO convert(Course course) {
		var dto = new CourseDTO();
		dto.setName(course.getName());
		dto.setNickname(course.getNickname());
		dto.setDescription(course.getDescription());
		dto.setAuthors(course.getAuthors().stream().map(AuthorDTO::convert).collect(Collectors.toList()));
		dto.setSubjectIds(course.getSubjects().stream().map(subj -> subj.getId()).collect(Collectors.toList()));
		return dto;
	}

}
