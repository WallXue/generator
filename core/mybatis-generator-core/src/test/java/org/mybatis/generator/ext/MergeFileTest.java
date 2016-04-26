package org.mybatis.generator.ext;

import com.github.antlrjavaparser.CompilationUnitListener;
import com.github.antlrjavaparser.Java7Lexer;
import com.github.antlrjavaparser.Java7Parser;
import com.github.antlrjavaparser.api.CompilationUnit;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.atn.PredictionMode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Test;

/**
 */
public class MergeFileTest {


    @Test
    public void testMerge() throws Exception {
        String existingFileFullPath = "E:\\javaprj\\webapp\\mybatis\\generator\\core\\mybatis-generator-core\\src\\main\\java\\org\\mybatis\\generator\\api\\ShellRunner.java";
        Java7Lexer lexer = new Java7Lexer(new ANTLRFileStream(existingFileFullPath, "UTF-8"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        Java7Parser parser = new Java7Parser(tokens);
//            CommonTree tree = new CommonTokenStream(lexer.get);
//            int type = ((Integer) (Java7Lexer.class.getDeclaredField(existingFileFullPath).get(null))).intValue();

        long start = System.currentTimeMillis();

        parser.getInterpreter().setPredictionMode(PredictionMode.SLL);
        ParseTree tree = null;

        tree = parser.compilationUnit();
        ParseTreeWalker walker = new ParseTreeWalker();

        // Fills out the compilationUnit object
        CompilationUnitListener listener = new CompilationUnitListener(tokens);
        walker.walk(listener, tree);
        CompilationUnit compilationUnit = listener.getCompilationUnit();

        System.out.println("==========================================================================");

        long end = System.currentTimeMillis();

        System.out.println((end - start) + "ms");

        System.out.println(compilationUnit.toString());
    }

}
