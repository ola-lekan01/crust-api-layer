package afrcia.crust.data.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateAccountNotificationPreference {
    private String AccountNumber;
    private String NotificationPreference;
}
