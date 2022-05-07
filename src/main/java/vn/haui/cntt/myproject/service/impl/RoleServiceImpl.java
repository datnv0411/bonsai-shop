package vn.haui.cntt.myproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.haui.cntt.myproject.entity.Role;
import vn.haui.cntt.myproject.repository.RoleRepository;
import vn.haui.cntt.myproject.service.RoleService;

@Service
@Transactional
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    @Override
    public void create(Role roleEntity) {
        roleRepository.save(roleEntity);
    }
}
