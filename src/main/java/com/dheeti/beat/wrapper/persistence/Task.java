package com.dheeti.beat.wrapper.persistence;

import javax.persistence.*;

/**
 * Created by jayram on 19/3/15.
 */
@Entity
@Table(name="tasks")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(
        name="tasktype",
        discriminatorType=DiscriminatorType.STRING
)
@DiscriminatorValue(value="TASK")
public class Task {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "taskid")
    private Integer taskId;

    private String description;

    private String tasktype;

    public Task(){

    }
    public Task(Integer taskid,String taskname,String tasktype, String command, String description) {
        this.taskId = taskid;
        this.description = description;
        this.tasktype = tasktype;
        this.command = command;
        this.taskname = taskname;
    }

    private String command;

    private String parameters;

    public String getTaskname() {
        return taskname;
    }

    public void setTaskname(String taskname) {
        this.taskname = taskname;
    }

    private String taskname;

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTasktype() {
        return tasktype;
    }

    public void setTasktype(String tasktype) {
        this.tasktype = tasktype;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }
}
