package cn.bugstack.chatbot.api.domain.zsxq;

import cn.bugstack.chatbot.api.domain.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;

import java.io.IOException;

/*
知识星球 API 接口
 */
public interface IZsxqApi {


    //未回答的信息数据
    UnAnsweredQuestionsAggregates queryUnAnsweredQuestionsTopicId(String groupId, String cookie) throws IOException;
    // topicId 需回答问题的编号 slienced 可见

    boolean answer(String groupId, String cookie, String topicId, String text, boolean slienced) throws IOException;


}
