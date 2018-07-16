package com.example.hcwong.testproject.DI.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityScope {
    //The scope that exist for the whole application lifecycle.
}
