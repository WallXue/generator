package org.mybatis.generator.ext.daomapper;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.codegen.AbstractJavaClientGenerator;
import org.mybatis.generator.codegen.AbstractXmlGenerator;
import org.mybatis.generator.codegen.mybatis3.IntrospectedTableMyBatis3Impl;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.AbstractJavaMapperMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.XMLMapperGenerator;
import org.mybatis.generator.config.PropertyRegistry;
import org.mybatis.generator.ext.codegen.IntrospectedTableDecorator;
import org.mybatis.generator.ext.daomapper.elements.AbstractDaoMapperMethodGenerator;
import org.mybatis.generator.ext.daomapper.elements.DeleteByPKMethodGenerator;
import org.mybatis.generator.ext.daomapper.elements.GetMaxIdClientGenerator;
import org.mybatis.generator.ext.daomapper.elements.InsertBeanMethodGenerator;

import java.util.ArrayList;
import java.util.List;

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;
import static org.mybatis.generator.internal.util.messages.Messages.getString;

/**
 */
public class DaoMapperGenerator extends AbstractJavaClientGenerator {

    protected IntrospectedTable introspectedTableDecorator;
    protected String basePackage;
    FullyQualifiedJavaType entityType;

    public DaoMapperGenerator( ) {
        super(true);
    }

    public void setIntrospectedTable(IntrospectedTable introspectedTable) {
        this.introspectedTableDecorator = IntrospectedTableDecorator.newProxyInstance((IntrospectedTableMyBatis3Impl)introspectedTable);
        super.setIntrospectedTable(introspectedTable);


        String targetPackage = context.getJavaClientGeneratorConfiguration().getTargetPackage();
        basePackage = targetPackage.substring(0, targetPackage.indexOf(".dao."));
    }

    public AbstractXmlGenerator getMatchedXMLGenerator() {
        return new XMLMapperGenerator();
    }

    protected void initializeAndExecuteGenerator(
            AbstractJavaMapperMethodGenerator methodGenerator) {
        methodGenerator.setContext(context);
        methodGenerator.setIntrospectedTable(introspectedTable);
        methodGenerator.setProgressCallback(progressCallback);
        methodGenerator.setWarnings(warnings);
    }

    public List<CompilationUnit> getCompilationUnits() {
        progressCallback.startTask(getString("Progress.17", //$NON-NLS-1$
                introspectedTableDecorator.getFullyQualifiedTable().toString()));
        List<CompilationUnit> answer = new ArrayList<CompilationUnit>();
        Interface interfaze = generateInterfaces();
        TopLevelClass claz = generateImpl(interfaze);

        answer.add(interfaze);
        answer.add(claz);
        return answer;
    }

    protected Interface generateInterfaces() {
        CommentGenerator commentGenerator = context.getCommentGenerator();

        FullyQualifiedJavaType type = new FullyQualifiedJavaType(
                introspectedTableDecorator.getDAOInterfaceType());
        Interface interfaze = new Interface(type);
        interfaze.setVisibility(JavaVisibility.PUBLIC);
        commentGenerator.addJavaFileComment(interfaze);

        String rootInterface = introspectedTable
                .getTableConfigurationProperty(PropertyRegistry.ANY_ROOT_INTERFACE);
        if (!stringHasValue(rootInterface)) {
            rootInterface = context.getJavaClientGeneratorConfiguration()
                    .getProperty(PropertyRegistry.ANY_ROOT_INTERFACE);
        }

        if (stringHasValue(rootInterface)) {
            FullyQualifiedJavaType fqjt = new FullyQualifiedJavaType(
                    rootInterface);
            interfaze.addSuperInterface(fqjt);
            interfaze.addImportedType(fqjt);
        }

        addMaxIdMethod(interfaze);
        addDeleteByPrimaryKeyMethod(interfaze);
        addInsertMethod(interfaze);

        return interfaze;
    }

    /**
     * 生成impl
     */
    protected TopLevelClass generateImpl(Interface interfaces) {
        FullyQualifiedJavaType type = new FullyQualifiedJavaType(
                introspectedTableDecorator.getDAOImplementationType());
        CommentGenerator commentGenerator = context.getCommentGenerator();
        TopLevelClass topLevelClass = new TopLevelClass(type);
        topLevelClass.setVisibility(JavaVisibility.PUBLIC);
        commentGenerator.addJavaFileComment(topLevelClass);

        entityType = new FullyQualifiedJavaType(context.getJavaModelGeneratorConfiguration().getTargetPackage() + "." + introspectedTable.getFullyQualifiedTable().getDomainObjectName());
        topLevelClass.addImportedType(entityType);
        topLevelClass.addImportedType(basePackage + ".dao.impl.BaseDaoImp");
        topLevelClass.addImportedType(interfaces.getType());
        topLevelClass.setSuperClass("BaseDaoImp<" + introspectedTable.getFullyQualifiedTable().getDomainObjectName() + ">");
        topLevelClass.addSuperInterface(interfaces.getType());
        commentGenerator.addModelClassComment(topLevelClass, introspectedTable);

        addMaxIdMethod(topLevelClass);
        addDeleteByPrimaryKeyMethod(topLevelClass);
        addInsertMethod(topLevelClass);

        return topLevelClass;
    }

    /**
     * 根据主键删除
     */
    protected void addDeleteByPrimaryKeyMethod(JavaElement element) {
        AbstractDaoMapperMethodGenerator methodGenerator = new DeleteByPKMethodGenerator();
        initializeAndExecuteGenerator(methodGenerator);
        if (element instanceof Interface) {
            methodGenerator.addInterfaceElements((Interface)element);
        } else if (element instanceof TopLevelClass){
            methodGenerator.addTopLevelClassElements((TopLevelClass)element);
        }
    }

    protected void addInsertMethod(JavaElement element) {
        AbstractDaoMapperMethodGenerator methodGenerator = new InsertBeanMethodGenerator();
        initializeAndExecuteGenerator(methodGenerator);
        if (element instanceof Interface) {
            methodGenerator.addInterfaceElements((Interface)element);
        } else if (element instanceof TopLevelClass){
            methodGenerator.addTopLevelClassElements((TopLevelClass)element);
        }
    }

    protected void addMaxIdMethod(JavaElement element) {
        AbstractDaoMapperMethodGenerator methodGenerator = new GetMaxIdClientGenerator();
        initializeAndExecuteGenerator(methodGenerator);
        if (element instanceof Interface) {
            methodGenerator.addInterfaceElements((Interface)element);
        } else if (element instanceof TopLevelClass){
            methodGenerator.addTopLevelClassElements((TopLevelClass)element);
        }
    }
}
