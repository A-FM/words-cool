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
public class TUserRole extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -655057515555221097L;
    /**
     * 关联的用户id
     */
    private Integer userId;

    /**
     * 关联的角色id
     */
    private Integer roleId;

    /**
     * 描述
     */
    private String description;
}