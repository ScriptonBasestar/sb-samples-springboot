package sample.domain.cache.repository;

import org.springframework.data.repository.CrudRepository;
import sample.domain.cache.model.PasswordResetRequestCache;

import java.util.Optional;

public interface PasswordResetRequestRepository extends CrudRepository<PasswordResetRequestCache, String> {
    Optional<PasswordResetRequestCache> findOneByCode(String code);
}
