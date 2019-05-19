package olaf.cafe.omami.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRoomRepository extends JpaRepository<UserRoom, UserRoomId> {

    @Query(value = "SELECT * FROM omami.user_room r where user_id = ?1", nativeQuery = true)
    List<UserRoom> findRoomsForGivenUser(Integer userId);
}
