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

import java.io.File;

import static org.mybatis.generator.internal.util.messages.Messages.getString;

/**
 */
public class GenUtil {

    public static final String MAX_ID_METHODNAME = "maxIdMethodName";
    public static final String SELECT_BY_BEAN_METHODNAME = "selectByBeanMethodName";
    public static final String SELECT_BY_PRIMARYKEY_METHODNAME = "selectByPKMethodName";
    public static final String INSERT_BEAN_METHODNAME = "insertBeanMethodName";
    public static final String INSERT_BEAN_SELECTIVE_METHODNAME = "insertBeanSelMethodName";
    public static final String DELETE_BY_PRIMARYKEY_METHODNAME = "deleteByPKMethodName";
    public static final String SELECT_COUNT_METHODNAME = "selectCountMethodName";
    public static final String SELECT_BY_BEAN_PAGE_METHODNAME = "selectByBeanPageMethodName";
    public static final String UPDATE_BY_BEAN_METHODNAME = "updateByBeanMethodName";
    public static final String UPDATE_BY_BEANLIST_METHODNAME = "updateByBeanListMethodName";

    public static final String XML_MAX_ID_METHODNAME = "XML_maxIdMethodName";
    public static final String XML_SELECT_BY_BEAN_METHODNAME = "XML_selectByBeanMethodName";
    public static final String XML_SELECT_BY_PRIMARYKEY_METHODNAME = "XML_selectByPKMethodName";
    public static final String XML_INSERT_BEAN_METHODNAME = "XML_insertBeanMethodName";
    public static final String XML_INSERT_BEAN_SELECTIVE_METHODNAME = "XML_insertBeanSelectiveMethodName";
    public static final String XML_DELETE_BY_PRIMARYKEY_METHODNAME = "XML_deleteByPKMethodName";
    public static final String XML_SELECT_COUNT_METHODNAME = "XML_selectCountMethodName";
    public static final String XML_SELECT_BY_BEAN_PAGE_METHODNAME = "XML_selectByBeanPageMethodName";
    public static final String XML_UPDATE_BY_BEAN_METHODNAME = "XML_updateByBeanMethodName";
    public static final String XML_UPDATE_BY_BEANLIST_METHODNAME = "XML_updateByBeanListMethodName";

    public static final String GEN_COUNT_BY_PAGE = "genCountByPage";

    public  static enum ENUM_METHOD_TYPE {
        XML_TYPE,
        DAO_TYPE,
        REDIS_TYPE
    };
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
    public static String getMaxIdMethodName(IntrospectedTable introspectedTable, ENUM_METHOD_TYPE method_type) {
        String propKey = method_type == ENUM_METHOD_TYPE.DAO_TYPE? MAX_ID_METHODNAME:XML_MAX_ID_METHODNAME;
        String config = introspectedTable.getTableConfiguration().getProperty(propKey);
        if (config != null)
            return config;

        return "findMax" + introspectedTable.getFullyQualifiedTable().getDomainObjectName() + "Id";
    }

    /**
     * @return 根据bean条件取 的method name
     */
    public static String getSelectByBeanMethodName(IntrospectedTable introspectedTable, ENUM_METHOD_TYPE method_type) {
        String propKey = method_type == ENUM_METHOD_TYPE.DAO_TYPE? SELECT_BY_BEAN_METHODNAME:XML_SELECT_BY_BEAN_METHODNAME;
        String config = introspectedTable.getTableConfiguration().getProperty(propKey);
        if (config != null)
            return config;

        return "find" + introspectedTable.getFullyQualifiedTable().getDomainObjectName() + "s"; //"select" + introspectedTable.getFullyQualifiedTable().getDomainObjectName() + "List";
    }

    public static String getSelectByPrimaryKeyMethodName(IntrospectedTable introspectedTable, ENUM_METHOD_TYPE method_type) {
        String propKey = method_type == ENUM_METHOD_TYPE.DAO_TYPE? SELECT_BY_PRIMARYKEY_METHODNAME:XML_SELECT_BY_PRIMARYKEY_METHODNAME;
        String config = introspectedTable.getTableConfiguration().getProperty(propKey);
        if (config != null)
            return config;

        return "find" + introspectedTable.getFullyQualifiedTable().getDomainObjectName() + "ById";// "select" + introspectedTable.getFullyQualifiedTable().getDomainObjectName() + "byId";
    }

    /**
     * @return 分页方法method Name
     */
    public static String getSelectByBeanPageMethodName(IntrospectedTable introspectedTable, ENUM_METHOD_TYPE method_type) {
        String propKey = method_type == ENUM_METHOD_TYPE.DAO_TYPE? SELECT_BY_BEAN_PAGE_METHODNAME:XML_SELECT_BY_BEAN_PAGE_METHODNAME;
        String config = introspectedTable.getTableConfiguration().getProperty(propKey);
        if (config != null)
            return config;

        return "select" + introspectedTable.getFullyQualifiedTable().getDomainObjectName() + "ByPage";
    }

    /**
     * @return 查询数量
     */
    public static String getSelectCountMethodName(IntrospectedTable introspectedTable, ENUM_METHOD_TYPE method_type) {
        String propKey = method_type == ENUM_METHOD_TYPE.DAO_TYPE? SELECT_COUNT_METHODNAME:XML_SELECT_COUNT_METHODNAME;
        String config = introspectedTable.getTableConfiguration().getProperty(propKey);
        if (config != null)
            return config;

        return "getTotal" + introspectedTable.getFullyQualifiedTable().getDomainObjectName();//return "select" + introspectedTable.getFullyQualifiedTable().getDomainObjectName() + "Count";
    }

    /**
     * @return 插入语句method Name
     */
    public static String getInsertBeanMethodName(IntrospectedTable introspectedTable, ENUM_METHOD_TYPE method_type) {
        String propKey = method_type == ENUM_METHOD_TYPE.DAO_TYPE? INSERT_BEAN_METHODNAME:XML_INSERT_BEAN_METHODNAME;
        String config = introspectedTable.getTableConfiguration().getProperty(propKey);
        if (config != null)
            return config;

        return "add" + introspectedTable.getFullyQualifiedTable().getDomainObjectName();
    }

    /**
     * @return 插入语句method Name
     */
    public static String getInsertBeanSelectiveMethodName(IntrospectedTable introspectedTable, ENUM_METHOD_TYPE method_type) {
        String propKey = method_type == ENUM_METHOD_TYPE.DAO_TYPE? INSERT_BEAN_SELECTIVE_METHODNAME:XML_INSERT_BEAN_SELECTIVE_METHODNAME;
        String config = introspectedTable.getTableConfiguration().getProperty(propKey);
        if (config != null)
            return config;

        return "add" + introspectedTable.getFullyQualifiedTable().getDomainObjectName();
    }

    /**
     * @return 根据主键删除的method Name
     */
    public static String getDeleteByPrimaryKeyMethodName(IntrospectedTable introspectedTable, ENUM_METHOD_TYPE method_type) {
        String propKey = method_type == ENUM_METHOD_TYPE.DAO_TYPE? DELETE_BY_PRIMARYKEY_METHODNAME:XML_DELETE_BY_PRIMARYKEY_METHODNAME;
        String config = introspectedTable.getTableConfiguration().getProperty(propKey);
        if (config != null)
            return config;

        return "delete" + introspectedTable.getFullyQualifiedTable().getDomainObjectName() + "ById";
    }

    /**
     * @return 更新语句methodName
     */
    public static String getUpdateByBeanMethodName(IntrospectedTable introspectedTable, ENUM_METHOD_TYPE method_type) {
        String propKey = method_type == ENUM_METHOD_TYPE.DAO_TYPE? UPDATE_BY_BEAN_METHODNAME:XML_UPDATE_BY_BEAN_METHODNAME;
        String config = introspectedTable.getTableConfiguration().getProperty(propKey);
        if (config != null)
            return config;

        return "update" + introspectedTable.getFullyQualifiedTable().getDomainObjectName();
    }

    /**
     * @return 更新批量语句methodName  默认为update
     */
    public static String getUpdateByBeanListMethodName(IntrospectedTable introspectedTable, ENUM_METHOD_TYPE method_type) {
        String propKey = method_type == ENUM_METHOD_TYPE.DAO_TYPE? UPDATE_BY_BEANLIST_METHODNAME:XML_UPDATE_BY_BEANLIST_METHODNAME;
        String config = introspectedTable.getTableConfiguration().getProperty(propKey);
        if (config != null)
            return config;

        return "updateList" + introspectedTable.getFullyQualifiedTable().getDomainObjectName();
    }

    /**
     * @return xml文件里面的查询sql的id名字
     */
    public static String genSqlWhereExpression(IntrospectedTable introspectedTable) {
        return "select_where_sql";
    }

    public static String getGeneralEntityParamName(IntrospectedTable introspectedTable) {
        String entityName = introspectedTable.getFullyQualifiedTable().getDomainObjectName();
        return StringUtil.uncapitalize(entityName);
    }
    /**
     * Gets the unique file name.
     *
     * @param directory
     *            the directory
     * @param fileName
     *            the file name
     * @return the unique file name
     */
    public static File getUniqueFileName(File directory, String fileName) {
        File answer = null;

        // try up to 1000 times to generate a unique file name
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < 1000; i++) {
            sb.setLength(0);
            sb.append(fileName);
            sb.append('.');
            sb.append(i);

            File testFile = new File(directory, sb.toString());
            if (!testFile.exists()) {
                answer = testFile;
                break;
            }
        }

        if (answer == null) {
            throw new RuntimeException(getString(
                    "RuntimeError.3", directory.getAbsolutePath())); //$NON-NLS-1$
        }

        return answer;
    }
}
