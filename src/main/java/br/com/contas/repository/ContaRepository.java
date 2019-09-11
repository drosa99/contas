package br.com.contas.repository;

import br.com.contas.entity.ContaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepository extends JpaRepository<ContaEntity, String> {
    Page<ContaEntity> findAll(Pageable pageable);
}
