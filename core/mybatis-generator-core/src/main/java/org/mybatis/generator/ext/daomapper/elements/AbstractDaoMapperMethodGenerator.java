package org.mybatis.generator.ext.daomapper.elements;

import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.AbstractJavaMapperMethodGenerator;

/**
 */
public abstract class AbstractDaoMapperMethodGenerator extends AbstractJavaMapperMethodGenerator {

//    FullyQualifiedJavaType entityType;
//
//    public FullyQualifiedJavaType getEntityType() {
//        introspectedTable.get
//    }
//
//    public void setEntityType(FullyQualifiedJavaType entityType) {
//        this.entityType = entityType;
//    }

    public abstract void addTopLevelClassElements(TopLevelClass topLevelClass);


}
