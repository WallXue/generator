package org.mybatis.generator.util;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.config.Context;

/**
 */
public class GenUtil {

    /**
     * 返回 EntityBean的类名类型
     * @param context
     * @param introspectedTable
     * @return
     */
    public static FullyQualifiedJavaType getEntityType(Context context, IntrospectedTable introspectedTable) {
        FullyQualifiedJavaType entityType = new FullyQualifiedJavaType(context.getJavaModelGeneratorConfiguration().getTargetPackage() + "." + introspectedTable.getFullyQualifiedTable().getDomainObjectName());
        return entityType;
    }

    /**
     *
     * @param introspectedTable
     * @return
     */
    public static String getMaxIdExpression(IntrospectedTable introspectedTable) {
        return "getMax" + introspectedTable.getFullyQualifiedTable().getDomainObjectName() + "Id";
    }

    /**
     *
     * @return
     */
    public static String genSqlWhereExpression(IntrospectedTable introspectedTable) {
        return "select_where_sql";
    }

    /**
     *
     * @param introspectedTable
     * @return
     */
    public static String getSelectByBeanExpression(IntrospectedTable introspectedTable) {
        return "select" + introspectedTable.getFullyQualifiedTable().getDomainObjectName() + "List";
    }
}
