package com.example.produtivoBackend.repository;

import com.example.produtivoBackend.domain.tasks.CategoryTask;
import com.example.produtivoBackend.domain.tasks.StatusTask;
import com.example.produtivoBackend.domain.tasks.Task;
import org.springframework.data.jpa.domain.Specification;

public class SpecificationTask {

    public static Specification<Task> hasCategory(CategoryTask categoryTask){
        return (root, query, cb) ->
            cb.equal(root.get("categoryTask"), categoryTask);
    }

    public static Specification<Task> hasStatus(StatusTask statusTask){
        return (root, query, cb) ->
            cb.equal(root.get("statusTask"), statusTask);
    }

    public static Specification<Task> hasTitle(String title){
        return (root, query, cb) ->
                cb.like(root.get("title"), "%"+title+"%");
    }

}
