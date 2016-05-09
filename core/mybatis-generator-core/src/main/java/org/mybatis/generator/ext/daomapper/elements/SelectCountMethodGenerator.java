package org.mybatis.generator.ext.daomapper.elements;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.util.GenUtil;
import org.mybatis.generator.util.StringUtil;

import java.util.Set;
import java.util.TreeSet;

/**
 */
public class SelectCountMethodGenerator extends AbstractDaoMapperMethodGenerator {

    @Override
    public void setIntrospectedTable(IntrospectedTable introspectedTable) {
        super.setIntrospectedTable(introspectedTable);
    }

    protected Method generateMethod(Set<FullyQualifiedJavaType> importedTypes) {
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);

        method.setReturnType(new FullyQualifiedJavaType("Long"));
        method.setName(GenUtil.getSelectCountMethodName(introspectedTable));
        String entityName = introspectedTable.getFullyQualifiedTable().getDomainObjectName();
        String entityParaName = StringUtil.lowerCase(entityName);
        FullyQualifiedJavaType entityType = GenUtil.getEntityType(context, introspectedTable);
        importedTypes.add(entityType);
        method.addParameter(new Parameter(entityType, entityParaName));

        context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);
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
        StringBuilder sb = new StringBuilder();
        sb.append("return (Long) getRowCount(\"");
        sb.append(GenUtil.getSelectCountMethodName(introspectedTable, true));
        sb.append("\", ");
        sb.append(method.getParameters().get(0).getName());
        sb.append("); ");
        method.addBodyLine(sb.toString());

        topLevelClass.addImportedTypes(importedTypes);
        topLevelClass.addMethod(method);
    }
}
