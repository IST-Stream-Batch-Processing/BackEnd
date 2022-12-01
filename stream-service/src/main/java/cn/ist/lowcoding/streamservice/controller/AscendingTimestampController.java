package cn.ist.lowcoding.streamservice.controller;

import cn.ist.lowcoding.streamservice.service.AscendingTimestampService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AscendingTimestampController {
    @Autowired
    AscendingTimestampService ascendingTimestampService;


}
