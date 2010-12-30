package uk.org.sappho.jira.soap.getparent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.ofbiz.core.entity.GenericEntityException;

import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.IssueManager;
import com.atlassian.jira.issue.MutableIssue;
import com.atlassian.jira.issue.changehistory.ChangeHistoryManager;
import com.atlassian.jira.issue.history.ChangeItemBean;
import com.atlassian.jira.rpc.auth.TokenManager;
import com.atlassian.jira.rpc.exception.RemoteException;

public class GetParentImpl implements GetParent {

    private final TokenManager tokenManager;
    private final IssueManager issueManager;
    private final ChangeHistoryManager changeHistoryManager;

    public GetParentImpl(TokenManager tokenManager, IssueManager issueManager, ChangeHistoryManager changeHistoryManager) {

        this.tokenManager = tokenManager;
        this.issueManager = issueManager;
        this.changeHistoryManager = changeHistoryManager;
    }

    public String login(String username, String password) throws RemoteException {

        return tokenManager.login(username, password);
    }

    public String getParentKey(String token, String issueKey) throws RemoteException {

        tokenManager.retrieveUserNoPermissionCheck(token);
        MutableIssue issue = issueManager.getIssueObject(issueKey);
        Issue parentIssue = issue.getParentObject();
        return parentIssue != null ? parentIssue.getKey() : null;
    }

    public String getMovedIssueKey(String token, String issueKey) throws RemoteException {

        tokenManager.retrieveUserNoPermissionCheck(token);
        Issue issue = null;
        try {
            issue = changeHistoryManager.findMovedIssue(issueKey);
        } catch (GenericEntityException e) {
        }
        return issue != null ? issue.getKey() : null;
    }

    public List<Object[]> getFieldHistory(String token, String issueKey, String fieldName) throws RemoteException {

        List<Object[]> history = new ArrayList<Object[]>();
        tokenManager.retrieveUserNoPermissionCheck(token);
        MutableIssue issue = issueManager.getIssueObject(issueKey);
        List<ChangeItemBean> changes = changeHistoryManager.getChangeItemsForField(issue, fieldName);
        Calendar calendar = Calendar.getInstance();
        for (ChangeItemBean change : changes) {
            calendar.setTimeInMillis(change.getCreated().getTime());
            history.add(new Object[] { calendar.getTime(), change.getFromString(), change.getToString() });
        }
        return history;
    }
}
