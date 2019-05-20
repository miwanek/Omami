package olaf.cafe.omami.interfaces;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {

    private String username;
    private Integer roomId;
    private String data;
    private LocalDateTime time;
}
