package com.dm.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.dm.pojo.User;
import com.dm.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class UserDataListener extends AnalysisEventListener<User> {

    @Autowired
    private IUserService userService;

    public UserDataListener(IUserService userService) {
        this.userService = userService;
    }

    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 5;
    List<User> userList = new ArrayList<User>();

    @Override
    public void invoke(User data, AnalysisContext context) {
        log.info("解析到一条数据:{}", JSON.toJSONString(data));
        userList.add(data);
        if (userList.size() >= BATCH_COUNT) {
            saveData();
            userList.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        saveData();
        log.info("所有数据解析完成！");
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        log.info("{}条数据，开始存储数据库！", userList.size());
        if (!CollectionUtils.isEmpty(userList)) {
            userService.saveBatch(userList);
        }
        log.info("存储数据库成功！");
    }

}
