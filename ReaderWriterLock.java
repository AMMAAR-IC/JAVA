public class ReaderWriterLock {
    private int readers = 0;
    private boolean writing = false;

    public synchronized void readLock() throws InterruptedException {
        while (writing) wait();
        readers++;
    }

    public synchronized void readUnlock() {
        readers--;
        if (readers == 0) notifyAll();
    }

    public synchronized void writeLock() throws InterruptedException {
        while (writing || readers > 0) wait();
        writing = true;
    }

    public synchronized void writeUnlock() {
        writing = false;
        notifyAll();
    }
}
