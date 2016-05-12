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

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.util.GenUtil;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 */
public class SelectByPrimaryKeyClientGenerator extends AbstractDaoMapperMethodGenerator {

    protected Method generateMethod(Set<FullyQualifiedJavaType> importedTypes) {
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);

        FullyQualifiedJavaType beanType = GenUtil.getEntityType(context, introspectedTable);
        importedTypes.add(beanType);
        method.setReturnType(beanType);
        method.setName(GenUtil.getSelectByPrimaryKeyMethodName(introspectedTable, GenUtil.ENUM_METHOD_TYPE.DAO_TYPE));

        List<IntrospectedColumn> introspectedColumns = introspectedTable
                .getPrimaryKeyColumns();
//        StringBuilder sb = new StringBuilder();
        for (IntrospectedColumn introspectedColumn : introspectedColumns) {
            FullyQualifiedJavaType type = introspectedColumn
                    .getFullyQualifiedJavaType();
            importedTypes.add(type);
            Parameter parameter = new Parameter(type, introspectedColumn
                    .getJavaProperty());
            method.addParameter(parameter);
        }

        context.getCommentGenerator().addGeneralMethodComment(method,
                introspectedTable);

        return method;
    }

    @Override
    public void addInterfaceElements(Interface interfaze) {
        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
        Method method = generateMethod(importedTypes);
        if (method == null)
            return;

        interfaze.addImportedTypes(importedTypes);
        interfaze.addMethod(method);
    }

    @Override
    public void addTopLevelClassElements(TopLevelClass topLevelClass) {
        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
        Method method = generateMethod(importedTypes);
        if (method == null)
            return;

        method.setVisibility(JavaVisibility.PUBLIC);
        List<IntrospectedColumn> introspectedColumns = introspectedTable
                .getPrimaryKeyColumns();
        if (introspectedColumns.size() == 1) {
            StringBuilder sb = new StringBuilder();
            sb.append("return ");
            sb.append("getPojoById(\"selectByPrimaryKey\"");
            sb.append(", ");
            sb.append(method.getParameters().get(0).getName());
            sb.append("); ");
            method.addBodyLine(sb.toString());

        } else {

        }

        topLevelClass.addImportedTypes(importedTypes);
        topLevelClass.addMethod(method);
    }
}
