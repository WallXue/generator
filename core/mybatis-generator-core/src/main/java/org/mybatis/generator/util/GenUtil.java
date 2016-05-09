/**
 *    Copyright 2006-2015 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.mybatis.generator.util;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.config.Context;

/**
 */
public class GenUtil {

    public static final String MAX_ID_METHODNAME = "maxIdMethodName";
    public static final String SELECT_BY_BEAN_METHODNAME = "selectByBeanMethodName";
    public static final String SELECT_BY_PRIMARYKEY_METHODNAME = "selectByPKMethodName";
    public static final String INSERT_BEAN_METHODNAME = "insertBeanMethodName";
    public static final String DELETE_BY_PRIMARYKEY_METHODNAME = "deleteByPKMethodName";
    public static final String SELECT_COUNT_METHODNAME = "selectCountMethodName";
    public static final String SELECT_BY_BEAN_PAGE_METHODNAME = "selectByBeanPageMethodName";
    public static final String UPDATE_BY_BEAN_METHODNAME = "updateByBeanMethodName";

    public static final String XML_MAX_ID_METHODNAME = "XML_maxIdMethodName";
    public static final String XML_SELECT_BY_BEAN_METHODNAME = "XML_selectByBeanMethodName";
    public static final String XML_SELECT_BY_PRIMARYKEY_METHODNAME = "XML_selectByPKMethodName";
    public static final String XML_INSERT_BEAN_METHODNAME = "XML_insertBeanMethodName";
    public static final String XML_DELETE_BY_PRIMARYKEY_METHODNAME = "XML_deleteByPKMethodName";
    public static final String XML_SELECT_COUNT_METHODNAME = "XML_selectCountMethodName";
    public static final String XML_SELECT_BY_BEAN_PAGE_METHODNAME = "XML_selectByBeanPageMethodName";
    public static final String XML_UPDATE_BY_BEAN_METHODNAME = "XML_updateByBeanMethodName";

    public static final String GEN_COUNT_BY_PAGE = "genCountByPage";
    /**
     * @param context  上下文
     * @param introspectedTable 表结构
     * @return EntityBean的类名类型
     */
    public static FullyQualifiedJavaType getEntityType(Context context, IntrospectedTable introspectedTable) {
        FullyQualifiedJavaType entityType = new FullyQualifiedJavaType(context.getJavaModelGeneratorConfiguration().getTargetPackage() + "." + introspectedTable.getFullyQualifiedTable().getDomainObjectName());
        return entityType;
    }

    /**
     * @return 取最大值的method name
     */
    public static String getMaxIdMethodName(IntrospectedTable introspectedTable, boolean isXml) {
        String config = introspectedTable.getTableConfiguration().getProperty(isXml? XML_MAX_ID_METHODNAME : MAX_ID_METHODNAME);
        if (config != null)
            return config;

        return "getMax" + introspectedTable.getFullyQualifiedTable().getDomainObjectName() + "Id";
    }

    public static String getMaxIdMethodName(IntrospectedTable introspectedTable) {
        return getMaxIdMethodName(introspectedTable, false);
    }

    /**
     * @return 根据bean条件取 的method name
     */
    public static String getSelectByBeanMethodName(IntrospectedTable introspectedTable, boolean isXml) {
        String config = introspectedTable.getTableConfiguration().getProperty(isXml? XML_SELECT_BY_BEAN_METHODNAME: SELECT_BY_BEAN_METHODNAME);
        if (config != null)
            return config;

        return "select" + introspectedTable.getFullyQualifiedTable().getDomainObjectName() + "List";
    }

    public static String getSelectByBeanMethodName(IntrospectedTable introspectedTable) {
        return getSelectByBeanMethodName(introspectedTable, false);
    }

    public static String getSelectByPrimaryKeyMethodName(IntrospectedTable introspectedTable, boolean isXml) {
        String config = introspectedTable.getTableConfiguration().getProperty(isXml? XML_SELECT_BY_PRIMARYKEY_METHODNAME:SELECT_BY_PRIMARYKEY_METHODNAME);
        if (config != null)
            return config;

        return "select" + introspectedTable.getFullyQualifiedTable().getDomainObjectName();
    }

    public static String getSelectByPrimaryKeyMethodName(IntrospectedTable introspectedTable) {
        return getSelectByPrimaryKeyMethodName(introspectedTable, false);
    }

    /**
     * @return 分页方法method Name
     */
    public static String getSelectByBeanPageMethodName(IntrospectedTable introspectedTable, boolean isXml) {
        String config = introspectedTable.getTableConfiguration().getProperty(isXml? XML_SELECT_BY_BEAN_PAGE_METHODNAME: SELECT_BY_BEAN_PAGE_METHODNAME);
        if (config != null)
            return config;

        return "select" + introspectedTable.getFullyQualifiedTable().getDomainObjectName() + "ByPage";
    }

    public static String getSelectByBeanPageMethodName(IntrospectedTable introspectedTable) {
        return getSelectByBeanPageMethodName(introspectedTable, false);
    }

    /**
     * @return 查询数量
     */
    public static String getSelectCountMethodName(IntrospectedTable introspectedTable, boolean isXml) {
        String config = introspectedTable.getTableConfiguration().getProperty(isXml? XML_SELECT_COUNT_METHODNAME: SELECT_COUNT_METHODNAME);
        if (config != null)
            return config;

        return "select" + introspectedTable.getFullyQualifiedTable().getDomainObjectName() + "Count";
    }

    /**
     * @return 查询数量
     */
    public static String getSelectCountMethodName(IntrospectedTable introspectedTable) {
        return getSelectCountMethodName(introspectedTable, false);
    }

    /**
     * @return 插入语句method Name
     */
    public static String getInsertBeanMethodName(IntrospectedTable introspectedTable) {
        String config = introspectedTable.getTableConfiguration().getProperty(INSERT_BEAN_METHODNAME);
        if (config != null)
            return config;

        return "add" + introspectedTable.getFullyQualifiedTable().getDomainObjectName();
    }

    /**
     * @return 根据主键删除的method Name
     */
    public static String getDeleteByPrimaryKeyMethodName(IntrospectedTable introspectedTable) {
        String config = introspectedTable.getTableConfiguration().getProperty(DELETE_BY_PRIMARYKEY_METHODNAME);
        if (config != null)
            return config;

        return "delete" + introspectedTable.getFullyQualifiedTable().getDomainObjectName() + "ById";
    }

    /**
     * @return 更新语句methodName
     */
    public static String getUpdateByBeanMethodName(IntrospectedTable introspectedTable) {
        String config = introspectedTable.getTableConfiguration().getProperty(UPDATE_BY_BEAN_METHODNAME);
        if (config != null)
            return config;

        return "update" + introspectedTable.getFullyQualifiedTable().getDomainObjectName();
    }

    /**
     * @return xml文件里面的查询sql的id名字
     */
    public static String genSqlWhereExpression(IntrospectedTable introspectedTable) {
        return "select_where_sql";
    }
}
