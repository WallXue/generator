/**
 *    Copyright 2006-2015 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.mybatis.generator.ext.daomapper.elements;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.util.GenUtil;

import java.util.Set;

/**
 */
public class SelectByBeanPageMethodGenerator extends SelectByBeanMethodGenerator {

    @Override
    public void setIntrospectedTable(IntrospectedTable introspectedTable) {
        super.setIntrospectedTable(introspectedTable);
    }

    @Override
    protected Method generateMethod(Set<FullyQualifiedJavaType> importedTypes) {
        Method method = super.generateMethod(importedTypes);
        method.setName(GenUtil.getSelectByBeanPageMethodName(introspectedTable, GenUtil.ENUM_METHOD_TYPE.DAO_TYPE));
        return method;
    }

    @Override
    public void addInterfaceElements(Interface interfaze) {
        super.addInterfaceElements(interfaze);
    }

    @Override
    public void addTopLevelClassElements(TopLevelClass topLevelClass) {
        super.addTopLevelClassElements(topLevelClass);
    }
}
