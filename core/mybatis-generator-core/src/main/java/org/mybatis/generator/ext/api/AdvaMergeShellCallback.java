package org.mybatis.generator.ext.api;

import org.mybatis.generator.exception.ShellException;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.mybatis.generator.util.MergeUtil;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;

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
            if (existingFileFullPath.contains(File.separator + "dao" + File.separator )) {
                return newFileSource;
            }
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
