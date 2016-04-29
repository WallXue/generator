/*
 * file comment: SysRoleMapper.java
 * Copyright(C) All rights reserved. 
 * 2016-04-29 Created
 */
package com.wins.shop.dao;

import com.wins.shop.entity.admin.SysRole;

public interface SysRoleMapper {
    int deleteByPrimaryKey(Long rid);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Long rid);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);
}