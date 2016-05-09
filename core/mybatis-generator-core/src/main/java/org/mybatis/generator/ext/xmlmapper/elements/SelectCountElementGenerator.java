package org.mybatis.generator.ext.xmlmapper.elements;

import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.AbstractXmlElementGenerator;
import org.mybatis.generator.util.GenUtil;

/**
 */
public class SelectCountElementGenerator extends
        AbstractXmlElementGenerator {

    public SelectCountElementGenerator() {
        super();
    }

    @Override
    public void addElements(XmlElement parentElement) {
        if (introspectedTable.getPrimaryKeyColumns().size() != 1)
            return;

        XmlElement answer = new XmlElement("select");
        answer.addAttribute(new Attribute(
                "id", GenUtil.getSelectCountMethodName(introspectedTable, true)));
        answer.addAttribute(new Attribute("resultType", "java.lang.Long"));
        answer.addAttribute(new Attribute("parameterType", introspectedTable.getBaseRecordType()));

        answer.addElement(new TextElement("select count(*) as `count`"));
        answer.addElement(new TextElement(" from " +
                introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime() +
                " where 1=1 "));

        XmlElement includeSqlWhere = new XmlElement("include"); //$NON-NLS-1$
        includeSqlWhere.addAttribute(new Attribute("refid", GenUtil.genSqlWhereExpression(introspectedTable)));
        answer.addElement(includeSqlWhere);

        parentElement.addElement(answer);
    }
}
