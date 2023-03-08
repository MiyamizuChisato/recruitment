package fun.ciallo.aoi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.ciallo.aoi.entity.Chat;
import fun.ciallo.aoi.mapper.ChatMapper;
import fun.ciallo.aoi.service.ChatService;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl extends ServiceImpl<ChatMapper, Chat> implements ChatService {

}
