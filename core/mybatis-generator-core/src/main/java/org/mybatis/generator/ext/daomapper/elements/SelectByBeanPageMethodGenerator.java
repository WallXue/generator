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
        method.setName(GenUtil.getSelectByBeanPageMethodName(introspectedTable));
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
