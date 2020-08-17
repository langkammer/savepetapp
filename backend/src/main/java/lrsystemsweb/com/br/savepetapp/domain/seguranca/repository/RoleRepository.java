package lrsystemsweb.com.br.savepetapp.domain.seguranca.repository;

import lrsystemsweb.com.br.savepetapp.domain.seguranca.Role;
import lrsystemsweb.com.br.savepetapp.domain.usuario.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Roles, Long> {
    public Roles findByRole(Role role);
}
