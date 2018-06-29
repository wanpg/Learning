package com.wanpg;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;

/**
 * 利用文件锁来实现进程间的数据读写同步问题
 */
public final class ProcessLock {

    /**
     * 进程间文件锁指向的文件名
     */
    private static final String LOCK_FILE_NAME = "process_lock";

    public static ProcessLock lock(File lockFile) throws IOException {
        return lock(lockFile, false);
    }

    public static ProcessLock lock(File lockFile, boolean append) throws IOException {
        // 获取进程锁
        File parentFile = lockFile.getParentFile();
        // 创建文件夹
        while (!parentFile.exists()) {
            if (parentFile.mkdirs()) {
                break;
            }
        }
        // 创建文件
        while (!lockFile.exists()) {
            if (lockFile.createNewFile()) {
                break;
            }
        }
        // 获取文件锁
        ProcessLock processLock = new ProcessLock();
        boolean lockSuccess = false;
        try {
            processLock.fileChannel = new FileOutputStream(lockFile, append).getChannel();
            while (processLock.fileLock == null) {
                try {
                    processLock.fileLock = processLock.fileChannel.tryLock();
                } catch (OverlappingFileLockException ignored) {
                    // 同一个虚拟机，也就是同一个进程内重复获取文件锁会抛此异常
                    // catch此异常并做while循环，也可以试做线程之间同步
                }
            }
            lockSuccess = true;
            return processLock;
        } finally {
            if (!lockSuccess) {
                processLock.release();
            }
        }
    }


    public FileChannel fileChannel;
    public FileLock fileLock;

    private ProcessLock() {
    }

    public void release() throws IOException {
        if (fileLock != null) {
            fileLock.release();
        }
        if (fileChannel != null) {
            fileChannel.close();
        }
    }
}
