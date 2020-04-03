package io.rushb.messageapp.dao;

import io.rushb.messageapp.entity.ConnectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author <a href="mailto:flamingodev@outlook.com">FLAMINGO</a>
 * @since 2020/4/3 17:11
 */
public interface ConnectionDao extends JpaRepository<ConnectionEntity, Integer> {
}
