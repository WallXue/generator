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

import com.github.antlrjavaparser.JavaParser;
import com.github.antlrjavaparser.api.CompilationUnit;
import com.github.antlrjavaparser.api.ImportDeclaration;
import com.github.antlrjavaparser.api.body.*;
import com.github.antlrjavaparser.api.type.ClassOrInterfaceType;
import com.github.antlrjavaparser.api.type.ReferenceType;
import com.github.antlrjavaparser.api.type.Type;
import com.github.antlrjavaparser.api.type.VoidType;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 */
public class MergeUtil {

    static Set<String> primitiveTypeName = new HashSet<String>();
    static {
        primitiveTypeName.addAll(Arrays.asList(new String[] {
                "Long", "Integer", "Byte", "Short", "Date", "String"
        }));
    }
    public static boolean isCoinsFilterType(Type type) {
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

    public static  boolean containsCollectionOrClass(FieldDeclaration fieldDeclaration) {
        return isCoinsFilterType(fieldDeclaration.getType());
    }

    public static boolean containsCollectionOrClass(MethodDeclaration methodDeclaration) {
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

    public static String merge2FileReserve(InputStream srcStream, InputStream orginStream) throws IOException {
        CompilationUnit compilationUnit = JavaParser.parse(orginStream);

        List<ImportDeclaration> orignImports = compilationUnit.getImports();
        List<TypeDeclaration> list = compilationUnit.getTypes();
        List<BodyDeclaration> listDiff2Reserve = new ArrayList<BodyDeclaration>();

        for (TypeDeclaration typeDeclaration: list) {
            for (BodyDeclaration bodyDeclaration: typeDeclaration.getMembers()) {
                JavadocComment javadocComment = bodyDeclaration.getJavaDoc();
                System.out.println(javadocComment);

                if (bodyDeclaration instanceof FieldDeclaration) {
                    FieldDeclaration fieldDeclaration = (FieldDeclaration)bodyDeclaration;
                    if (containsCollectionOrClass(fieldDeclaration)) {
                        listDiff2Reserve.add(fieldDeclaration);
                    }
                }
                if (bodyDeclaration instanceof MethodDeclaration) {
                    MethodDeclaration methodDeclaration = (MethodDeclaration) bodyDeclaration;
                    if (containsCollectionOrClass(methodDeclaration)) {
                        listDiff2Reserve.add(methodDeclaration);
                    }
                }
            }
        }

        CompilationUnit compilationUnitSrc = JavaParser.parse(srcStream);
        List<ImportDeclaration> srcImports = compilationUnitSrc.getImports();
        for (ImportDeclaration importDeclaration: orignImports) {
            if (!srcImports.contains(importDeclaration))
                srcImports.add(importDeclaration);
        }

        List<TypeDeclaration> listSrc = compilationUnitSrc.getTypes();
        List<BodyDeclaration> listBodySrc = listSrc.get(0).getMembers();
        listBodySrc.addAll(listDiff2Reserve);
        Collections.sort(listBodySrc, new Comparator<BodyDeclaration>() {
            @Override
            public int compare(BodyDeclaration o1, BodyDeclaration o2) {
                if (o1 instanceof MethodDeclaration && o2 instanceof FieldDeclaration)
                    return 1;
                else if (o1 instanceof FieldDeclaration && o2 instanceof MethodDeclaration)
                    return -1;
                return 0;
            }
        });
        listSrc.get(0).setMembers(listBodySrc);

        System.out.println(compilationUnitSrc.toString());
        return compilationUnitSrc.toString();
    }
}
