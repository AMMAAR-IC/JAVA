import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.revwalk.RevCommit;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class GitCommitAnalyzer {
    public static void main(String[] args) throws Exception {
        String repoPath = "/path/to/your/repo"; // change this
        Git git = Git.open(new File(repoPath));

        Map<String, Integer> authorCommits = new HashMap<>();
        Iterable<RevCommit> commits = git.log().all().call();

        int totalCommits = 0;
        for (RevCommit commit : commits) {
            totalCommits++;
            String author = commit.getAuthorIdent().getName();
            authorCommits.put(author, authorCommits.getOrDefault(author, 0) + 1);
        }

        System.out.println("Total commits: " + totalCommits);
        for (var entry : authorCommits.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue() + " commits");
        }
    }
}
