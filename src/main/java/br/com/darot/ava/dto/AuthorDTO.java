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

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.sun.istack.NotNull;

import br.com.darot.ava.models.User;
import lombok.Data;

@Data
public class AuthorDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	public String name;
	@NotBlank
	@NotEmpty
	@NotNull
	public String email;

	public static AuthorDTO convert(User user) {
		var dto = new AuthorDTO();
		dto.setName(user.getName());
		dto.setEmail(user.getEmail());
		return dto;
	}

}
