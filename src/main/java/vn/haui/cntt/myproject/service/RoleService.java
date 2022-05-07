package vn.haui.cntt.myproject.service;

import vn.haui.cntt.myproject.entity.Role;
import vn.haui.cntt.myproject.enums.RoleEnum;

import java.util.List;

public interface RoleService {
    void create(Role roleEntity);

    Role findByUserId(Long id);

    Role findByName(RoleEnum name);

    List<Role> findAll();
}
