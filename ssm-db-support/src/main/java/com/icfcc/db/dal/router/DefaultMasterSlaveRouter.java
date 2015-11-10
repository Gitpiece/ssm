package com.icfcc.db.dal.router;

import com.icfcc.db.dal.datasource.DBContextHolder;

public class DefaultMasterSlaveRouter implements MasterSlaveRouter {

    @Override
    public void routeToMaster() {
        DBContextHolder.swithToWrite();

    }

    @Override
    public void routeToSlave() {
        DBContextHolder.swithToRead();

    }

}
