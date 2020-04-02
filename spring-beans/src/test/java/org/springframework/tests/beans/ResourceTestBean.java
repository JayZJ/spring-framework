package org.springframework.tests.beans;

/*
 * Copyright 2002-2011 the original author or authors.
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


import java.io.InputStream;
import java.util.Map;

import org.springframework.core.io.ContextResource;
import org.springframework.core.io.Resource;

/**
 * @author Juergen Hoeller
 * @since 01.04.2004
 */
public class ResourceTestBean {

	private Long id;
	private String name;
	private String email;

	public ResourceTestBean(Long id, String name, String email) {
		this.id = id;
		this.name = name;
		this.email = email;
	}

	public ResourceTestBean() {
	}

	@Override
	public String toString() {
		return "ResourceTestBean{" +
				"id=" + id +
				", name='" + name + '\'' +
				", email='" + email + '\'' +
				'}';
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}

