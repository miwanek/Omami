package olaf.cafe.omami.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoomRepository extends JpaRepository<UserRoom, UserRoomId> {
}
