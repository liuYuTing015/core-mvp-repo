package com.core.app.model;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by Ting on 17/5/26.
 */

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@interface QualifierB { }