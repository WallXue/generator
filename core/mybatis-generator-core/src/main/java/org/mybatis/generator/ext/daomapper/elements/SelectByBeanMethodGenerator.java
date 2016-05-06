package org.mybatis.generator.ext.daomapper.elements;

import org.apache.commons.lang.StringUtils;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.util.GenUtil;

import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Administrator on 5/6/2016.
 */
public class SelectByBeanMethodGenerator extends AbstractDaoMapperMethodGenerator {

    @Override
    public void setIntrospectedTable(IntrospectedTable introspectedTable) {
        super.setIntrospectedTable(introspectedTable);
    }

    protected Method generateMethod(Set<FullyQualifiedJavaType> importedTypes) {
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);

//        List<User>
        String entityName = introspectedTable.getFullyQualifiedTable().getDomainObjectName();
        String entityParaName = StringUtils.lowerCase(entityName);

        FullyQualifiedJavaType listType = FullyQualifiedJavaType.getNewListInstance();
        method.setReturnType(new FullyQualifiedJavaType("List<" + entityName + ">"));
        importedTypes.add(listType);
        method.setName("select" + entityName + "ByBean");
        method.addParameter(new Parameter(GenUtil.getEntityType(context, introspectedTable), "entity"));

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
        sb.append("return ");
        sb.append("findByProperty(\"select\"");
        sb.append(introspectedTable.getFullyQualifiedTable().getDomainObjectName());
        sb.append("\", entity); ");
        sb.append(';');
        method.addBodyLine(sb.toString());

        topLevelClass.addImportedTypes(importedTypes);
        topLevelClass.addMethod(method);
    }
}