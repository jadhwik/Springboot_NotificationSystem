package co.instio.mapper;

import co.instio.dto.DetailsDto;
import co.instio.entity.Details;
import co.instio.dto.MessageView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface NotificationsMapper {

    @Mapping(target = "notificationId",ignore = true)
    @Mapping(target = "status",constant="PENDING")
    Details toEntity(DetailsDto detailsDto);
    MessageView toView(Details details);
}
