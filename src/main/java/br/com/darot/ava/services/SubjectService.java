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

import br.com.darot.ava.dto.SubjectDTO;
import br.com.darot.ava.dto.SubjectDetailsDTO;
import br.com.darot.ava.form.SubjectForm;
import br.com.darot.ava.form.SubjectFormUpdate;
import br.com.darot.ava.models.Course;
import br.com.darot.ava.models.Subject;
import br.com.darot.ava.repository.SubjectRepository;
import javassist.NotFoundException;

@Service
public class SubjectService {

	@Autowired
	private SubjectRepository repository;

	public SubjectDTO create(Long courseId, SubjectForm subjectForm) {
		Subject subject = new Subject();
		subject.setTitle(subjectForm.getTitle());
		subject.setSequence(subjectForm.getSequence());
		Course course = new Course();
		course.setId(courseId);
		subject.setCourse(course);

		return SubjectDTO.convert(repository.save(subject));
	}

	public SubjectDetailsDTO findByIdAndCourseId(Long subjectId, Long courseId) throws NotFoundException {
		Optional<Subject> subject = repository.findByIdAndCourseId(subjectId, courseId);
		if (subject.isPresent())
			return SubjectDetailsDTO.convert(subject.get());
		throw new NotFoundException("Subject id not found");
	}

	public List<SubjectDTO> findAllSubjectByCourseId(Long courseId) {
		List<Subject> subjects = repository.findAllByCourseId(courseId);
		return subjects.stream().map(SubjectDTO::convert).collect(Collectors.toList());
	}

	public void delete(Long id) throws NotFoundException {
		if (repository.existsById(id)) {
			repository.deleteById(id);
		} else {
			throw new NotFoundException("Subject id not found");
		}
	}

	public SubjectDTO update(Long id, @Valid SubjectFormUpdate formUpdate) throws NotFoundException {
		Optional<Subject> subject = repository.findById(id);
		if (subject.isPresent()) {
			subject.get().setTitle(formUpdate.getTitle());
			subject.get().setSequence(formUpdate.getSequence());
			return SubjectDTO.convert(subject.get());
		}
		throw new NotFoundException("Invalid Subject Id");
	}
	
	public boolean existsByIdAndCourseId(Long subjectId, Long courseId) {
		return repository.existsByIdAndCourseId(subjectId,courseId);
	}
}
