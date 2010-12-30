package uk.org.sappho.jira.soap.getparent;

import java.util.List;

import com.atlassian.jira.rpc.exception.RemoteException;

public interface GetParent {

    public String login(String username, String password) throws RemoteException;

    public String getParentKey(String token, String issueKey) throws RemoteException;

    public String getMovedIssueKey(String token, String issueKey) throws RemoteException;

    public List<Object[]> getFieldHistory(String token, String issueKey, String fieldName) throws RemoteException;
}
