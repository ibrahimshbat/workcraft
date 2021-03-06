package org.workcraft.tasks;

public interface ProgressMonitor<T> {
    boolean isCancelRequested();
    void progressUpdate(double completion);
    void stdout(byte[] data);
    void stderr(byte[] data);
    void finished(Result<? extends T> result);
    Result<? extends T> waitResult();
}
