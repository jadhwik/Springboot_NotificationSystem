package co.instio.dto;

import co.instio.enums.Status;
import lombok.Data;
import java.util.Date;

@Data
public class DetailsDto {

    private String subject;
    private String message;
    private Date date;
    private Status status;
}
