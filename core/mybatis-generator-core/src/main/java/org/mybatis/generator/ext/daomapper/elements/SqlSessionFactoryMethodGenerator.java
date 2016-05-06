package org.mybatis.generator.ext.daomapper.elements;

import org.mybatis.generator.api.dom.java.*;

/**
 * 生成 类似这样的格式
 * @Autowired
 @Qualifier("userSqlSessionFactory")
 public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
 super.sqlSessionFactory = sqlSessionFactory;
 }
 */
public class SqlSessionFactoryMethodGenerator extends AbstractDaoMapperMethodGenerator {

    @Override
    public void addTopLevelClassElements(TopLevelClass topLevelClass) {
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.addAnnotation("@Autowired");
        method.addAnnotation("@Qualifier(\"userSqlSessionFactory\")");
        method.setName("setSqlSessionFactory");

        FullyQualifiedJavaType importedTypes = new FullyQualifiedJavaType("org.apache.ibatis.session.SqlSessionFactory");
        method.addParameter(new Parameter(importedTypes, "sqlSessionFactory"));

        method.addBodyLine(" super.sqlSessionFactory = sqlSessionFactory;");

        topLevelClass.addImportedType(importedTypes);
        topLevelClass.addMethod(method);
    }

    @Override
    public void addInterfaceElements(Interface interfaze) {
        return;
    }
}
