package olaf.cafe.omami.interfaces;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class MessageForm {
    private Integer userId;
    private Integer roomId;
    private String data;
}
