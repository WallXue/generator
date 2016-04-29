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
import org.mybatis.generator.util.MergeUtil;
import sun.nio.cs.StandardCharsets;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

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
            fileEncoding = fileEncoding == null? "UTF-8":fileEncoding;
            return MergeUtil.merge2FileReserve(new ByteArrayInputStream(newFileSource.getBytes(fileEncoding)), new FileInputStream(existingFileFullPath));
        } catch (Throwable ex) {
            ex.printStackTrace();
            throw new ShellException(ex);
        }
    }

    @Override
    public void refreshProject(String project) {
        super.refreshProject(project);
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
