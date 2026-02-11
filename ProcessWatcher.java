public class ProcessWatcher {
    public static void main(String[] args) {
        ProcessHandle.allProcesses()
                .filter(p -> p.info().command().isPresent())
                .forEach(p -> {
                    String cmd = p.info().command().get();
                    if(cmd.contains("java"))
                        System.out.println("Java Process PID: " + p.pid());
                });
    }
}
