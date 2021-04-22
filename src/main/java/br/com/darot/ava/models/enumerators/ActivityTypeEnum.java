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
package br.com.darot.ava.models.enumerators;

import javax.persistence.Embeddable;

import br.com.darot.ava.models.activities.ActivityType;
import br.com.darot.ava.models.activities.DocumentActivity;
import br.com.darot.ava.models.activities.VideActivity;

@Embeddable
public enum ActivityTypeEnum {

	VIDEO {
		@Override
		public ActivityType getType() {
			return new VideActivity();
		}
	},

	DOCUMENT{
		@Override
		public ActivityType getType() {
			return new DocumentActivity();
		}
	},

	INFO {
		@Override
		public ActivityType getType() {
			// TODO Auto-generated method stub
			return null;
		}
	},

	TIP {
		@Override
		public ActivityType getType() {
			// TODO Auto-generated method stub
			return null;
		}
	},
	LINK {
		@Override
		public ActivityType getType() {
			// TODO Auto-generated method stub
			return null;
		}
	};
	
	public abstract ActivityType getType();
	
}
