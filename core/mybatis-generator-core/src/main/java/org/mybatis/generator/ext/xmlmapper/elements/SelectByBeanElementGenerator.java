package org.mybatis.generator.ext.xmlmapper.elements;

import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.AbstractXmlElementGenerator;
import org.mybatis.generator.config.PropertyRegistry;
import org.mybatis.generator.util.GenUtil;

/**
 */
public class SelectByBeanElementGenerator extends
        AbstractXmlElementGenerator {

    public SelectByBeanElementGenerator() {
        super();
    }

    @Override
    public void addElements(XmlElement parentElement) {
        if (introspectedTable.getPrimaryKeyColumns().size() != 1)
            return;

        XmlElement answer = new XmlElement("select");
        answer.addAttribute(new Attribute(
                "id", GenUtil.getSelectByBeanExpression(introspectedTable)));
        answer.addAttribute(new Attribute("resultMap", introspectedTable.getBaseResultMapId()));

        String identityColumnType = (introspectedTable.getRules().generatePrimaryKeyClass())?
                introspectedTable.getPrimaryKeyType():
                introspectedTable.getPrimaryKeyColumns().get(0).getFullyQualifiedJavaType() .toString();
        answer.addAttribute(new Attribute("resultType", identityColumnType));
        answer.addAttribute(new Attribute("parameterType", introspectedTable.getBaseRecordType()));

//        context.getCommentGenerator().addComment(answer);
        answer.addElement(new TextElement("select "));
        answer.addElement(getBaseColumnListElement());
        answer.addElement(new TextElement(" from " +
                introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime() +
                " where 1=1 "));

        XmlElement includeSqlWhere = new XmlElement("include"); //$NON-NLS-1$
        includeSqlWhere.addAttribute(new Attribute("refid", GenUtil.genSqlWhereExpression(introspectedTable)));
        answer.addElement(includeSqlWhere);

        String orderByClause = introspectedTable.getTableConfigurationProperty(PropertyRegistry.TABLE_SELECT_ALL_ORDER_BY_CLAUSE);
        if (orderByClause != null) {
            answer.addElement(new TextElement(orderByClause));
        }

        parentElement.addElement(answer);
    }
}