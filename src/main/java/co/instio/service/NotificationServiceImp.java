package co.instio.service;

import co.instio.dto.DetailsDto;
import co.instio.dto.MessageView;
import co.instio.entity.Details;
import co.instio.enums.CommonErrorCodeEnum;
import co.instio.exceptions.ServiceException;
import co.instio.mapper.NotificationsMapper;
import co.instio.repo.NotificationRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class NotificationServiceImp implements  NotificationService{
    private final NotificationRepo notificationRepo;
    private final NotificationsMapper notificationsMapper;

    @Override
    public List<MessageView> findAll(){
        List<Details> details=notificationRepo.findAll();
        if(details.isEmpty()){
            throw new ServiceException(CommonErrorCodeEnum.NO_CONTENT);
        }
        List<MessageView> view = details.stream()
                .map(notificationsMapper::toView)
                .collect(Collectors.toList());
        return view;

    }
    @Override
    public MessageView findById(Long id){
        Details details=notificationRepo.findById(id);
        if(details==null){
            throw new ServiceException(CommonErrorCodeEnum.NO_CONTENT);
        }
        MessageView view =notificationsMapper.toView(details);
        return view;

    }

    @Override
    public void save(DetailsDto detailsDto){
        Details details=notificationsMapper.toEntity(detailsDto);
        if(details==null){
            throw new ServiceException(CommonErrorCodeEnum.BAD_REQUEST);
        }
         notificationRepo.save(details);

    }

    @Override
    public MessageView update(Long id , DetailsDto detailsDto){
        Details details=notificationsMapper.toEntity(detailsDto);
        Details details1=notificationRepo.findById(id);
        if(details1==null){
            throw new ServiceException(CommonErrorCodeEnum.NO_CONTENT);
        }
        details1.setMessage(detailsDto.getMessage());
        details1.setSubject(detailsDto.getSubject());
        notificationRepo.update(id,details);
        MessageView view = notificationsMapper.toView(details1);
        return view;

    }

    @Override
    public void deleteById(Long notificationId){
        Details details=notificationRepo.findById(notificationId);
        if(details==null){
            throw new ServiceException(CommonErrorCodeEnum.BAD_REQUEST);
        }
        notificationRepo.deleteById(notificationId);

    }


}
