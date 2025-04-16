package com.wordscool.abstractLayer;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
@Component
public abstract class BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 70551143735140523L;

    private Integer id;

    private Date createTime;

    private Date updateTime;
}
