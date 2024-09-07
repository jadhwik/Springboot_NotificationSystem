package co.instio.service;

import co.instio.dto.DetailsDto;
import co.instio.dto.MessageView;
import co.instio.entity.Details;
import org.aspectj.bridge.Message;

import java.util.List;

public interface NotificationService {

    List<MessageView> findAll();
    MessageView findById(Long id);
    void  save(DetailsDto  detailsDto);
    MessageView update(Long id,DetailsDto detailsDto);
    void deleteById(Long id);


}
