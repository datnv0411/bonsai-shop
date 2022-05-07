package vn.haui.cntt.myproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.haui.cntt.myproject.entity.Role;
import vn.haui.cntt.myproject.enums.RoleEnum;
import vn.haui.cntt.myproject.repository.RoleRepository;
import vn.haui.cntt.myproject.service.RoleService;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    @Override
    public void create(Role roleEntity) {
        roleRepository.save(roleEntity);
    }

    @Override
    public Role findByUserId(Long id) {
        return roleRepository.findByUserId(id);
    }

    @Override
    public Role findByName(RoleEnum name) {
        return roleRepository.findByNameAndDeletedFlag(name, false);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAllByDeletedFlag(false);
    }
}
