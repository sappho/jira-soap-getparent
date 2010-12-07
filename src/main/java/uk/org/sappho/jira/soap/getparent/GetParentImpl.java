package uk.org.sappho.jira.soap.getparent;

import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.IssueManager;
import com.atlassian.jira.issue.MutableIssue;

public class GetParentImpl implements GetParent {

    private final IssueManager issueManager;

    public GetParentImpl(IssueManager issueManager) {

        this.issueManager = issueManager;
    }

    public String getParentKey(String issueKey) {

        MutableIssue issue = issueManager.getIssueObject(issueKey);
        Issue parentIssue = issue.getParentObject();
        return parentIssue != null ? parentIssue.getKey() : null;
    }
}
