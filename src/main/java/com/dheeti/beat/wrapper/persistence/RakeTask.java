package com.dheeti.beat.wrapper.persistence;

import javax.persistence.*;

/**
 * Created by jayram on 19/3/15.
 */

@Entity
@Table(name="raketasks")
@PrimaryKeyJoinColumn(name="raketaskid", referencedColumnName = "taskid")
@DiscriminatorValue(value="RAKETASK")
public class RakeTask extends Task{
    private String environment;

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }
}
