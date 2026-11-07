
package com.scc.model;

public class ActivityLog {

    private int logsId;
    private int userId;
    private String actiontype;
    private String description;
    private String datetime;

    public ActivityLog(int logsId, int userId,
                       String actionType, String description,
                       String datetime) {
        this.logsId = logsId;
        this.userId = userId;
        this.actiontype = actiontype;
        this.description = description;
        this.datetime = datetime;
    }

    public int getLogsId() {
        return logsId;
    }

    public int getUserId() {
        return userId;
    }

    public String getActionType() {
        return actiontype;
    }

    public String getDescription() {
        return description;
    }

    public String getDatetime() {
        return datetime;
    }
}
