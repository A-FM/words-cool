package com.wordscool.entity;

import com.wordscool.abstractLayer.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
public class TUser extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 144466865824582546L;

    private String username;

    /**
     * 密码，不为空
     */
    private String password;

    /**
     * 邮箱并且是登录账号，不为空，唯一
     */
    private String email;

    /**
     * 账号未过期
     */
    private Boolean accountNonExpired;

    /**
     * 凭证未过期
     */
    private Boolean credentialsNonExpired;

    /**
     * 账号未锁定
     */
    private Boolean accountNonLocked;
    /**
     * 账号未锁定
     */
    private Boolean enabled;
}