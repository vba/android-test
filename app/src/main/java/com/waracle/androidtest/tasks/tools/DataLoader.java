package com.waracle.androidtest.tasks.tools;


public interface DataLoader<TIn, TOut> {
    TOut load(TIn in);
}
