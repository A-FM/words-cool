package com.wordscool.entity;

import com.wordscool.abstractLayer.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TPermission extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 3670401366397492914L;

    private String code;

    private String description;

    private String url;
}