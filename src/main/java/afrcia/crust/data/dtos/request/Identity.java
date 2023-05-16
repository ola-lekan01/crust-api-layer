package afrcia.crust.data.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Identity {
    @JsonProperty(value = "id_type")
    private String identificationType;

    @JsonProperty(value = "id_no")
    private String IdentificationNo;

    @JsonProperty(value = "id_image")
    private String IdentificationImage;

    @JsonProperty(value = "bvn")
    private String bvn;
}