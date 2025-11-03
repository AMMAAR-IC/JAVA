// GitChangelog.java
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.revwalk.RevCommit;
import java.io.File;

public class GitChangelog {
    public static void main(String[] args) throws Exception {
        try (Git git = Git.open(new File("."))) {
            Iterable<RevCommit> commits = git.log().setMaxCount(50).call();
            System.out.println("## Changelog (last 50 commits)");
            for (RevCommit c : commits) System.out.println("- " + c.getShortMessage() + " (" + c.getAuthorIdent().getName() + ")");
        }
    }
}
