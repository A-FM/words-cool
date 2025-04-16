package com.wordscool.entity;

import com.wordscool.abstractLayer.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
public class TRole extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 3434894790148535074L;

    private String roleName;

    private String description;
}