package com.davy.restapi.shared.mapper;

import org.springframework.stereotype.Service;
import java.util.function.Function;

@Service
public interface Mapper<S, D> extends Function<S, D>  {
}
