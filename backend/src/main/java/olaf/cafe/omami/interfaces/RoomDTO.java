package olaf.cafe.omami.interfaces;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
public class RoomDTO {
    private Integer id;
    private String name;
}
