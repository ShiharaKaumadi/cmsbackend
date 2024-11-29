package lk.greenshadow.greens.util;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class ResponseUtil {
    private String code;
    private String status;
    private String message;
}