package com.dheeti.beat.wrapper.persistence;

import javax.persistence.*;

/**
 * Created by jayram on 18/3/15.
 */
@Entity
@Table(name="Configurations")
public class Configuration {

    public Configuration() {
    }

    public Configuration(Integer id,String key, String value) {
        this.id = id;
        this.key = key;
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    Integer id;
    @Column(name="confkey")
    String key;
    @Column(name="confvalue")
    String value;
}
