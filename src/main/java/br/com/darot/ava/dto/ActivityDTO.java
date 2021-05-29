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

import br.com.darot.ava.models.Activity;
import br.com.darot.ava.models.enumerators.ActivityTypeEnum;
import lombok.Data;

@Data
public class ActivityDTO {

	private Long id;
	private String title;
	private ActivityTypeEnum activityType;

	public static ActivityDTO convert(Activity activity) {
		var activityDto = new ActivityDTO();
		activityDto.setId(activity.getId());
		activityDto.setTitle(activity.getTitle());
		activityDto.setActivityType(activity.getActivityType());
		return activityDto;
	}
}
