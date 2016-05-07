package org.mybatis.generator.ext.daomapper;

import org.apache.commons.lang.StringUtils;
import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.codegen.AbstractJavaClientGenerator;
import org.mybatis.generator.codegen.AbstractXmlGenerator;
import org.mybatis.generator.codegen.mybatis3.IntrospectedTableMyBatis3Impl;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.AbstractJavaMapperMethodGenerator;
import org.mybatis.generator.config.PropertyRegistry;
import org.mybatis.generator.ext.codegen.IntrospectedTableDecorator;
import org.mybatis.generator.ext.daomapper.elements.*;
import org.mybatis.generator.ext.xmlmapper.XMLExtMapperGenerator;
import org.mybatis.generator.util.GenUtil;

import java.util.ArrayList;
import java.util.List;

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;
import static org.mybatis.generator.internal.util.messages.Messages.getString;

/**
 */
public class DaoMapperGenerator extends AbstractJavaClientGenerator {

    protected IntrospectedTable introspectedTableDecorator;
    protected String basePackage;

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
        return new XMLExtMapperGenerator();
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
        addUpdateMethod(interfaze);
        addSelectByPrimaryKeyMethod(interfaze);
        addSelectByBeanMethod(interfaze);

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

        topLevelClass.addAnnotation("@Repository(\"" + StringUtils.uncapitalize(interfaces.getType().getShortName()) + "\")");
        topLevelClass.addImportedType(GenUtil.getEntityType(context, introspectedTable));
        topLevelClass.addImportedType(basePackage + ".dao.impl.BaseDaoImp");
        topLevelClass.addImportedType(interfaces.getType());
        topLevelClass.addImportedType("org.springframework.stereotype.Repository");
        topLevelClass.addImportedType("org.springframework.beans.factory.annotation.Qualifier");
        topLevelClass.addImportedType("org.springframework.beans.factory.annotation.Autowired");

        topLevelClass.setSuperClass("BaseDaoImp<" + introspectedTable.getFullyQualifiedTable().getDomainObjectName() + ">");
        topLevelClass.addSuperInterface(interfaces.getType());
        commentGenerator.addModelClassComment(topLevelClass, introspectedTable);

        addMaxIdMethod(topLevelClass);
        addDeleteByPrimaryKeyMethod(topLevelClass);
        addInsertMethod(topLevelClass);
        addUpdateMethod(topLevelClass);
        addSelectByPrimaryKeyMethod(topLevelClass);
        addSelectByBeanMethod(topLevelClass);
        addSqlRepositoryMethod(topLevelClass);

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

    /**
     * 新增
     * @param element
     */
    protected void addInsertMethod(JavaElement element) {
        AbstractDaoMapperMethodGenerator methodGenerator = new InsertBeanMethodGenerator();
        initializeAndExecuteGenerator(methodGenerator);
        if (element instanceof Interface) {
            methodGenerator.addInterfaceElements((Interface)element);
        } else if (element instanceof TopLevelClass){
            methodGenerator.addTopLevelClassElements((TopLevelClass)element);
        }
    }

    /**
     * 取最大id值， 当主键只有一个的时候
     * @param element
     */
    protected void addMaxIdMethod(JavaElement element) {
        AbstractDaoMapperMethodGenerator methodGenerator = new GetMaxIdClientGenerator();
        initializeAndExecuteGenerator(methodGenerator);
        if (element instanceof Interface) {
            methodGenerator.addInterfaceElements((Interface)element);
        } else if (element instanceof TopLevelClass){
            methodGenerator.addTopLevelClassElements((TopLevelClass)element);
        }
    }

    protected void addUpdateMethod(JavaElement element) {
        AbstractDaoMapperMethodGenerator methodGenerator = new UpdateBeanMethodGenerator();
        initializeAndExecuteGenerator(methodGenerator);
        if (element instanceof Interface) {
            methodGenerator.addInterfaceElements((Interface)element);
        } else if (element instanceof TopLevelClass){
            methodGenerator.addTopLevelClassElements((TopLevelClass)element);
        }
    }

    /**
     *
     * @param element
     */
    protected void addSelectByBeanMethod(JavaElement element) {
        AbstractDaoMapperMethodGenerator methodGenerator = new SelectByBeanMethodGenerator();
        initializeAndExecuteGenerator(methodGenerator);
        if (element instanceof Interface) {
            methodGenerator.addInterfaceElements((Interface)element);
        } else if (element instanceof TopLevelClass){
            methodGenerator.addTopLevelClassElements((TopLevelClass)element);
        }
    }

    /**
     *
     * @param element
     */
    protected void addSelectByPrimaryKeyMethod(JavaElement element) {
        AbstractDaoMapperMethodGenerator methodGenerator = new SelectByPrimaryKeyClientGenerator();
        initializeAndExecuteGenerator(methodGenerator);
        if (element instanceof Interface) {
            methodGenerator.addInterfaceElements((Interface)element);
        } else if (element instanceof TopLevelClass){
            methodGenerator.addTopLevelClassElements((TopLevelClass)element);
        }
    }

    /**
     *
     * @param element
     */
    protected void addSqlRepositoryMethod(TopLevelClass element) {
        SqlSessionFactoryMethodGenerator sqlSessionFactoryMethodGenerator = new SqlSessionFactoryMethodGenerator();
        sqlSessionFactoryMethodGenerator.addTopLevelClassElements(element);
    }
}
