package org.mybatis.generator.ext;

import com.github.antlrjavaparser.CompilationUnitListener;
import com.github.antlrjavaparser.Java7Lexer;
import com.github.antlrjavaparser.Java7Parser;
import com.github.antlrjavaparser.JavaParser;
import com.github.antlrjavaparser.api.CompilationUnit;
import com.github.antlrjavaparser.api.body.*;
import com.github.antlrjavaparser.api.type.ClassOrInterfaceType;
import com.github.antlrjavaparser.api.type.ReferenceType;
import com.github.antlrjavaparser.api.type.Type;
import com.github.antlrjavaparser.api.type.VoidType;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.atn.PredictionMode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Test;

import java.io.FileInputStream;
import java.util.*;

/**
 */
public class MergeFileTest {


    @Test
    public void testMerge() throws Exception {
        String existingFileFullPath = "";
        CompilationUnit compilationUnit = JavaParser.parse(new FileInputStream(existingFileFullPath));

        List<TypeDeclaration> list = compilationUnit.getTypes();
        List<BodyDeclaration> listSave = new ArrayList<BodyDeclaration>();

        for (TypeDeclaration typeDeclaration: list) {
            System.out.println(typeDeclaration.getMembers());

            for (BodyDeclaration bodyDeclaration: typeDeclaration.getMembers()) {
                JavadocComment javadocComment = bodyDeclaration.getJavaDoc();
                System.out.println(javadocComment);

                if (bodyDeclaration instanceof FieldDeclaration) {
                    FieldDeclaration fieldDeclaration = (FieldDeclaration)bodyDeclaration;
                    if (containsCollectionOrClass(fieldDeclaration)) {
                        listSave.add(fieldDeclaration);
                    }
                }
                if (bodyDeclaration instanceof MethodDeclaration) {
                    MethodDeclaration methodDeclaration = (MethodDeclaration) bodyDeclaration;
                    if (containsCollectionOrClass(methodDeclaration)) {
                        listSave.add(methodDeclaration);
                    }
                }
            }
        }

        String existingFileFullPath2 = "E:\\javaprj\\webapp\\mybatis\\generator\\core\\mybatis-generator-core\\src\\main\\java\\com\\wins\\shop\\entity\\admin\\AdminFunc2.java";
        CompilationUnit compilationUnit2 = JavaParser.parse(new FileInputStream(existingFileFullPath2));
        List<TypeDeclaration> list2 = compilationUnit2.getTypes();
        List<BodyDeclaration> listBody2 = list2.get(0).getMembers();
        listBody2.addAll(listSave);

        Collections.sort(listBody2, new Comparator<BodyDeclaration>() {
            @Override
            public int compare(BodyDeclaration o1, BodyDeclaration o2) {
                if (o1 instanceof MethodDeclaration && o2 instanceof FieldDeclaration)
                    return -1;
                else if (o1 instanceof FieldDeclaration && o2 instanceof MethodDeclaration)
                    return 1;
                return 0;
            }
        });

        list2.get(0).setMembers(listBody2);

        System.out.println(compilationUnit2.toString());

    }

    static Set<String> primitiveTypeName = new HashSet<String>();
    static {
        primitiveTypeName.addAll(Arrays.asList(new String[] {
                "Long", "Integer", "Byte", "Short", "Date", "String"
        }));
    }
    private boolean isCoinsFilterType(Type type) {
        if (type instanceof ReferenceType) {
            Type innerType = ((ReferenceType) type).getType();
            if (innerType instanceof ClassOrInterfaceType) {
                String name = ((ClassOrInterfaceType) innerType).getName();
                if (name == null)
                    return false;
                if (name.endsWith("List") || name.startsWith("Collection")) {
                    return true;
                }
                if (!primitiveTypeName.contains(name)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean containsCollectionOrClass(FieldDeclaration fieldDeclaration) {
        return isCoinsFilterType(fieldDeclaration.getType());
    }

    private boolean containsCollectionOrClass(MethodDeclaration methodDeclaration) {
        if (methodDeclaration.getName().startsWith("set") &&
                methodDeclaration.getType() instanceof VoidType) {
            for (Parameter parameter: methodDeclaration.getParameters()) {
                if (isCoinsFilterType(parameter.getType()))
                    return true;
            }
        } else if (methodDeclaration.getName().startsWith("get") &&
                isCoinsFilterType(methodDeclaration.getType())) {
            return true;
        }

        return false;
    }

}
