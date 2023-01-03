package net.ddns.cloudtecnologia.pessoas.model.repository;

import net.ddns.cloudtecnologia.pessoas.model.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
}
