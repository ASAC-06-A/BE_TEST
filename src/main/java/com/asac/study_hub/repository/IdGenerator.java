package com.asac.study_hub.repository;

import java.util.HashMap;

public abstract class IdGenerator<T> {

    protected void reassignId(HashMap<Integer, T> memory) { //추후에 Object를 도메인 클래스로 제한할 것
        //강의 삭제후 HashMap id 재정렬
        HashMap<Integer, T> tempList = new HashMap<>();
        Integer id = 1;
        for (T entity : memory.values()) {
            tempList.put(id++, entity);
        }
        memory.clear();
        for (T entity : tempList.values()) {
            memory.put(id++, entity);
        }
    }
}
