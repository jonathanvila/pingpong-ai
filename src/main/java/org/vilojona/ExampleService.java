package org.vilojona;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import jakarta.annotation.Nullable;


public class ExampleService {

    private final AtomicInteger cacheReference;

    public ExampleService() {
        cacheReference = new AtomicInteger();
        cacheReference.set(createCache());
        System.out.println("Cache created: " + getCache());
        getItems();
        getObject();
    }

    public Integer getCache() {
        Integer cache = cacheReference.get(); // can be null
        return cache.intValue() * 2; // NPE could be thrown
    }

    private void getVar() {
        Integer a = null;
        System.out.println(a.intValue() * 2); // NPE could be thrown
    }
    private void getItems() {
        List<Integer> items = List.of(1, 2, null);
        var b = items.get(3).intValue() * 2; // NPE could be thrown
        System.out.println(b);
    }

    private void getObject() {
        MyObject obj = new MyObject();
        obj.value = null;
        System.out.println(obj.value.intValue() * 2); // NPE could be thrown
    }
    class MyObject {
        @Nullable
        public Integer value;
    }

    @Nullable
    public Integer createCache() {
        return null;
    }
}