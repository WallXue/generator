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
package org.mybatis.generator.ext.api;

import org.mybatis.generator.exception.ShellException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;

/**
 * 此方法的前提是机器上装有meld
 */
public class MeldMergeShellCallback extends DefaultShellCallback {

    /** 调用外部命令进行合并
     *
     * @param overwrite the overwrite
     */
    public MeldMergeShellCallback(boolean overwrite) {
        super(true);
    }


    @Override
    public String mergeJavaFile(String newFileSource, String existingFileFullPath, String[] javadocTags, String fileEncoding) throws ShellException {
        try {
            Runtime rt = Runtime.getRuntime();
                    Process proc = rt.exec("meld ");
                    int exitVal = proc.waitFor();
            System.out.println("Process exitValue: " + 1);
            return newFileSource;
        } catch (Exception ex) {
            return null;
        }
    }

    public static boolean exec(String command){
//        CommandLine commandLine = CommandLine.parse(command);
//        DefaultExecutor executor = new DefaultExecutor();
//        int exitValue = DefaultExecutor.INVALID_EXITVALUE;
//        ExecuteWatchdog watchdog = new ExecuteWatchdog(2000);
//        executor.setWatchdog(watchdog);
//        try {
//            exitValue = executor.execute(commandLine);
//        } catch (ExecuteException e) {
//            exitValue = e.getExitValue();
//        } catch (IOException e){
//            System.err.println(e.getMessage());
//        }
//        System.out.println("Exit :" + exitValue);
//        return (exitValue == 0);
        return false;
    }

    @Override
    public File getDirectory(String targetProject, String targetPackage)
            throws ShellException {
        return super.getDirectory(targetProject, targetPackage);
    }

}
