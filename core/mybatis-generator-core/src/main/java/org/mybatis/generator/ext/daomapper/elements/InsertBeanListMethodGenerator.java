package org.mybatis.generator.ext.daomapper.elements;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.util.GenUtil;

import java.util.Set;
import java.util.TreeSet;

/**
 * 新增entity方法：
 * Integer addXXXs(List<XXX> listXXX)  { return saveList("insertXXX", user); }
 */
public class InsertBeanListMethodGenerator extends AbstractDaoMapperMethodGenerator {

    public void setIntrospectedTable(IntrospectedTable introspectedTable) {
        super.setIntrospectedTable(introspectedTable);
    }

    protected Method generateMethod(Set<FullyQualifiedJavaType> importedTypes) {
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);

        method.setReturnType(FullyQualifiedJavaType.getIntInstance());
        method.setName(GenUtil.getInsertBeanListMethodName(introspectedTable, GenUtil.ENUM_METHOD_TYPE.DAO_TYPE));
        FullyQualifiedJavaType listType = FullyQualifiedJavaType.getNewListInstance();
        importedTypes.add(listType);
        FullyQualifiedJavaType entityType = GenUtil.getEntityType(context, introspectedTable);
        importedTypes.add(entityType);
        String entityParaName = GenUtil.getGeneralEntityParamName4ListWithList(introspectedTable);
        method.addParameter(new Parameter(new FullyQualifiedJavaType("List<" + GenUtil.getEntityName(introspectedTable) + ">"), entityParaName));

        context.getCommentGenerator().addGeneralMethodComment(method,
                introspectedTable);
        return method;
    }

    public void addInterfaceElements(Interface interfaze) {
        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
        Method method = generateMethod(importedTypes);
        if (method == null)
            return;

        interfaze.addImportedTypes(importedTypes);
        interfaze.addMethod(method);
    }

    public void addTopLevelClassElements(TopLevelClass topLevelClass) {
        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
        Method method = generateMethod(importedTypes);
        if (method == null)
            return;

        method.setVisibility(JavaVisibility.PUBLIC);
        StringBuilder sb = new StringBuilder();
        sb.append("return ");
        sb.append("saveList(\"");
        sb.append(GenUtil.getInsertBeanListMethodName(introspectedTable, GenUtil.ENUM_METHOD_TYPE.XML_TYPE));
        sb.append("\"");
        sb.append(", ");
        sb.append(method.getParameters().get(0).getName());
        sb.append(");");
        method.addBodyLine(sb.toString());

        topLevelClass.addImportedTypes(importedTypes);
        topLevelClass.addMethod(method);
    }
}