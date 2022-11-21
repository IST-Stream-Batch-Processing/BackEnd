package cn.ist.lowcoding.common.exception;

import lombok.Data;
import lombok.Getter;

import java.util.List;

public class DependencyException extends RuntimeException {
    @Getter
    List<String> dependencies;

    public DependencyException(List<String> dependencies) {
        this.dependencies = dependencies;
    }
}
