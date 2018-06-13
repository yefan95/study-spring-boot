package com.yefan.study.spring.elastic.repository;

import com.yefan.study.spring.elastic.bean.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * Async query results
 * <p>
 * This means the method will return immediately upon invocation and the actual query execution
 * will occur in a task that has been submitted to a Spring TaskExecutor.
 */
public interface UserRepository extends ElasticsearchRepository<User, Long> {
    @Async
    Future<User> findByUserName(String userName);

    @Async
    CompletableFuture<List<User>> findBySex(int sex);

    @Async
    ListenableFuture<List<User>> findByAgeBetween(int start, int end);
}
