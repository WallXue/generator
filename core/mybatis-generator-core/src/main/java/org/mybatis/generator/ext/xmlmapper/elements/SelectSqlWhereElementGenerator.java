package org.mybatis.generator.ext.xmlmapper.elements;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.AbstractXmlElementGenerator;
import org.mybatis.generator.util.GenUtil;

/**
 */
public class SelectSqlWhereElementGenerator extends AbstractXmlElementGenerator {
    @Override
    public void addElements(XmlElement parentElement) {

        XmlElement answer = new XmlElement("sql");

        answer.addAttribute(new Attribute("id", GenUtil.genSqlWhereExpression(introspectedTable)));

        for (IntrospectedColumn introspectedColumn : introspectedTable
                .getAllColumns()) {

            StringBuilder sb = new StringBuilder();

            XmlElement ifNotNullElement = new XmlElement("if");
            sb.setLength(0);
            sb.append(introspectedColumn.getJavaProperty());
            sb.append(" != null");
            ifNotNullElement.addAttribute(new Attribute(
                    "test", sb.toString()));

            sb.setLength(0);
            sb.append(" and ");
            sb.append(MyBatis3FormattingUtilities
                    .getEscapedColumnName(introspectedColumn));
            sb.append(" = ");
            sb.append(MyBatis3FormattingUtilities
                    .getParameterClause(introspectedColumn));

            ifNotNullElement.addElement(new TextElement(sb.toString()));

            answer.addElement(ifNotNullElement);
        }

        parentElement.addElement(answer);
    }
}
