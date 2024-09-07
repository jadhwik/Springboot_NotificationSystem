package co.instio.controller;

import co.instio.entity.Details;
import co.instio.dto.DetailsDto;
import co.instio.dto.MessageView;
import co.instio.dto.ResponseModel;
import co.instio.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notify")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping("/all")
    public ResponseModel< List<MessageView>> getAllById(){
        return ResponseModel.of(notificationService.findAll());
    }

    @GetMapping("{id}")
    public ResponseModel< MessageView> getById(@PathVariable Long id){
        return ResponseModel.of(notificationService.findById(id));
    }

    @PostMapping
    public ResponseModel<?> create(@RequestBody  DetailsDto detailsDto){
        notificationService.save(detailsDto);
        return ResponseModel.of(HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseModel<MessageView> update(@PathVariable Long id,@RequestBody DetailsDto detailsDto){
        return ResponseModel.of(notificationService.update(id,detailsDto));

    }

    @DeleteMapping("{id}")
    public ResponseModel<?> deleteById(@PathVariable("id") Long notificationId){
        notificationService.deleteById(notificationId);
        return ResponseModel.of(HttpStatus.OK);

    }




}
