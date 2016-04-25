package org.mybatis.generator.ext.api;

import com.github.antlrjavaparser.Java7Lexer;
import com.github.antlrjavaparser.Java7Parser;
import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.mybatis.generator.api.ShellCallback;
import org.mybatis.generator.exception.ShellException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;

/**
 * Created by silveringsea
 */
public class AdvaMergeShellCallback extends DefaultShellCallback {
    /**
     * Instantiates a new default shell callback.
     *
     * @param overwrite the overwrite
     */
    public AdvaMergeShellCallback(boolean overwrite) {
        super(true);
    }

    /**
     * 优先以原来的版本为准， 相同的地方不合并
     * @param newFileSource
     * @param existingFileFullPath
     * @param javadocTags
     * @param fileEncoding
     * @return
     * @throws ShellException
     */
    @Override
    public String mergeJavaFile(String newFileSource, String existingFileFullPath, String[] javadocTags, String fileEncoding) throws ShellException {
        try {
            Java7Lexer lexer = new Java7Lexer(new org.antlr.v4.runtime.ANTLRFileStream(existingFileFullPath, "UTF-8"));
//            Java7Parser parser = new Java7Parser(new CommonTokenStream(lexer.token));
//            CommonTree tree = new CommonTokenStream(lexer.get);
//            int type = ((Integer) (Java7Lexer.class.getDeclaredField(existingFileFullPath).get(null))).intValue();

        } catch (Throwable ex) {

        }
        return "";
    }

    @Override
    public void refreshProject(String project) {

    }

    @Override
    public boolean isMergeSupported() {
        return true;
    }

    @Override
    public boolean isOverwriteEnabled() {
        return false;
    }
}
