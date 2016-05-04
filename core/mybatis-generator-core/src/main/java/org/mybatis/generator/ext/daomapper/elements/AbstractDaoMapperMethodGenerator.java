package org.mybatis.generator.ext.daomapper.elements;

import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.AbstractJavaMapperMethodGenerator;

/**
 */
public abstract class AbstractDaoMapperMethodGenerator extends AbstractJavaMapperMethodGenerator {

    public abstract void addTopLevelClassElements(TopLevelClass topLevelClass);

}
