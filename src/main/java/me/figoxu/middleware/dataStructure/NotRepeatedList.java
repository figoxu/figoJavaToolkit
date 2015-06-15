package me.figoxu.middleware.dataStructure;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * User: figo.xu
 * Date: 13-2-6
 * Time: 下午3:39
 *
 */

public class NotRepeatedList<E> extends ArrayList<E> {
    public NotRepeatedList() {
        super();
    }

    public NotRepeatedList(Collection<? extends E> c) {
        for(E e : c) {
            add(e);
        }
    }

    public NotRepeatedList(int initialCapacity) {
        super(initialCapacity);
    }

    public boolean add(E o) {
        if(! contains(o)){
            return super.add(o);
        }
        return false;
    };

    public void add(int index, E element) {
        if(! contains(element)){
            super.add(index, element);
        }
    };


    public boolean addAll(Collection<? extends E> c) {
        for(E e:c){
              add(e);
        }
        return true;
    }
}
