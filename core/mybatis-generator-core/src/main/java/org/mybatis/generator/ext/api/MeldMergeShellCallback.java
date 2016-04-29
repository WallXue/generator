package org.mybatis.generator.ext.api;

import org.mybatis.generator.internal.DefaultShellCallback;

/**
 */
public class MeldMergeShellCallback extends DefaultShellCallback {

    /** 调用外部命令进行合并
     *
     * @param overwrite the overwrite
     */
    public MeldMergeShellCallback(boolean overwrite) {
        super(true);
    }


}
