package cn.ist.lowcoding.framework.service;

import cn.ist.lowcoding.framework.persistence.Persistence;

public interface ServiceFramework<Out, In> {
    Out service(Persistence persistence, In in);
}
