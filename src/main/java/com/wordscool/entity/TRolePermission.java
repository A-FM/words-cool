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
public class TRolePermission extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -6615109731686841233L;

    private Integer roleId;

    private Integer permissionId;
}