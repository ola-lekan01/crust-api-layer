package africa.crust.data.dtos.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiErrorResponse {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private ZonedDateTime timeStamp;
    private Integer statusCode;
    private String path;
    private Object data;
    private Boolean isSuccessful;
}
