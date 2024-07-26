package skaly.ps43766_asm.service;

import skaly.ps43766_asm.entity.Role;
import skaly.ps43766_asm.repository.RoleDao;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class RoleService extends BaseService<Role, Integer> {

    public RoleService(RoleDao dao) {
        super(dao);
    }

}
