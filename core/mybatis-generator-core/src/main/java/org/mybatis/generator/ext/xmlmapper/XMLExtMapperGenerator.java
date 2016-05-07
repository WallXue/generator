package org.mybatis.generator.ext.xmlmapper;

import org.mybatis.generator.api.FullyQualifiedTable;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.AbstractXmlGenerator;
import org.mybatis.generator.codegen.XmlConstants;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.*;
import org.mybatis.generator.ext.xmlmapper.elements.SelectByBeanElementGenerator;
import org.mybatis.generator.ext.xmlmapper.elements.SelectSqlWhereElementGenerator;

import static org.mybatis.generator.internal.util.messages.Messages.getString;

/**
 */
public class XMLExtMapperGenerator extends AbstractXmlGenerator {

    public XMLExtMapperGenerator() {
        super();
    }

    protected XmlElement getSqlMapElement() {
        FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
        progressCallback.startTask(getString(
                "Progress.12", table.toString())); //$NON-NLS-1$
        XmlElement answer = new XmlElement("mapper"); //$NON-NLS-1$
        String namespace = introspectedTable.getMyBatis3SqlMapNamespace();
        answer.addAttribute(new Attribute("namespace", //$NON-NLS-1$
                namespace));

        context.getCommentGenerator().addRootComment(answer);

        addResultMapWithoutBLOBsElement(answer);
        addResultMapWithBLOBsElement(answer);
        addSelectSqlWhereElement(answer);
        addBaseColumnListElement(answer);
//        addBlobColumnListElement(answer);
        addSelectByPrimaryKeyElement(answer);
        addSelectByBeanElement(answer);
        addDeleteByPrimaryKeyElement(answer);
        addInsertElement(answer);
        addInsertSelectiveElement(answer);
        addUpdateByPrimaryKeySelectiveElement(answer);
//        addUpdateByPrimaryKeyWithBLOBsElement(answer);
//        addUpdateByPrimaryKeyWithoutBLOBsElement(answer);

        return answer;
    }

    protected void addResultMapWithoutBLOBsElement(XmlElement parentElement) {
        if (!introspectedTable.getRules().generateBaseResultMap())
            return;

        AbstractXmlElementGenerator elementGenerator = new ResultMapWithoutBLOBsElementGenerator(false);
        initializeAndExecuteGenerator(elementGenerator, parentElement);
    }

    protected void addResultMapWithBLOBsElement(XmlElement parentElement) {
        if (!introspectedTable.getRules().generateResultMapWithBLOBs())
            return;

        AbstractXmlElementGenerator elementGenerator = new ResultMapWithBLOBsElementGenerator();
        initializeAndExecuteGenerator(elementGenerator, parentElement);
    }

    protected void addBaseColumnListElement(XmlElement parentElement) {
        if (!introspectedTable.getRules().generateBaseColumnList())
            return;

        AbstractXmlElementGenerator elementGenerator = new BaseColumnListElementGenerator();
        initializeAndExecuteGenerator(elementGenerator, parentElement);
    }

    /**
     * 生成 <sql id="select_where_sql">  <if test="uid != null and uid != ''"> 类似的格式
     * @param parentElement
     */
    protected void addSelectSqlWhereElement(XmlElement parentElement) {
        AbstractXmlElementGenerator elementGenerator = new SelectSqlWhereElementGenerator();
        initializeAndExecuteGenerator(elementGenerator, parentElement);
    }

    /**
     * 生成 <sql id="select_where_sql">  <if test="uid != null and uid != ''"> 类似的格式
     * @param parentElement
     */
    protected void addSelectByBeanElement(XmlElement parentElement) {
        AbstractXmlElementGenerator elementGenerator = new SelectByBeanElementGenerator();
        initializeAndExecuteGenerator(elementGenerator, parentElement);
    }

    protected void addBlobColumnListElement(XmlElement parentElement) {
        if (!introspectedTable.getRules().generateBlobColumnList())
            return;

        AbstractXmlElementGenerator elementGenerator = new BlobColumnListElementGenerator();
        initializeAndExecuteGenerator(elementGenerator, parentElement);
    }

    protected void addSelectByPrimaryKeyElement(XmlElement parentElement) {
        if (introspectedTable.getRules().generateSelectByPrimaryKey())
            return;

        AbstractXmlElementGenerator elementGenerator = new SelectByPrimaryKeyElementGenerator();
        initializeAndExecuteGenerator(elementGenerator, parentElement);
    }

    protected void addDeleteByPrimaryKeyElement(XmlElement parentElement) {
        if (introspectedTable.getRules().generateDeleteByPrimaryKey()) {
            AbstractXmlElementGenerator elementGenerator = new DeleteByPrimaryKeyElementGenerator(false);
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    protected void addInsertElement(XmlElement parentElement) {
        if (introspectedTable.getRules().generateInsert()) {
            AbstractXmlElementGenerator elementGenerator = new InsertElementGenerator(false);
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    protected void addInsertSelectiveElement(XmlElement parentElement) {
        if (introspectedTable.getRules().generateInsertSelective()) {
            AbstractXmlElementGenerator elementGenerator = new InsertSelectiveElementGenerator();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    protected void addUpdateByPrimaryKeySelectiveElement(
            XmlElement parentElement) {
        if (introspectedTable.getRules().generateUpdateByPrimaryKeySelective()) {
            AbstractXmlElementGenerator elementGenerator = new UpdateByPrimaryKeySelectiveElementGenerator();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    protected void addUpdateByPrimaryKeyWithBLOBsElement(
            XmlElement parentElement) {
        if (introspectedTable.getRules().generateUpdateByPrimaryKeyWithBLOBs())
            return;

        AbstractXmlElementGenerator elementGenerator = new UpdateByPrimaryKeyWithBLOBsElementGenerator();
        initializeAndExecuteGenerator(elementGenerator, parentElement);
    }

    protected void addUpdateByPrimaryKeyWithoutBLOBsElement(
            XmlElement parentElement) {
        if (introspectedTable.getRules().generateUpdateByPrimaryKeyWithoutBLOBs()) {
            AbstractXmlElementGenerator elementGenerator = new UpdateByPrimaryKeyWithoutBLOBsElementGenerator(false);
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    protected void initializeAndExecuteGenerator(
            AbstractXmlElementGenerator elementGenerator,
            XmlElement parentElement) {
        elementGenerator.setContext(context);
        elementGenerator.setIntrospectedTable(introspectedTable);
        elementGenerator.setProgressCallback(progressCallback);
        elementGenerator.setWarnings(warnings);
        elementGenerator.addElements(parentElement);
    }

    @Override
    public Document getDocument() {
        Document document = new Document(
                XmlConstants.MYBATIS3_MAPPER_PUBLIC_ID,
                XmlConstants.MYBATIS3_MAPPER_SYSTEM_ID);
        document.setRootElement(getSqlMapElement());

        if (!context.getPlugins().sqlMapDocumentGenerated(document,
                introspectedTable)) {
            document = null;
        }

        return document;
    }
}

