package org.mybatis.generator.ext;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.internal.DefaultCommentGenerator;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * http://www.blogjava.net/bolo/archive/2015/03/25/423826.html
 */
public class AdvaCommentGenerator extends DefaultCommentGenerator {

    @Override
    public void addJavaFileComment(CompilationUnit compilationUnit) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        compilationUnit.addFileCommentLine("/*");
        compilationUnit.addFileCommentLine(" * " + compilationUnit.getType().getShortName() + ".java");
        compilationUnit.addFileCommentLine(" * Copyright(C) All rights reserved. ");
        compilationUnit.addFileCommentLine(" * " + sdf.format(new Date()) + " Created");
        compilationUnit.addFileCommentLine(" */");
        super.addJavaFileComment(compilationUnit);
    }

    @Override
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        innerClass.addJavaDocLine("/**");
        innerClass.addJavaDocLine(" * " + introspectedTable.getRemarks()); //introspectedTable.getFullyQualifiedTable().getRemarks
        innerClass.addJavaDocLine(" * @version 0.1" + sdf.format(new Date()));
        innerClass.addJavaDocLine(" */");
        super.addClassComment(innerClass, introspectedTable);
    }

    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        super.addFieldComment(field, introspectedTable, introspectedColumn);
    }
}
