package uk.org.sappho.jira.rpc.soap.service.getparent;

import com.atlassian.jira.rpc.exception.RemoteException;

public interface GetParent {

    public String login(String username, String password) throws RemoteException;

    public String getParentKey(String token, String issueKey) throws RemoteException;

    public String getMovedIssueKey(String token, String issueKey) throws RemoteException;

    public String getFieldChangeHistory(String token, String issueKey, String[] fieldNames) throws RemoteException;
}
