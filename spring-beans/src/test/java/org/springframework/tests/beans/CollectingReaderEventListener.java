/*
 * Copyright 2002-2016 the original author or authors.
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

package org.springframework.tests.beans;

import java.util.*;

import org.junit.Test;
import org.springframework.beans.factory.parsing.AliasDefinition;
import org.springframework.beans.factory.parsing.ComponentDefinition;
import org.springframework.beans.factory.parsing.DefaultsDefinition;
import org.springframework.beans.factory.parsing.ImportDefinition;
import org.springframework.beans.factory.parsing.ReaderEventListener;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

/**
 * @author Rob Harrop
 * @author Juergen Hoeller
 */
public class CollectingReaderEventListener implements ReaderEventListener {

	private final List<DefaultsDefinition> defaults = new LinkedList<>();

	private final Map<String, ComponentDefinition> componentDefinitions = new LinkedHashMap<>(8);

	private final Map<String, List<AliasDefinition>> aliasMap = new LinkedHashMap<>(8);

	private final List<ImportDefinition> imports = new LinkedList<>();


	@Override
	public void defaultsRegistered(DefaultsDefinition defaultsDefinition) {
		this.defaults.add(defaultsDefinition);
	}

	public List<DefaultsDefinition> getDefaults() {
		return Collections.unmodifiableList(this.defaults);
	}

	@Override
	public void componentRegistered(ComponentDefinition componentDefinition) {
		this.componentDefinitions.put(componentDefinition.getName(), componentDefinition);
	}

	public ComponentDefinition getComponentDefinition(String name) {
		return this.componentDefinitions.get(name);
	}

	public ComponentDefinition[] getComponentDefinitions() {
		Collection<ComponentDefinition> collection = this.componentDefinitions.values();
		return collection.toArray(new ComponentDefinition[collection.size()]);
	}

	@Override
	public void aliasRegistered(AliasDefinition aliasDefinition) {
		List<AliasDefinition> aliases = this.aliasMap.get(aliasDefinition.getBeanName());
		if (aliases == null) {
			aliases = new ArrayList<>();
			this.aliasMap.put(aliasDefinition.getBeanName(), aliases);
		}
		aliases.add(aliasDefinition);
	}

	public List<AliasDefinition> getAliases(String beanName) {
		List<AliasDefinition> aliases = this.aliasMap.get(beanName);
		return (aliases != null ? Collections.unmodifiableList(aliases) : null);
	}

	@Override
	public void importProcessed(ImportDefinition importDefinition) {
		this.imports.add(importDefinition);
	}

	public List<ImportDefinition> getImports() {
		return Collections.unmodifiableList(this.imports);
	}

	@Test
	public void ioc() {
		Resource resource = new FileSystemResource("C:\\Users\\Admin\\IdeaProjects\\codes\\spring-framework\\spring-context\\src\\test\\resources\\org\\springframework\\context\\support\\ClassPathXmlApplicationContextTests-resourceImport.xml");
		DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(defaultListableBeanFactory);
		xmlBeanDefinitionReader.loadBeanDefinitions(resource);
		Object yourMessageSource = defaultListableBeanFactory.getBean("maybeOne");
		System.out.println(yourMessageSource);
		System.out.println(!(1 == 3) || (2 == 3));
		Set<Integer> set = new HashSet<>();
		System.out.println(set.add(1));
		System.out.println(set.add(1));
		Map<String, Integer> map = new HashMap<>();
		map.put("aaa", 1);
		map.forEach(this::ttt);
		System.out.println(map.remove("aaa"));
	}

	void ttt(String a, Integer b) {
		System.out.println(a);
		System.out.println(b);
	}

}
