package org.mybatis.generator.ext.daomapper.elements;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.util.GenUtil;

import java.util.Set;
import java.util.TreeSet;

/**
 */
public class UpdateByBeanListPKMethodGenerator extends AbstractDaoMapperMethodGenerator {

    @Override
    public void setIntrospectedTable(IntrospectedTable introspectedTable) {
        super.setIntrospectedTable(introspectedTable);
    }

    protected Method generateMethod(Set<FullyQualifiedJavaType> importedTypes) {
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);

        method.setReturnType(FullyQualifiedJavaType.getIntInstance());
        method.setName(GenUtil.getUpdateByBeanListMethodName(introspectedTable, GenUtil.ENUM_METHOD_TYPE.DAO_TYPE));
        FullyQualifiedJavaType listType = FullyQualifiedJavaType.getNewListInstance();
        importedTypes.add(listType);
        FullyQualifiedJavaType entityType = GenUtil.getEntityType(context, introspectedTable);
        importedTypes.add(entityType);
        String entityParaName = GenUtil.getGeneralEntityParamName4List(introspectedTable);
        method.addParameter(new Parameter(new FullyQualifiedJavaType("List<" + GenUtil.getEntityName(introspectedTable) + ">"), entityParaName));

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
        StringBuilder sb = new StringBuilder();

        method.addBodyLine("int result = 0;");
        String eachItem = GenUtil.getGeneralEntityParamName(introspectedTable);
        sb.setLength(0);
        sb.append("for (").append(GenUtil.getEntityName(introspectedTable)).append(" ").append(eachItem).append(": ").append(method.getParameters().get(0).getName()).append(") {");
        method.addBodyLine(sb.toString());

        sb.setLength(0);
        sb.append("result = update(\"").append(GenUtil.getUpdateByBeanMethodName(introspectedTable, GenUtil.ENUM_METHOD_TYPE.XML_TYPE)).append("\", ").append(eachItem).append(");");
        method.addBodyLine(sb.toString());

        method.addBodyLine("if (result == -1) {");
        method.addBodyLine("break;");
        method.addBodyLine("}");
        method.addBodyLine("}");
        method.addBodyLine("return result;");

        topLevelClass.addImportedTypes(importedTypes);
        topLevelClass.addMethod(method);
    }
}