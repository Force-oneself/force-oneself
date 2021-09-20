package com.quan.framework.mongo.push.prepare;

import com.quan.framework.mongo.push.context.Context;

import java.util.List;

/**
 * @author Force-oneself
 * @Description DefaultPrepareManager
 * @date 2021-09-15
 */
public class DefaultPrepareManager implements PrepareManager{

    private List<Preparer> preparers;

    @Override
    public void prepare(Context context) {
        List<Preparer> temp;
        if (preparers == null) {
            temp = context.getPrepares();
        } else {
            preparers.addAll(context.getPrepares());
            temp = preparers;
        }
        if (temp == null) {
            return;
        }
        temp.forEach(preparer -> preparer.prepare(context));
    }
}
