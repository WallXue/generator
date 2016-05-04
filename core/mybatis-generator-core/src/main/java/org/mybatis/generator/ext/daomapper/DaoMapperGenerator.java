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
import org.mybatis.generator.ext.daomapper.elements.GetMaxIdClientGenerator;

import java.util.ArrayList;
import java.util.List;

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;
import static org.mybatis.generator.internal.util.messages.Messages.getString;

/**
 */
public class DaoMapperGenerator extends AbstractJavaClientGenerator {

    protected IntrospectedTable introspectedTableDecorator;

    public DaoMapperGenerator( ) {
        super(true);
    }

    @Override
    public void setIntrospectedTable(IntrospectedTable introspectedTable) {
        this.introspectedTableDecorator = IntrospectedTableDecorator.newProxyInstance((IntrospectedTableMyBatis3Impl)introspectedTable);
        super.setIntrospectedTable(introspectedTable);
    }

    @Override
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

    @Override
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
        return interfaze;
    }

    /**
     * 生成impl
     */
    protected TopLevelClass generateImpl(Interface interfaces) {
        FullyQualifiedJavaType type = new FullyQualifiedJavaType(
                introspectedTable.getDAOImplementationType());
        CommentGenerator commentGenerator = context.getCommentGenerator();
        TopLevelClass topLevelClass = new TopLevelClass(type);
        topLevelClass.setVisibility(JavaVisibility.PUBLIC);
        commentGenerator.addJavaFileComment(topLevelClass);

        topLevelClass.addImportedType(interfaces.getType());
        topLevelClass.addSuperInterface(interfaces.getType());
        commentGenerator.addModelClassComment(topLevelClass, introspectedTable);

        addMaxIdMethod(topLevelClass);
        return topLevelClass;
    }

//    /**
//     * 数目的方法
//     * @param interfaze
//     */
//    protected void addCountByExampleMethod(Interface interfaze) {
//        AbstractJavaMapperMethodGenerator methodGenerator = new CountByExampleMethodGenerator();
//        initializeAndExecuteGenerator(methodGenerator, interfaze);
//    }

//    /**
//     * 删除的方法
//     * @param interfaze
//     */
//    protected void addDeleteByExampleMethod(Interface interfaze) {
//        if (introspectedTable.getRules().generateDeleteByExample()) {
//            AbstractJavaMapperMethodGenerator methodGenerator = new DeleteByExampleMethodGenerator();
//            initializeAndExecuteGenerator(methodGenerator, interfaze);
//        }
//    }

//    /**
//     * 根据主键删除
//     * @param interfaze
//     */
//    protected void addDeleteByPrimaryKeyMethod(Interface interfaze) {
//        if (!introspectedTable.getRules().generateDeleteByPrimaryKey()) {
//            AbstractJavaMapperMethodGenerator methodGenerator = new DeleteByPrimaryKeyMethodGenerator(false);
//            initializeAndExecuteGenerator(methodGenerator, interfaze);
//        }
//    }
//
//    protected void addInsertMethod(Interface interfaze) {
//        if (introspectedTable.getRules().generateInsert()) {
//            AbstractJavaMapperMethodGenerator methodGenerator = new InsertMethodGenerator(false);
//            initializeAndExecuteGenerator(methodGenerator, interfaze);
//        }
//    }

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
