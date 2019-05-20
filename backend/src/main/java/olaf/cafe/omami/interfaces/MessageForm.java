package olaf.cafe.omami.interfaces;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MessageForm {
    private Integer userId;
    private Integer roomId;
    private String data;
}
